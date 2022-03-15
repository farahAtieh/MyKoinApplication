package com.example.mykoinapplication.data.api

import com.example.mykoinapplication.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}