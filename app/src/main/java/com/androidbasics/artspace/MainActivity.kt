package com.androidbasics.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidbasics.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        var imageNo by remember {
            mutableIntStateOf(0)
        }

        val images =
            intArrayOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)
        val titles =
            intArrayOf(R.string.title_1, R.string.title_2, R.string.title_3, R.string.title_4)
        val dates =
            intArrayOf(R.string.date_1, R.string.date_2, R.string.date_3, R.string.date_4)

        fun changeImage(amount: Int = 1) {
            imageNo += amount
            if (imageNo > 3) {
                imageNo = 0
            } else if (imageNo < 0) {
                imageNo = 3
            }
        }

        Surface (
            onClick = { changeImage() },
            shadowElevation = 10.dp,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp),
        ) {
            Image(
                painter = painterResource(id = images[imageNo]),
                contentDescription = "Manhattan Bridge",
                modifier = Modifier.padding(10.dp)
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .background(color = Color.LightGray)
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(titles[imageNo]),
                fontSize = 24.sp,
                fontWeight = FontWeight(250)
            )
            Text(
                text = stringResource(dates[imageNo]),
                fontSize = 16.sp,
                fontWeight = FontWeight(750)
            )
        }
        NavigationButtons(changeImage = ::changeImage)
    }
}


@Composable
fun NavigationButtons(changeImage: (amount: Int) -> Unit, modifier: Modifier = Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { changeImage(1) },
            content = { Text(text = "<") },
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { changeImage(-1) },
            content = { Text(text = ">") },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}