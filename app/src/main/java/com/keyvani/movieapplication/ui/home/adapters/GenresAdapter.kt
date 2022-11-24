package com.keyvani.movieapplication.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.keyvani.movieapplication.databinding.ItemHomeGenreListBinding
import com.keyvani.movieapplication.response.home.ResponseGenresList
import com.keyvani.movieapplication.response.home.ResponseGenresList.*
import com.keyvani.movieapplication.response.home.ResponseMoviesList.*
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    //Binding
    private lateinit var binding: ItemHomeGenreListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseGenresListItem) {
            binding.apply {
                tvGenreName.text = item.name

            }
        }

    }

    private val differCalback = object : DiffUtil.ItemCallback<ResponseGenresListItem>() {
        override fun areItemsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCalback)
}





