package android.rickandmorty.data.network.model.characters

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllCharactersDto(
    @SerializedName("results")
    @Expose
    val resultsCharacter: List<SingleCharacterDto>
)