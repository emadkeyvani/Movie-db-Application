package com.keyvani.movieapplication.repository

import com.keyvani.movieapplication.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api :ApiServices){
    suspend fun searchMovie (name:String)= api.searchMovie(name)
}