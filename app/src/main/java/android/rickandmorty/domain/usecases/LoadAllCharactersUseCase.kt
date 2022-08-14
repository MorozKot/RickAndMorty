package android.rickandmorty.domain.usecases

import android.rickandmorty.domain.models.SingleCharacter
import android.rickandmorty.domain.repository.RickMortyRepository
import android.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllCharactersUseCase @Inject constructor(
    private val rickMortyRepository: RickMortyRepository
) {
    fun loadAllCharacters(page: Int): Flow<Resource<List<SingleCharacter>>> {
        return rickMortyRepository.loadAllCharacters(page)
    }
}