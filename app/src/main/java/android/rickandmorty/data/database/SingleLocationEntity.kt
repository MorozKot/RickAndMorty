package com.mytestprogram.rickmortyapplication.data.local.entities.locations

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation

@Entity(tableName = "locations")
data class SingleLocationEntity(
    val dimension: String,
    @PrimaryKey
    val name: String,
    val type: String
){
    fun toSingleLocation(): SingleLocation {
        return SingleLocation(
            dimension, name, type
        )
    }
}