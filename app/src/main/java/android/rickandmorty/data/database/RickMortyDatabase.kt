package android.rickandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.SingleEpisodeEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.locations.SingleLocationEntity

@Database(
    entities = [SingleCharacterEntity::class, SingleLocationEntity::class, SingleEpisodeEntity::class],
    version = 1
)
abstract class RickMortyDatabase: RoomDatabase() {
    abstract fun getDatabase(): RickMortyDao
}
