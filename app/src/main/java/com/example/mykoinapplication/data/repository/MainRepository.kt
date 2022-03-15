package com.example.mykoinapplication.data.repository

import com.example.mykoinapplication.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper){

    suspend fun getUsers() = apiHelper.getUsers()
}