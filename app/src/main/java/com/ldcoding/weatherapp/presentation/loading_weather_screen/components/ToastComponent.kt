package com.ldcoding.weatherapp.presentation.loading_weather_screen.components

import android.os.Handler
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastDemo(myHandler: Handler, loading: Boolean) {
    val context = LocalContext.current
    Column(
        content = {

            val listStrings = listOf("Nous téléchargeons les données…",
                " C’est presque fini…",
                "Plus que quelques secondes avant d’avoir le résultat… !")
            var runnable: Runnable? = null
            runnable = Runnable {
                try {
                    Toast.makeText(
                        context,
                        listStrings.random(),
                        Toast.LENGTH_SHORT
                    ).show()
                } finally {
                    runnable?.let { myHandler.postDelayed(it, 6000) }
                }
            }
            runnable.run()

            if (!loading) {
                myHandler.removeCallbacks(runnable)
            }
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}
