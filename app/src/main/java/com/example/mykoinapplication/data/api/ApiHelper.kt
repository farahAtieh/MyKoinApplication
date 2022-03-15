package com.example.mykoinapplication.data.api

import com.example.mykoinapplication.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}