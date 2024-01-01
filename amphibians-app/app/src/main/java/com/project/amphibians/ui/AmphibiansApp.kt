package com.project.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.amphibians.components.AmphibiansAppBar
import com.project.amphibians.components.AmphibiansScreen
import com.project.amphibians.data.Amphibian
import com.project.amphibians.screens.AmphibiansUiState
import com.project.amphibians.screens.AmphibiansViewModel
import com.project.amphibians.screens.CategoryListScreen
import com.project.amphibians.screens.StartScreen

@Composable
fun AmphibiansApp() {
  val navController: NavHostController = rememberNavController()

  // TODO: Create a viewModel here.
  val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)

  // TODO: Set entry for navHost
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentScreen = AmphibiansScreen.valueOf(
    backStackEntry?.destination?.route ?: AmphibiansScreen.Start.name
  )

  // TODO: Add Scaffolding here.
  Scaffold(topBar = {
    AmphibiansAppBar(currentScreen = currentScreen,
      canNavigateBack = navController.previousBackStackEntry != null,
      navigateUp = {
        navController.navigateUp()
      })
  }) { innerPadding -> // TODO: Create a uiState here.

    // TODO: Create a NavHost
    NavHost(
      navController = navController,
      startDestination = AmphibiansScreen.Start.name,
      modifier = Modifier.padding(innerPadding)
    ) {
      composable(AmphibiansScreen.Start.name) { // The actual screen for the start screen
        StartScreen(
          onStartOrderButtonClicked = {
            navController.navigate(AmphibiansScreen.Types.name)
          }, modifier = Modifier.fillMaxSize()
        )
      }

      composable(AmphibiansScreen.Types.name) { // The actual screen for the start screen
        val uiState: AmphibiansUiState = viewModel.amphibiansUiState
        // val amphibianList = uiState.SUCC
        CategoryListScreen(
          uiState = uiState,
          onClickDetailsCard = {
            navController.navigate(AmphibiansScreen.Start.name)
          }, modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}

// <string name="start_app">Learn about amphibians</string>
// <string name="types">Amphibian types</string>
// <string name="details">Details</string>

