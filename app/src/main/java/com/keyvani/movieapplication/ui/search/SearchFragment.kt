package com.keyvani.movieapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keyvani.movieapplication.databinding.FragmentSearchBinding
import com.keyvani.movieapplication.ui.home.adapters.LastMoviesAdapter
import com.keyvani.movieapplication.utils.initRecycler
import com.keyvani.movieapplication.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchAdapter: LastMoviesAdapter

    //Other
    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {
            edtSearch.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    viewModel.loadSearchMovie(search)
                }
            }
            // Get movies list
            viewModel.moviesList.observe(viewLifecycleOwner) {
                searchAdapter.setData(it.data)
                //RecyclerView
                rvSearchedMovie.initRecycler(LinearLayoutManager(requireContext()), searchAdapter)

            }
            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    pbSearch.visibility = View.VISIBLE
                } else {
                    pbSearch.visibility = View.GONE

                }
            }
            //Empty items
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    conEmptyLayout.visibility = View.VISIBLE
                    rvSearchedMovie.visibility = View.GONE
                } else {
                    conEmptyLayout.visibility = View.GONE
                    rvSearchedMovie.visibility = View.VISIBLE
                }
            }

        }

    }

}