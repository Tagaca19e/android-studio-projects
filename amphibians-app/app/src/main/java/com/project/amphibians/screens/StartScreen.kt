package com.project.amphibians.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.amphibians.R

@Composable
fun StartScreen(
  onStartOrderButtonClicked: () -> Unit, modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Button(
      onClick = onStartOrderButtonClicked,
      colors = ButtonDefaults.buttonColors(
        containerColor = Color.Gray,
        contentColor = Color.White,
      ), modifier = Modifier.widthIn(min = 250.dp)
    ) {
      Text(stringResource(id = R.string.start_app))
    }
  }
}

@Preview(showBackground = true)
@Composable
fun StartAppPreview() {
  StartScreen(
    onStartOrderButtonClicked = { /*TODO*/ }, modifier = Modifier
      .padding(10.dp)
      .fillMaxSize()
  )
}