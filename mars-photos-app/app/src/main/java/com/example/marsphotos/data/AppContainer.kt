package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
  val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer : AppContainer {

  private val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

  private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

  // I think this is where the actual api call is being made to fetch the data from the web.
  private val retrofitService: MarsApiService by lazy {
    retrofit.create(MarsApiService::class.java)
  }

  // Create a repository for the data.
  override val marsPhotosRepository: MarsPhotosRepository by lazy {
    NetworkMarsPhotoRepository(retrofitService)
  }
}