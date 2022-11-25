package com.keyvani.movieapplication.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keyvani.movieapplication.databinding.ItemHomeMoviesLastBinding
import com.keyvani.movieapplication.response.home.ResponseMoviesList
import com.keyvani.movieapplication.response.home.ResponseMoviesList.*
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor() : RecyclerView.Adapter<LastMoviesAdapter.ViewHolder>() {

    //Binding
    private lateinit var binding: ItemHomeMoviesLastBinding
    private var movieList = emptyList<ResponseMoviesList.Data>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(movieList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = movieList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItems(item: ResponseMoviesList.Data) {
            binding.apply {
                tvMovieName.text = item.title
                tvMovieRate.text = item.imdbRating
                tvMovieCountry.text = item.country
                tvMovieYear.text = item.year
                ivMoviePoster.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }

            }


        }
    }

    fun setData(data: List<ResponseMoviesList.Data>) {
        val moviesDiffUtil = MoviesDiffUtils(movieList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        movieList = data
        diffUtils.dispatchUpdatesTo(this)

    }

    class MoviesDiffUtils(
        private val oldItem: List<ResponseMoviesList.Data>, private val newItem: List<ResponseMoviesList.Data>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }


    }

}


