package com.mytestprogram.rickmortyapplication.data.remote.dto.episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllEpisodesDto(
    @SerializedName("results")
    @Expose
    val resultsEpisode: List<SingleEpisodeDto>
)