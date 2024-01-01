package com.project.amphibians.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.amphibians.data.Amphibian
// import com.project.amphibians.data.DataSource

@Composable
fun CategoryListScreen(
  uiState: AmphibiansUiState,
  onClickDetailsCard: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (uiState) {
    is AmphibiansUiState.Success -> {
      SuccessScreen(amphibianList = uiState.amphibians)
    }
    is AmphibiansUiState.Error -> {
      ErrorScreen(modifier = Modifier.fillMaxSize())
    }
    is AmphibiansUiState.Loading -> {
      LoadingScreen(modifier = modifier.fillMaxSize())
    }
  }
}

@Composable
fun ErrorScreen(
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
  ) {
    Text(text = "Something went wrong")
  }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
  Text(modifier = modifier, text = "Loading..")
}

@Composable
fun SuccessScreen(
  amphibianList: List<Amphibian>,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
  ) {
    // val items = DataSource.toadItems
    val items = amphibianList
    items.forEach { frog ->
      item {
        DetailsCard(
          name = frog.name,
          imageUrl = frog.imageSrc ?: "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png",
          description = frog.description,
          onClick = { /*TODO*/ })
      }
    }
  }
}

/**
 * Shows details about the frog on the category list.
 */
@Composable
fun DetailsCard(
  name: String,
  imageUrl: String,
  description: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .padding(10.dp)
      .clickable {
        println("Clicked detail card")
      }
  ) {
    Text(text = name)
    Spacer(modifier = Modifier.padding(10.dp))
    AsyncImage(
      model = ImageRequest.Builder(context = LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build(),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(10.dp))
    Text(
      text = description
    )
  }
}
