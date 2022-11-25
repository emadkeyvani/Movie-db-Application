package com.keyvani.movieapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.keyvani.movieapplication.R
import com.keyvani.movieapplication.databinding.FragmentHomeBinding
import com.keyvani.movieapplication.ui.home.adapters.GenresAdapter
import com.keyvani.movieapplication.ui.home.adapters.LastMoviesAdapter
import com.keyvani.movieapplication.ui.home.adapters.TopMoviesAdapter
import com.keyvani.movieapplication.utils.initRecycler
import com.keyvani.movieapplication.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    //Other
    private val viewModel: HomeViewModel by viewModels()
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Api call
        viewModel.loadTopMoviesList(1)
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {
            //Get top movies
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)
                //RecyclerView
                rvTopMovies.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter

                )

                //Indicator
                pagerHelper.attachToRecyclerView(rvTopMovies)
                inTopMovies.attachToRecyclerView(rvTopMovies, pagerHelper)

            }
            //Get genres
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)
                //RecyclerView
                rvGenre.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter

                )
            }

            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.data)
                //RecyclerView
                rvLastMovies.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false),
                    lastMoviesAdapter

                )

            }
            //Loading
            viewModel.loading.observe(viewLifecycleOwner){
                if(it){
                    pbMoviesLoading.visibility=View.VISIBLE
                    nsMovies.visibility=View.INVISIBLE


                }else{
                    pbMoviesLoading.visibility=View.GONE
                    nsMovies.visibility=View.VISIBLE

                }
            }
        }

    }
}