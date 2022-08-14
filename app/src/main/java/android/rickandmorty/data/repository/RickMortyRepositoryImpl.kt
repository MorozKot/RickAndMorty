package android.rickandmorty.data.repository

import android.rickandmorty.data.database.RickMortyDatabase
import android.rickandmorty.data.network.ApiService
import android.rickandmorty.domain.models.SingleCharacter
import android.rickandmorty.domain.repository.RickMortyRepository
import android.rickandmorty.utils.Resource
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val db: RickMortyDatabase
) : RickMortyRepository {

    private val rickMortyDao = db.getDatabase()

    override fun loadAllCharacters(page: Int): Flow<Resource<List<SingleCharacter>>> = flow {
        emit(Resource.Loading())
        val dataCharactersFromDb = rickMortyDao.getCharactersList().map { it.toSingleCharacter() }
        emit(Resource.Loading(dataCharactersFromDb))
        try {
            val remoteCharacters = api.getAllCharacters(page)
            rickMortyDao.insertCharactersList(remoteCharacters.resultsCharacter.map { it.toSingleCharacterEntity() })
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Try again to load Characters",
                    data = dataCharactersFromDb
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check internet connection!",
                    data = dataCharactersFromDb
                )
            )
        }

        val newCharactersData = rickMortyDao.getCharactersList().map { it.toSingleCharacter() }
        emit(Resource.Success(newCharactersData))
    }

    override fun loadAllEpisodes(page: Int): Flow<Resource<List<SingleEpisode>>> = flow {
        emit(Resource.Loading())
        val dataEpisodesFromDb = rickMortyDao.getEpisodesList().map { it.toSingleEpisode() }
        emit(Resource.Loading(dataEpisodesFromDb))
        try {
            val remoteEpisodes = api.getAllEpisodes(page)
            rickMortyDao.insertEpisodesList(remoteEpisodes.resultsEpisode.map { it.toSingleEpisodeEntity() })
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Try again to load Episodes",
                    data = dataEpisodesFromDb
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check internet connection!",
                    data = dataEpisodesFromDb
                )
            )
        }

        val newEpisodesData = rickMortyDao.getEpisodesList().map { it.toSingleEpisode() }
        emit(Resource.Success(newEpisodesData))
    }

    override fun loadAllLocations(page: Int): Flow<Resource<List<SingleLocation>>> = flow {
        emit(Resource.Loading())
        val dataLocationsFromDb = rickMortyDao.getLocationsList().map { it.toSingleLocation() }
        emit(Resource.Loading(dataLocationsFromDb))
        try {
            val remoteLocations = api.getAllLocations(page)
            rickMortyDao.insertLocationsList(remoteLocations.resultsLocation.map { it.toSingleLocationEntity() })
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Try again to load Locations",
                    data = dataLocationsFromDb
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check internet connection!",
                    data = dataLocationsFromDb
                )
            )
        }

        val newLocationsData = rickMortyDao.getLocationsList().map { it.toSingleLocation() }
        emit(Resource.Success(newLocationsData))
    }
}