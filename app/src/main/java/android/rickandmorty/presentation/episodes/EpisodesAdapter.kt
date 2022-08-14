package android.rickandmorty.presentation.episodes

import android.rickandmorty.databinding.ItemEpisodeBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode

class EpisodesAdapter :
    ListAdapter<SingleEpisode, EpisodesAdapter.ListEpisodesViewHolder>(EpisodeDiffCallback) {

    object EpisodeDiffCallback : DiffUtil.ItemCallback<SingleEpisode>() {
        override fun areItemsTheSame(oldItem: SingleEpisode, newItem: SingleEpisode): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SingleEpisode, newItem: SingleEpisode): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEpisodesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodeBinding.inflate(inflater, parent, false)

        return ListEpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListEpisodesViewHolder, position: Int) {
        val singleEpisode = getItem(position)

        with(holder.binding) {
            tvEpisodeName.text = singleEpisode.name
            tvEpisode.text = singleEpisode.episode
            tvAirDate.text = singleEpisode.air_date
        }
    }

    inner class ListEpisodesViewHolder(var binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root)
}