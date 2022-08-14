package android.rickandmorty.presentation.characters

import android.rickandmorty.databinding.ItemCharacterBinding
import android.rickandmorty.domain.models.SingleCharacter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CharactersAdapter :
    ListAdapter<SingleCharacter, CharactersAdapter.ListCharactersViewHolder>(CharacterDiffCallback) {

    object CharacterDiffCallback: DiffUtil.ItemCallback<SingleCharacter>() {
        override fun areItemsTheSame(oldItem: SingleCharacter, newItem: SingleCharacter): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SingleCharacter, newItem: SingleCharacter): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)

        return ListCharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCharactersViewHolder, position: Int) {
        val singleCharacter = getItem(position)

        Glide.with(holder.itemView)
            .load(singleCharacter.image)
            .into(holder.binding.imageViewCharacter)

        with(holder.binding) {
            tvCharacterName.text = singleCharacter.name
            tvSpecies.text = singleCharacter.species
            tvGender.text = singleCharacter.gender
            tvStatus.text = singleCharacter.status
        }
    }

    inner class ListCharactersViewHolder(var binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)
}