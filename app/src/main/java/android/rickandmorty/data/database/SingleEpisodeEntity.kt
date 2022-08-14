package com.mytestprogram.rickmortyapplication.data.local.entities.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode


@Entity(tableName = "episodes")
data class SingleEpisodeEntity(
    val air_date: String,
    val episode: String,
    @PrimaryKey
    val name: String
) {
    fun toSingleEpisode(): SingleEpisode {
        return SingleEpisode(
            air_date, episode, name
        )
    }
}