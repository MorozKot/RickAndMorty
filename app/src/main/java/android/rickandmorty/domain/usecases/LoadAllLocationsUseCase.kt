package com.mytestprogram.rickmortyapplication.domain.usecases.locations

import android.rickandmorty.domain.repository.RickMortyRepository
import android.rickandmorty.utils.Resource
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllLocationsUseCase @Inject constructor(
    private val rickMortyRepository: RickMortyRepository
) {

    fun loadAllLocations(page: Int): Flow<Resource<List<SingleLocation>>> {
        return rickMortyRepository.loadAllLocations(page)
    }
}