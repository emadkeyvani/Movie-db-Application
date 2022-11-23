package com.keyvani.movieapplication.api

import com.keyvani.movieapplication.response.register.BodyRegister
import com.keyvani.movieapplication.response.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>
}