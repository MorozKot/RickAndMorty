package android.rickandmorty.presentation.characters

import android.rickandmorty.domain.models.SingleCharacter
import android.rickandmorty.domain.usecases.LoadAllCharactersUseCase
import android.rickandmorty.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase
) : ViewModel() {

    var page = 1

    private val _allCharacters = MutableLiveData<List<SingleCharacter>?>()
    val allCharacters: LiveData<List<SingleCharacter>?> = _allCharacters

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadAllCharacters()
    }

    fun loadAllCharacters() {
        viewModelScope.launch {
            loadAllCharactersUseCase.loadAllCharacters(page).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _allCharacters.postValue(result.data)
                        page++
                        _isDataLoading.value = false
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _allCharacters.postValue(result.data)
                        if (result.data.isNullOrEmpty()) {
                            _isError.postValue(true)
                        }
                        _isDataLoading.postValue(false)
                    }
                    is Resource.Loading -> {
                        _isDataLoading.value = true
                        _isError.postValue(false)
                    }
                }
            }
        }
    }
}