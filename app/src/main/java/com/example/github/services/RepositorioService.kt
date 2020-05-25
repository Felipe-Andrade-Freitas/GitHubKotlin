package com.example.github.services

import com.example.github.models.AccessToken
import com.example.github.models.AutorSearch
import com.example.github.models.Repositorio
import com.example.github.models.Usuario
import retrofit2.Call
import retrofit2.http.*

interface RepositorioService {

    @GET("users/{user}/repos")
    fun getRepositoriosPorUsuario(@Path("user") user : String) : Call<List<Repositorio>>

    @GET("users/{user}")
    fun getUsuarioPorNome(@Path("user") user : String) : Call<Usuario>

    @GET("search/users")
    fun getUsuarioPorNomeQuery(@Query("q") user : String) : Call<AutorSearch>
}