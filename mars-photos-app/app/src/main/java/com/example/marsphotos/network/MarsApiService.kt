package com.example.marsphotos.network
import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

// private const val BASE_URL =
//   "https://android-kotlin-fun-mars-server.appspot.com"
//
// private val retrofit = Retrofit.Builder()
//   .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//   .baseUrl(BASE_URL)
//   .build()

// You could also do it like this.
// private val retrofitsample = Retrofit.Builder()
//   .baseUrl(BASE_URL)
//   .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//   .build()
//   .create(MarsApiService::class.java)

interface MarsApiService {

  @GET("photos")
  suspend fun getPhotos(): List<MarsPhoto>
  // Get the list of photos with the photos appended to the base url.
}

// object MarsApi {
//   val retrofitService : MarsApiService by lazy {
//     retrofit.create(MarsApiService::class.java)
//   }
// }