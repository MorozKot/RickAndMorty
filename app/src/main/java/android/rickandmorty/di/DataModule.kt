package android.rickandmorty.di

import android.rickandmorty.data.database.RickMortyDatabase
import android.rickandmorty.data.network.ApiService
import android.rickandmorty.data.repository.RickMortyRepositoryImpl
import android.rickandmorty.domain.usecases.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadAllEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadAllLocationsUseCase
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideLoadAllCharactersUseCase(rickMortyRepositoryImpl: RickMortyRepositoryImpl): LoadAllCharactersUseCase {
        return LoadAllCharactersUseCase(rickMortyRepositoryImpl)
    }

    @Provides
    fun provideLoadAllEpisodesUseCase(rickMortyRepositoryImpl: RickMortyRepositoryImpl): LoadAllEpisodesUseCase {
        return LoadAllEpisodesUseCase(rickMortyRepositoryImpl)
    }

    @Provides
    fun provideLoadAllLocationsUseCase(rickMortyRepositoryImpl: RickMortyRepositoryImpl): LoadAllLocationsUseCase {
        return LoadAllLocationsUseCase(rickMortyRepositoryImpl)
    }

    @Provides
    fun provideCharactersRepository(
        retrofitService: ApiService,
        db: RickMortyDatabase
    ): RickMortyRepositoryImpl {
        return RickMortyRepositoryImpl(retrofitService, db)
    }
}