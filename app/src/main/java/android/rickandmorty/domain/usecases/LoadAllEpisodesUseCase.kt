package com.mytestprogram.rickmortyapplication.domain.usecases.episodes

import android.rickandmorty.domain.repository.RickMortyRepository
import android.rickandmorty.utils.Resource
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllEpisodesUseCase @Inject constructor(
    private val rickMortyRepository: RickMortyRepository
) {

    fun loadAllEpisodes(page: Int): Flow<Resource<List<SingleEpisode>>> {
        return rickMortyRepository.loadAllEpisodes(page)
    }
}