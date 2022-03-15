package com.example.mykoinapplication.data.api

import com.example.mykoinapplication.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService): ApiHelper{

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()
}