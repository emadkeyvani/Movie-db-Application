package com.keyvani.movieapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keyvani.movieapplication.databinding.FragmentFavoriteBinding
import com.keyvani.movieapplication.utils.initRecycler
import com.keyvani.movieapplication.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //Other
    private val viewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Show All Fav
            viewModel.favoriteList.observe(viewLifecycleOwner) {
                favoriteAdapter.setData(it)
                rvFavoriteMovie.initRecycler(LinearLayoutManager(requireContext()), favoriteAdapter)
            }

            //Empty items
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    conEmptyLayout.visibility = View.VISIBLE
                    rvFavoriteMovie.visibility = View.GONE
                } else {
                    conEmptyLayout.visibility = View.GONE
                    rvFavoriteMovie.visibility = View.VISIBLE
                }
            }

        }

    }

}