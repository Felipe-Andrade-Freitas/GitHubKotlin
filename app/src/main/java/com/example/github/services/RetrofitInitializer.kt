package com.example.github.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                    chain.proceed(newRequest.build())
                }
                .addInterceptor(HttpLoggingInterceptor().also { it ->
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }

    }

    private val retrofitApiGit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        fun serviceRepositorio(): RepositorioService {
            return retrofitApiGit.create(RepositorioService::class.java)
        }


        private val retrofitGit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val retrofitlogin = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.fluo.work/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceAccount(): AccountService {
        return retrofitlogin.create(AccountService::class.java)
    }


    fun serviceAutentication(): AutenticationService {
        return retrofitGit.create(AutenticationService::class.java)
    }

    }
