package com.example.sports.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sports.R
import com.example.sports.data.LocalSportsDataProvider
import com.example.sports.model.Sport

@Composable
fun SportsDetail(
  selectedSport: Sport,
  onBackPressed: () -> Unit,
  contentPadding: PaddingValues,
  modifier: Modifier = Modifier
) {
  BackHandler {
    onBackPressed()
  }
  val scrollState = rememberScrollState()
  val layoutDirection = LocalLayoutDirection.current
  Box(
    modifier = modifier
      .verticalScroll(state = scrollState)
      .padding(top = contentPadding.calculateTopPadding())
  ) {
    Column(
      modifier = Modifier
        .padding(
          bottom = contentPadding.calculateTopPadding(),
          start = contentPadding.calculateStartPadding(layoutDirection),
          end = contentPadding.calculateEndPadding(layoutDirection)
        )
    ) {
      Box {
        Box {
          Image(
            painter = painterResource(selectedSport.sportsImageBanner),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
          )
        }
        Column(
          Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                0f,
                400f
              )
            )
        ) {
          Text(
            text = stringResource(selectedSport.titleResourceId),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            modifier = Modifier
              .padding(horizontal = dimensionResource(R.dimen.padding_small))
          )
          Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
          ) {
            Text(
              text = pluralStringResource(
                R.plurals.player_count_caption,
                selectedSport.playerCount,
                selectedSport.playerCount
              ),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.inverseOnSurface,
            )
            Spacer(Modifier.weight(1f))
            Text(
              text = stringResource(R.string.olympic_caption),
              style = MaterialTheme.typography.labelMedium,
              color = MaterialTheme.colorScheme.inverseOnSurface,
            )
          }
        }
      }
      Text(
        text = stringResource(selectedSport.sportDetails),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(
          vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
          horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
        )
      )
    }
  }
}

// selectedSport: Sport,
// onBackPressed: () -> Unit,
// contentPadding: PaddingValues,
// modifier: Modifier = Modifier


// SportsList
// sports: List<Sport>,
// onClick: (Sport) -> Unit,
// modifier: Modifier = Modifier,
// contentPadding: PaddingValues = PaddingValues(0.dp),
@Composable
fun SportsListAndDetails(
  sports: List<Sport>,
  onClick: (Sport) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues,
  currentSport: Sport,
  onBackPressed: () -> Unit,
  innerPadding: PaddingValues,
) {
  Row(
    modifier = modifier
  ) {
    SportsList(
      sports,
      onClick,
      modifier = Modifier
        .weight(2f)
        .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
      contentPadding
    )

    SportsDetail(
      selectedSport = currentSport,
      onBackPressed = {
        onBackPressed()
      },
      modifier = Modifier.weight(3f),
      contentPadding = contentPadding
    )
  }
}

@Preview(showBackground = true)
@Composable
fun SportsListAndDetailsPreview() {
  SportsListAndDetails(
    sports = LocalSportsDataProvider.getSportsData(),
    onClick = {},
    contentPadding = PaddingValues(1.dp),
    currentSport = LocalSportsDataProvider.defaultSport,
    onBackPressed = {},
    innerPadding = PaddingValues(1.dp),
  )
}
