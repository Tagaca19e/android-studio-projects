package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

/**
 * Mars repository for the appContainer.
 */
interface MarsPhotosRepository {
  suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository(
  private val marsApiService: MarsApiService
): MarsPhotosRepository {
  override suspend fun getMarsPhotos(): List<MarsPhoto> {
    return marsApiService.getPhotos()
  }
}