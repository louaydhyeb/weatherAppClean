package com.ldcoding.weatherapp.presentation.loading_weather_screen.components

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.ldcoding.weatherapp.presentation.loading_weather_screen.WeatherViewModel


@Composable
fun WeatherListScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val myHandler = Handler(Looper.getMainLooper())
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.listCities) { city ->
                    CoinListItem(
                        weather = city,
                        onItemClick = {
                            Toast.makeText(context, city.name, Toast.LENGTH_SHORT).show()
                        }
                    )
                    Divider()
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Bienvenue Dans Weather App")
            Spacer(modifier = Modifier.height(16.dp))

            val progressBarAlpha = if (state.isLoading) 1f else 0f
            val buttonAlpha = if (state.isLoading) 0f else 1f

            CustomProgressBar(
                Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .height(14.dp)
                    .alpha(progressBarAlpha),
                300.dp,
                Color.Gray,
                Brush.horizontalGradient(listOf(Color(0xffFD7D20), Color(0xffFBE41A))),
                100,
                true
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(buttonAlpha),
                onClick = { viewModel.relaunch()},
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                )
            ) {
                // Inner content including an icon and a text label
                Text("Recommencer", color = Color.White)
            }
        }
        ToastDemo(myHandler, state.isLoading)
    }
}