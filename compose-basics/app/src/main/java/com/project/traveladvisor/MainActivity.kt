package com.project.traveladvisor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.traveladvisor.ui.theme.TravelAdvisorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TravelAdvisorTheme { // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
          // ModalNavigationDemo()
          // ModalNavigationDrawerSample()
          // GreetingText("Happy Birthday Sam!", from = "From Eidmone")
          GreetingImage(stringResource(R.string.happy_birthday_text),
            stringResource(R.string.signature_text)
          )
        }
      }
    }
  }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
  Column(
    verticalArrangement = Arrangement.Center,
    modifier = modifier.padding(10.dp)
  ) {
    Text(
      text = message,
      fontSize = 100.sp,
      lineHeight = 116.sp,
      textAlign = TextAlign.Center,
    )
    Text(
      text = from,
      fontSize = 36.sp,
      modifier = Modifier
        .padding(16.dp)
        .align(alignment = Alignment.CenterHorizontally)
    )
  }
}

// @Preview(
//   showBackground = true,
// )
// @Composable
// fun GreetingPreview() {
//   TravelAdvisorTheme {
//     GreetingText("Happy birthday sam!", from = "From Emma")
//   }
// }

/**
 * ------------------
 * Artist composable
 * ------------------
 */
@Composable
fun ArtistCarRow() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .clip(shape = RoundedCornerShape(20.dp))
      .background(color = Color.LightGray)
      .border(5.dp, Color.Black, RoundedCornerShape(20.dp))
  ) {
    Image(
      painter = painterResource(id = R.drawable.hero),
      contentDescription = "Hero image",
      modifier = Modifier
        .padding(10.dp)
        .clip(CircleShape)
    )
    Column(modifier = Modifier.padding(20.dp)) {
      Text("Alfred Sisley")
      Text("3 minutes ago")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ArtistCardRowPreview() {
  ArtistCarRow()
}

@Composable
fun ArtistCheckMark() {
  Box() {
    Image(
      painter = painterResource(R.drawable.hero),
      contentDescription = "Sample image",
      modifier = Modifier.clip(CircleShape)
    )
    Icon(
      Icons.Filled.Check,
      contentDescription = "Sample icon",
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .clip(shape = CircleShape)
        .background(Color.White)
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ArtistCheckMarkPreview() {
  ArtistCheckMark()
}

/**
 * Card arrangement
 */
@Composable
fun ArtistCardArrangement() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.End,
    modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp)
  ) {
    Image(
      painter = painterResource(R.drawable.hero),
      contentDescription = "Some image",
      modifier = Modifier.clip(CircleShape)
    )
    Column(modifier = Modifier.padding(20.dp)) {
      Text("Alfred Sisley")
      Text("3 minutes ago")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ArtistCardArrangementPreview() {
  ArtistCardArrangement()
}

@Composable
fun ImageHolder() {
  Image(
    painter = painterResource(R.drawable.sunset),
    contentDescription = "Sunset",
    modifier = Modifier
      .padding(20.dp, 0.dp, 20.dp, 20.dp)
      .clip(RoundedCornerShape(16.dp))
      .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(16.dp),)
  )
}

@Composable
fun ArtistCardModifiers() {
  val padding = 16.dp
  Column(
    modifier = Modifier
      .clickable {  }
  ) {
    ArtistCardArrangement()
    Spacer(Modifier.size(padding))
    ImageHolder()
  }
}

@Preview(showBackground = true)
@Composable
fun ArtistCardModifiersPreview() {
  ArtistCardModifiers()
}

/**
 * ---------------------
 * Modal Navigation Demo
 * ---------------------
 */

@Composable
fun ModalNavigationDrawerSample() {
  val drawerState = rememberDrawerState(DrawerValue.Open)
  val scope = rememberCoroutineScope()
  // icons to mimic drawer destinations
  val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
  val selectedItem = remember { mutableStateOf(items[0]) }
  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        items.forEach { item ->
          NavigationDrawerItem(
            icon = { Icon(item, contentDescription = null) },
            label = { Text(item.name) },
            selected = item == selectedItem.value,
            onClick = {
              scope.launch { drawerState.close() }
              selectedItem.value = item
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
          )
        }
      }
    },
    content = {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
        Spacer(Modifier.height(20.dp))
        Button(onClick = { scope.launch { drawerState.open() } }) {
          Text("Click to open")
        }
      }
    }
  )
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
  val image = painterResource(R.drawable.androidparty)
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Image(
      painter = image,
      contentDescription = null,
      modifier = Modifier.fillMaxSize(),
      alpha = 0.5F
    )
    GreetingText(
      message = message,
      from = from,
      modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    )
  }
}
