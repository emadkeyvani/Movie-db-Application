package com.keyvani.movieapplication.repository

import com.keyvani.movieapplication.api.ApiServices
import com.keyvani.movieapplication.db.MovieDao
import com.keyvani.movieapplication.db.MovieEntity
import javax.inject.Inject

class DetailRepository@Inject constructor(private val api : ApiServices, private val dao:MovieDao){
    //Api
    suspend fun detailMovie (id:Int)= api.detailMovie(id)
    //Database
    suspend fun insertMovie(entity: MovieEntity) = dao.insertMovie(entity)
    suspend fun deleteMovie(entity: MovieEntity) = dao.deleteMovie(entity)
    suspend fun existMovie(id : Int) = dao.existsMovie(id)

}