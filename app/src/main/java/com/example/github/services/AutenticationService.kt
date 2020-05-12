package com.example.github.services

import com.example.github.models.AccessToken
import com.example.github.models.Repositorio
import retrofit2.Call
import retrofit2.http.*


interface AutenticationService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") client_id : String,
        @Field("client_secret") client_secret : String,
        @Field("code") code : String?
    ) : Call<AccessToken>
}