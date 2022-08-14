package com.mytestprogram.rickmortyapplication.data.remote.dto.locations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllLocationsDto(
    @SerializedName("results")
    @Expose
    val resultsLocation: List<SingleLocationDto>
)