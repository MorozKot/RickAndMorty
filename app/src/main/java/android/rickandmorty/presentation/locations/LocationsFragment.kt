package android.rickandmorty.presentation.locations

import android.content.Context
import android.os.Bundle
import android.rickandmorty.databinding.FragmentLocationsBinding
import android.rickandmorty.presentation.MyApplication
import android.rickandmorty.presentation.ViewModelFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class LocationsFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as MyApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LocationsViewModel

    private lateinit var binding: FragmentLocationsBinding

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LocationsAdapter()

        binding.recyclerViewLocations.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)[LocationsViewModel::class.java]

        viewModel.allLocations.observe(viewLifecycleOwner) {
            adapter.submitList(it)

            viewModel.isDataLoading.observe(viewLifecycleOwner) {
                binding.listLocationsProgressBar.isVisible = it
            }

            viewModel.isError.observe(viewLifecycleOwner) {
                if (it == true)
                    Toast.makeText(
                        context, "Check your internet connection",
                        Toast.LENGTH_SHORT
                    )
                        .show()
            }
        }

        var isScrolling = false
        binding.recyclerViewLocations.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalVisibleItems = recyclerView.layoutManager!!.itemCount
                val pastVisibleItem = (recyclerView.layoutManager as GridLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()

                if (isScrolling && visibleItemCount + pastVisibleItem >= totalVisibleItems) {
                    isScrolling = false
                    viewModel.loadAllLocations()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        })

        binding.swipeContainer.setOnRefreshListener {
            viewModel.allLocations.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            binding.swipeContainer.isRefreshing = false
        }
    }
}