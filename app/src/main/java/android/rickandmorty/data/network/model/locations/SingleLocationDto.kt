package com.mytestprogram.rickmortyapplication.data.remote.dto.locations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mytestprogram.rickmortyapplication.data.local.entities.locations.SingleLocationEntity

data class SingleLocationDto(
    @SerializedName("dimension")
    @Expose
    val dimension: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("type")
    @Expose
    val type: String
){
    fun toSingleLocationEntity(): SingleLocationEntity {
        return SingleLocationEntity(
            dimension, name, type
        )
    }
}