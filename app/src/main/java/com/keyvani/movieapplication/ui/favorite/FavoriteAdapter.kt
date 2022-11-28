package com.keyvani.movieapplication.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keyvani.movieapplication.databinding.ItemHomeMoviesLastBinding
import com.keyvani.movieapplication.db.MovieEntity
import com.keyvani.movieapplication.response.home.ResponseMoviesList
import com.keyvani.movieapplication.response.home.ResponseMoviesList.*
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    //Binding
    private lateinit var binding: ItemHomeMoviesLastBinding
    private var movieList = emptyList<MovieEntity>()


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
        fun bindItems(item: MovieEntity) {
            binding.apply {
                tvMovieName.text = item.title
                tvMovieRate.text = item.rate
                tvMovieCountry.text = item.contry
                tvMovieYear.text = item.year
                ivMoviePoster.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let{
                        it(item)
                    }
                }

            }

        }
    }
    private var onItemClickListener : ((MovieEntity)->Unit) ?= null

    fun setonItemClickListener (listener : (MovieEntity)->Unit){
        onItemClickListener = listener
    }

    fun setData(data: List<MovieEntity>) {
        val moviesDiffUtil = MoviesDiffUtils(movieList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        movieList = data
        diffUtils.dispatchUpdatesTo(this)

    }

    class MoviesDiffUtils(
        private val oldItem: List<MovieEntity>, private val newItem: List<MovieEntity>
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


