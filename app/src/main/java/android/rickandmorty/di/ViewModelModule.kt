package android.bignerdranch.pokemon.di

import android.rickandmorty.presentation.characters.CharactersViewModel
import android.rickandmorty.presentation.episodes.EpisodesViewModel
import android.rickandmorty.presentation.locations.LocationsViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(impl: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    fun bindEpisodesViewModel(impl: EpisodesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    fun bindLocationsViewModel(impl: LocationsViewModel): ViewModel
}
