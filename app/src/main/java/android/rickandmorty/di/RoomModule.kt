package android.rickandmorty.di

import android.content.Context
import android.rickandmorty.data.database.RickMortyDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val RICKMORTY_DB_NAME = "rick_morty_database"

@Module
class RoomModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRickMortyDatabase(context: Context): RickMortyDatabase =
        Room.databaseBuilder(context, RickMortyDatabase::class.java, RICKMORTY_DB_NAME)
            .build()
}