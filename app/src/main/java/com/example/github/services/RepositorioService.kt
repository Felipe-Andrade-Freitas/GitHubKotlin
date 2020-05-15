package com.example.github.services

import com.example.github.models.AccessToken
import com.example.github.models.Repositorio
import com.example.github.models.Usuario
import retrofit2.Call
import retrofit2.http.*

interface RepositorioService {

    @GET("repositories")
    fun getRepositorios() : Call<List<Repositorio>>

    @GET("users/{user}/repos")
    fun getRepositoriosPorUsuario(@Path("user") user : String) : Call<List<Repositorio>>

    @Headers("Authorization: {type_token} {access_token}")
    @GET("user")
    fun getUsuario(@Path("type_token") type_token : String, @Path("access_token") access_token : String) : Call<Usuario>

    @GET("users/{user}")
    fun getUsuarioPorNome(@Path("user") user : String) : Call<Usuario>
}