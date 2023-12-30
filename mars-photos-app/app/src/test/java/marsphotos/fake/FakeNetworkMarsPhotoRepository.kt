package marsphotos.fake

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto

/**
 * Fake repository for testing.
 */
class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
  override suspend fun getMarsPhotos(): List<MarsPhoto> {
    return FakeDataSource.photosList
  }
}