package android.rickandmorty.domain.repository

import android.rickandmorty.domain.models.SingleCharacter
import android.rickandmorty.utils.Resource
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import kotlinx.coroutines.flow.Flow

interface RickMortyRepository {

    fun loadAllCharacters(page: Int): Flow<Resource<List<SingleCharacter>>>

    fun loadAllEpisodes(page: Int): Flow<Resource<List<SingleEpisode>>>

    fun loadAllLocations(page: Int): Flow<Resource<List<SingleLocation>>>
}
