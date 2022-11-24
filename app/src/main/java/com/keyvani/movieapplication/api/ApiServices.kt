package com.keyvani.movieapplication.api

import com.keyvani.movieapplication.response.home.ResponseMoviesList
import com.keyvani.movieapplication.response.register.BodyRegister
import com.keyvani.movieapplication.response.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

}