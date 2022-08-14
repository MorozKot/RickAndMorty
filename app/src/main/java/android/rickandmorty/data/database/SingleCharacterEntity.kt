package android.rickandmorty.data.database

import android.rickandmorty.domain.models.SingleCharacter
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class SingleCharacterEntity(
    @PrimaryKey
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String
) {
    fun toSingleCharacter(): SingleCharacter {
        return SingleCharacter(
            name, species, status, gender, image
        )
    }
}

