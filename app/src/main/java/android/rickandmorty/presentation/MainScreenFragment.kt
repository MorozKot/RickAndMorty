package android.rickandmorty.presentation

import android.os.Bundle
import android.rickandmorty.databinding.FragmentMainScreenBinding
import android.rickandmorty.presentation.characters.CharactersFragment
import android.rickandmorty.presentation.episodes.EpisodesFragment
import android.rickandmorty.presentation.locations.LocationsFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator

class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding

    private val fragmentsList = listOf(
        CharactersFragment(),
        EpisodesFragment(),
        LocationsFragment()
    )

    private val titlesList = listOf(
        CHARACTERS_TITLE,
        EPISODES_TITLE,
        LOCATIONS_TITLE
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = PagerAdapter(this, fragmentsList)

        TabLayoutMediator(binding.tableLayout, binding.viewPager) { tab, position ->
            tab.text = titlesList[position]
        }.attach()
    }

    companion object {
        private const val CHARACTERS_TITLE = "Characters"
        private const val EPISODES_TITLE = "Episodes"
        private const val LOCATIONS_TITLE = "Locations"
    }
}