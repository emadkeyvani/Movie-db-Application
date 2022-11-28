package com.keyvani.movieapplication.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keyvani.movieapplication.databinding.ItemDetailImagesBinding
import com.keyvani.movieapplication.response.datail.ResponseDetail
import javax.inject.Inject

class DetailImagesAdapter @Inject constructor() : RecyclerView.Adapter<DetailImagesAdapter.ViewHolder>() {

    //Binding
    private lateinit var binding: ItemDetailImagesBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImagesAdapter.ViewHolder {
        binding = ItemDetailImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun setData(item: String) {
            binding.apply {
                imgItem.load(item) {
                    crossfade(true)
                    crossfade(800)
                }

            }
        }

    }

    private val differCalback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCalback)
}





