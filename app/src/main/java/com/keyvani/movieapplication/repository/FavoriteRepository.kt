package com.keyvani.movieapplication.repository

import com.keyvani.movieapplication.db.MovieDao
import javax.inject.Inject


class FavoriteRepository @Inject constructor(private val dao: MovieDao){

    fun allFavoriteList()=dao.getAllMovies()
}