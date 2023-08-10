package com.turun.neweasyfoodmvvm.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//4 randommeal
object RetrofitInstance {
    val api:MealApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MealApi::class.java)
    }
}
//4 randommeal ve go to homefragment.kt