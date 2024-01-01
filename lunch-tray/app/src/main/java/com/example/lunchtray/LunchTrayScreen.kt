/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.compose.material3.Icon
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.ui.OrderViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuPreview
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum
// In this section, you create an enum class to hold constants for each of the following Lunch Tray app screens:
//
// Start
// Entree menu
// Side dish menu
// Accompaniment menu
// Checkout

// TODO: Add enum for screens
enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.start_order),
    Entree(title = R.string.choose_entree),
    Side(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel, navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
}

// TODO: Add topbar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        })
}

@Composable
fun LunchTrayApp() {
    val navController: NavHostController = rememberNavController()

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    // TODO: Set entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    // TODO: Add topbar to the app
    Scaffold(topBar = {
        LunchTrayAppBar(currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = {
                navController.navigateUp()
            })
    }) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // TODO: Add page transitions
            // The Start Order button on the Start screen navigates to the Entree menu screen.
            // The Next button on the Entree menu screen navigates to the Side dish menu screen.
            // The Next button on the Side dish menu screen navigates to the Accompaniment menu screen.
            // The Next button on the Accompaniment menu screen navigates to the Checkout screen.
            // The Submit button on the Checkout screen navigates to the Start screen.
            // The Cancel button on any screen navigates back to the Start screen.

            composable(LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.Entree.name)
                    }, modifier = Modifier.fillMaxSize()
                )
            }

            composable(LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(options = DataSource.entreeMenuItems, onCancelButtonClicked = {
                    cancelOrderAndNavigateToStart(
                        viewModel = viewModel, navController = navController
                    )
                }, onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.Side.name)
                }, onSelectionChanged = {
                    viewModel.updateEntree(it)
                })
            }

            // The Next button on the Side dish menu screen navigates to the Accompaniment menu screen.
            // The Next button on the Accompaniment menu screen navigates to the Checkout screen.
            // The Submit button on the Checkout screen navigates to the Start screen.
            // The Cancel button on any screen navigates back to the Start screen.

            composable(LunchTrayScreen.Side.name) {
                SideDishMenuScreen(options = DataSource.sideDishMenuItems, onCancelButtonClicked = {
                    cancelOrderAndNavigateToStart(viewModel, navController)
                }, onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.Accompaniment.name)
                }, onSelectionChanged = {
                    viewModel.updateSideDish(it)
                })
            }

            composable(LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Checkout.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateAccompaniment(it)
                    }
                )
            }

            composable(LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        // NOTE: We could have just started from the beggining without having
                        // to reset our order.
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    })
            }
        }
    }
}
