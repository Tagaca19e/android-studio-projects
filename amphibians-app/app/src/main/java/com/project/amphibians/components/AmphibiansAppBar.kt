package com.project.amphibians.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.project.amphibians.R

enum class AmphibiansScreen(@StringRes val title: Int) {
  Start(title = R.string.start_app),
  Types(title = R.string.types),
  Details(title = R.string.details)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansAppBar(
  currentScreen: AmphibiansScreen,
  modifier: Modifier = Modifier,
  canNavigateBack: Boolean,
  navigateUp: () -> Unit,
) {
  TopAppBar(
    title = { Text(stringResource(id = currentScreen.title)) },
    colors =  TopAppBarDefaults.mediumTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
    modifier = modifier,
    navigationIcon = {
      // Check if we can navigate back here
      if (canNavigateBack) {
        IconButton(onClick = { navigateUp() }) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_button)
          )
        }
      }
    }
  )
}