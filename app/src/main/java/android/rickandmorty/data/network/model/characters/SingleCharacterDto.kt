package android.rickandmorty.data.network.model.characters

import android.rickandmorty.data.database.SingleCharacterEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleCharacterDto(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("species")
    @Expose
    val species: String,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("gender")
    @Expose
    val gender: String,
    @SerializedName("image")
    @Expose
    val image: String)
{
    fun toSingleCharacterEntity(): SingleCharacterEntity {
        return SingleCharacterEntity(
            name, species, status, gender, image
        )
    }
}