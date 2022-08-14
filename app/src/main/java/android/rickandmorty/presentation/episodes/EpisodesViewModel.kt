package android.rickandmorty.presentation.episodes

import android.rickandmorty.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadAllEpisodesUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val loadAllEpisodesUseCase: LoadAllEpisodesUseCase
) : ViewModel() {

    var page = 1

    private val _allEpisodes = MutableLiveData<List<SingleEpisode>?>()
    val allEpisodes: LiveData<List<SingleEpisode>?> = _allEpisodes

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadAllEpisodes()
    }

    fun loadAllEpisodes() {
        viewModelScope.launch {
            loadAllEpisodesUseCase.loadAllEpisodes(page).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _allEpisodes.postValue(result.data)
                        page++
                        _isDataLoading.value = false
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _allEpisodes.postValue(result.data)
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