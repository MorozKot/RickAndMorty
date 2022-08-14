package android.rickandmorty.di

import android.bignerdranch.pokemon.di.ViewModelModule
import android.rickandmorty.presentation.characters.CharactersFragment
import android.rickandmorty.presentation.episodes.EpisodesFragment
import android.rickandmorty.presentation.locations.LocationsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class, DataModule::class, RoomModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: CharactersFragment)

    fun inject(fragment: EpisodesFragment)

    fun inject(fragment: LocationsFragment)
}