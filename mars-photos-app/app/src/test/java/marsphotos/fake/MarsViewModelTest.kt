package marsphotos.fake

import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.marsphotos.data.NetworkMarsPhotoRepository
import kotlinx.coroutines.test.TestDispatcher
import marsphotos.rules.TestDispatcherRule
import org.junit.Rule


class MarsViewModelTest {

  @get:Rule
   val testDispatcher = TestDispatcherRule()

  @Test
  fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
    val repository = NetworkMarsPhotoRepository(
      marsApiService =  FakeMarsApiService()
    )
    assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
  }

  @Test
  fun marsViewModel_getMarsPhotos_verifyMarsUiState() {
    runTest {
      // Create a  viewModel.
      val marsViewModel = MarsViewModel(
        marsPhotosRepository = FakeNetworkMarsPhotosRepository()
      )

      assertEquals(
        MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars " +
                "photos retrieved"),
        marsViewModel.marsUiState
      )
    }
  }
}