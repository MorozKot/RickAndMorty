package android.rickandmorty.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.SingleEpisodeEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.locations.SingleLocationEntity

@Dao
interface RickMortyDao {
    @Query("SELECT * FROM characters")
    suspend fun getCharactersList(): List<SingleCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharactersList(charactersList: List<SingleCharacterEntity>)

    @Query("SELECT * FROM episodes")
    suspend fun getEpisodesList(): List<SingleEpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEpisodesList(episodesList: List<SingleEpisodeEntity>)

    @Query("SELECT * FROM locations")
    suspend fun getLocationsList(): List<SingleLocationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocationsList(locationsList: List<SingleLocationEntity>)

}