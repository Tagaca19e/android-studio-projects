package com.project.amphibians.screens

import android.text.Editable.Factory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.project.amphibians.data.Amphibian
import com.project.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.project.amphibians.AmphibiansApplication

sealed interface AmphibiansUiState {
  data class Success(val amphibians: List<Amphibian>): AmphibiansUiState
  data object Error: AmphibiansUiState
  data object Loading: AmphibiansUiState
}

class AmphibiansViewModel (
  private val amphibiansRepository: AmphibiansRepository
) : ViewModel() {

  var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
    private set

  init {
    // Call function to populate the state as soon as possible.
    getAmphibiansData()
  }

  fun getAmphibiansData() {
    viewModelScope.launch {
      amphibiansUiState = try {
        AmphibiansUiState.Success(
          amphibiansRepository.getAmphibianList()
        )
      } catch (e: IOException) {
        AmphibiansUiState.Error
      } catch (e: HttpException) {
        AmphibiansUiState.Error
      }
    }
  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = (this[APPLICATION_KEY] as AmphibiansApplication)
        val amphibiansRepository = application.container.amphibiansRepository
        AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
      }
    }
  }
}
