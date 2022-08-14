package com.mytestprogram.rickmortyapplication.data.remote.dto.episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.SingleEpisodeEntity

data class SingleEpisodeDto(
    @SerializedName("air_date")
    @Expose
    val air_date: String,
    @SerializedName("episode")
    @Expose
    val episode: String,
    @SerializedName("name")
    @Expose
    val name: String
){
    fun toSingleEpisodeEntity(): SingleEpisodeEntity {
        return SingleEpisodeEntity(
            air_date, episode, name
        )
    }
}