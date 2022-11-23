package com.keyvani.movieapplication.repository

import com.keyvani.movieapplication.api.ApiServices
import com.keyvani.movieapplication.response.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: ApiServices) {
    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)
}