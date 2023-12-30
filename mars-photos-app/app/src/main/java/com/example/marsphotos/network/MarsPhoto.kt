package com.example.marsphotos.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Object that represents the JSON returned values.
 */
@Serializable
data class MarsPhoto(
  val id: String,
  @SerialName(value = "img_src") val imgSrc: String,
)