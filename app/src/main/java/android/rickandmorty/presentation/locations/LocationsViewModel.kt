package android.rickandmorty.presentation.locations

import android.rickandmorty.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadAllLocationsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val loadAllLocationsUseCase: LoadAllLocationsUseCase
) : ViewModel() {

    var page = 1

    private val _allLocations = MutableLiveData<List<SingleLocation>?>()
    val allLocations: LiveData<List<SingleLocation>?> = _allLocations

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadAllLocations()
    }

    fun loadAllLocations() {
        viewModelScope.launch {
            loadAllLocationsUseCase.loadAllLocations(page).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _allLocations.postValue(result.data)
                        page++
                        _isDataLoading.value = false
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _allLocations.postValue(result.data)
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