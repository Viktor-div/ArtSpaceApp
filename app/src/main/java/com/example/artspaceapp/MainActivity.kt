package com.example.artspaceapp


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}


@Composable
fun ArtSpace() {
    var currentImage by remember { mutableStateOf(1) }
    val imageResource =
        when (currentImage) {
        1 -> R.drawable.image
        2 -> R.drawable.image2
        else -> R.drawable.image3
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(

            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp)

        )

        Subscription(
            text = "Queenstown, New Zealand", author = "Lachlan Dempsey", modifier = Modifier
        )


      Row(modifier = Modifier
          .padding(top = 100.dp)

          ) {
          EditButton(
              onClick = {--currentImage},
              buttonText = R.string.previous
          )
          Spacer(
              modifier = Modifier.size(50.dp)
          )
          EditButton(
              onClick = {++currentImage},
              buttonText = R.string.next
          )
      }
    }
}
@Composable
fun EditButton(onClick: () -> Unit, @StringRes buttonText: Int, modifier: Modifier = Modifier){
     Button(
            onClick = onClick,
            modifier = Modifier
                .size(150.dp, 50.dp)



        )
     {
         Text(text = stringResource(id = buttonText))
     }

    }


@Composable
fun Subscription(text: String, author: String, modifier: Modifier = Modifier) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = Color(126, 122, 137))


    ) {

        Text(
            text = text,
            fontSize = 24.sp,
            color = Color(65, 53, 93),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold

        )
        Text(
            text = author,
            fontSize = 20.sp,
            color = Color(50, 44, 64),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpace()
    }
}