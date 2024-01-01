package com.project.amphibians.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
// import retrofit2.converter.j
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.gson.GsonConverterFactory

// import retrofit2.Converter

interface AppContainer {
  val amphibiansRepository: AmphibiansRepository
}

val gson: Gson = GsonBuilder().setLenient().create()

class DefaultAppContainer : AppContainer {

  private val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

  private val retrofit: Retrofit = Retrofit.Builder()
    // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

  // I think this is where the actual api call is being made to fetch the data from the web.
  private val retrofitService: AmphibiansApiService by lazy {
    retrofit.create(AmphibiansApiService::class.java)
  }

  // Create a repository for the data.
  override val amphibiansRepository: AmphibiansRepository by lazy {
    NetworkAmphibiansRepository(retrofitService)
  }
}