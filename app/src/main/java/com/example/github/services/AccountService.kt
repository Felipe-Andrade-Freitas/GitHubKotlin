package com.example.github.services

import com.example.github.models.AccessToken
import com.example.github.models.Account
import com.example.github.models.Repositorio
import retrofit2.Call
import retrofit2.http.*


interface AccountService {

    @Multipart
    @POST("account/auth")
    fun auth(@Body account: Account) : Call<Account>

}