package com.keyvani.movieapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.keyvani.movieapplication.R
import com.keyvani.movieapplication.databinding.FragmentDetailBinding
import com.keyvani.movieapplication.db.MovieEntity
import com.keyvani.movieapplication.utils.initRecycler
import com.keyvani.movieapplication.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var detailImagesAdapter: DetailImagesAdapter

    @Inject
    lateinit var entity: MovieEntity

    //Other
    private val viewModel: DetailViewModel by viewModels()
    private var movieID = 0
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get data
        movieID = args.movieID
        //Call api
        if (movieID > 0) {
            viewModel.loadDetailMovie(movieID)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Load data
            viewModel.detailMovie.observe(viewLifecycleOwner) { response ->
                imgBigPoster.load(response.poster)
                imgNormalPoster.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                movieNameTxt.text = response.title
                movieRateTxt.text = response.imdbRating
                movieTimeTxt.text = response.runtime
                movieDateTxt.text = response.released
                movieSummaryInfo.text = response.plot
                movieActorsInfo.text = response.actors
                //DetailImagesAdapter
                detailImagesAdapter.differ.submitList(response.images)
                imagesRecyclerView.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    detailImagesAdapter
                )
                //Fav click
                favImg.setOnClickListener {
                    entity.id = movieID
                    entity.poster = response.poster.toString()
                    entity.title = response.title.toString()
                    entity.rate = response.imdbRating.toString()
                    entity.contry = response.country.toString()
                    entity.year = response.year.toString()
                    viewModel.favoriteMovie(movieID, entity)


                }

            }
            //loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    detailLoading.visibility = View.VISIBLE
                    detailScrollView.visibility = View.GONE
                } else {
                    detailLoading.visibility = View.GONE
                    detailScrollView.visibility = View.VISIBLE

                }
            }
            // Default fav icon color
            lifecycleScope.launchWhenCreated {
                if (viewModel.existMovie(movieID)) {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet))
                } else {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.philippineSilver))
                }
            }
            //Change img with click
            viewModel.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet))
                } else {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.philippineSilver))
                }
            }

            //Back
            backImg.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

}