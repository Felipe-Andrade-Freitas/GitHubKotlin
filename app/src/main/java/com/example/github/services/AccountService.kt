package com.example.github.services

import com.example.github.models.Account
import retrofit2.Call
import retrofit2.http.*


interface AccountService {


    @POST("account/auth")
    fun auth(@Body account: Account) : Call<Account>

}