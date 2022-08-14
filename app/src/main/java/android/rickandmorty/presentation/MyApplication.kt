package android.rickandmorty.presentation

import android.app.Application
import android.rickandmorty.di.DaggerAppComponent
import android.rickandmorty.di.RoomModule

class MyApplication : Application() {

    val component by lazy {
        DaggerAppComponent.builder()
            .roomModule(RoomModule(context = this)).build()
    }
}