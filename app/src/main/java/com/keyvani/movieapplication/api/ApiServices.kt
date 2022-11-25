package com.keyvani.movieapplication.api

import com.keyvani.movieapplication.response.home.ResponseGenresList
import com.keyvani.movieapplication.response.home.ResponseMoviesList
import com.keyvani.movieapplication.response.register.BodyRegister
import com.keyvani.movieapplication.response.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenresList>

    @GET("movies")
    suspend fun lastMovies(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovie(@Query ("q")name:String): Response<ResponseMoviesList>

}