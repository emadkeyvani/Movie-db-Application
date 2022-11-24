package com.keyvani.movieapplication.response.home


import com.google.gson.annotations.SerializedName

class ResponseGenresList : ArrayList<ResponseGenresList.ResponseGenresListItem>(){
    data class ResponseGenresListItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Crime
    )
}