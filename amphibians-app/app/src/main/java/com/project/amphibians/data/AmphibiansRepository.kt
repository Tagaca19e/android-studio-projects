package com.project.amphibians.data
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET


interface AmphibiansRepository {
  suspend fun getAmphibianList(): List<Amphibian>
}

class NetworkAmphibiansRepository(
  private val amphibiansApiService: AmphibiansApiService
): AmphibiansRepository {
  override suspend fun getAmphibianList(): List<Amphibian> {
    // TODO(etagaca): This is currently not working.
    // Process: com.project.amphibians, PID: 5704
    // java.lang.IllegalArgumentException: Unable to create converter for java.util.List<com.project.amphibians.data.Amphibian>
    return amphibiansApiService.getAmphibians()
  }
}

/**
 * -------------------------------------------
 * Part of the network package
 * -------------------------------------------
 */

interface AmphibiansApiService {
  @GET("amphibians")
  suspend fun getAmphibians(): List<Amphibian>
}

@Serializable
data class Amphibian(
  val name: String,
  val type: String,
  val description: String,
  @SerializedName(value = "img_src") val imageSrc: String
)

