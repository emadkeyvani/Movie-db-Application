package com.keyvani.movieapplication.repository

import com.keyvani.movieapplication.api.ApiServices
import javax.inject.Inject


class HomeRepository @Inject constructor(private val api :ApiServices){
    suspend fun topMoviesList (id :Int) = api.moviesTopList(id)
    suspend fun genresList () = api.genresList()
}