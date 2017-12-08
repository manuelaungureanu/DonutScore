package com.chefless.ela.donutscore

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call

interface ScoreApiService {

    @GET("values")
    fun hitScoreCheck(): Call<Model.Result>

    companion object {
        fun create(): ScoreApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/prod/mockcredit/")
                    .build()

            return retrofit.create(ScoreApiService::class.java)
        }
    }
}