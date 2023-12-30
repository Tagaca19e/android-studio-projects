package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

interface MarsPhotosRepository {
  suspend fun getMarsphotos(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository(
  private val marsApiService: MarsApiService
): MarsPhotosRepository {
  override suspend fun getMarsphotos(): List<MarsPhoto> {
    return marsApiService.getPhotos()
  }
}