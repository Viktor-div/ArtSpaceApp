package com.example.artspaceapp



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
    var currentIndex by remember { mutableStateOf(0) }
    if (currentIndex == 3) currentIndex = 0
    if (currentIndex == -1) currentIndex = 2


    val imageResource: Int
    val author: Int
    val place: Int


    when (currentIndex) {
        0 -> {
            imageResource = R.drawable.image
            author = R.string.author1
            place = R.string.place1
        }

        1 -> {
            imageResource = R.drawable.image2
            author = R.string.author2
            place = R.string.place2
        }

        else -> {
            imageResource = R.drawable.image3
            author = R.string.author3
            place = R.string.place3
        }
    }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
            shape = RectangleShape,
        ) {

            Image(

                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .size(400.dp)

            )

        }
        BoxWithConstraints() {
            if(maxWidth < 413.dp) {
                Spacer(Modifier.height(76.dp))
            } else if (maxWidth < 1281.dp) {
                Spacer(Modifier.height(20.dp))
            }
        }
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    )
                    { append(stringResource(id = place)) }

                    append("\t\n")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 16.sp)
                    )
                    { append  (stringResource(id = author)) }
                },
                modifier = Modifier
                    .padding(16.dp)

            )



            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                EditButton(
                    onClick = { currentIndex-- },
                    buttonText = R.string.previous
                )
                Spacer(
                    modifier = Modifier.size(50.dp)
                )
                EditButton(
                    onClick = { currentIndex++ },
                    buttonText = R.string.next
                )
            }
        }
    }


    @Composable
    fun EditButton(
        onClick: () -> Unit, @StringRes buttonText: Int, modifier: Modifier = Modifier) {
        Button(
            onClick = onClick,
            modifier = modifier.width(140.dp),


        )
        {
            Text(text = stringResource(id = buttonText))
        }
    }

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpace()
    }
}