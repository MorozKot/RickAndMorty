package android.rickandmorty.presentation.locations

import android.rickandmorty.databinding.ItemLocationBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation

class LocationsAdapter :
    ListAdapter<SingleLocation, LocationsAdapter.ListLocationsViewHolder>(LocationDiffCallback) {

    object LocationDiffCallback : DiffUtil.ItemCallback<SingleLocation>() {
        override fun areItemsTheSame(oldItem: SingleLocation, newItem: SingleLocation): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SingleLocation, newItem: SingleLocation): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListLocationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)

        return ListLocationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListLocationsViewHolder, position: Int) {
        val singleLocation = getItem(position)

        with(holder.binding) {
            tvLocationName.text = singleLocation.name
            tvType.text = singleLocation.type
            tvDimension.text = singleLocation.dimension
        }
    }

    class ListLocationsViewHolder(var binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root)
}