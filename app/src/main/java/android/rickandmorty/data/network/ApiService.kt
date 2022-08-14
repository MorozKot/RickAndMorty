package android.rickandmorty.data.network

import android.rickandmorty.data.network.model.characters.AllCharactersDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.episodes.AllEpisodesDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.locations.AllLocationsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): AllCharactersDto

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): AllEpisodesDto

    @GET("location")
    suspend fun getAllLocations(@Query("page") page: Int): AllLocationsDto
}
