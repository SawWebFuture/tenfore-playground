package com.scottwilliams.tenfore_golf.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.scottwilliams.tenfore_golf.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // TEMP: Use a guaranteed-small drawable to avoid image decode crashes
        Image(
            painter = painterResource(R.drawable.tenfore_03),
            contentDescription = null,
            modifier = Modifier.size(400.dp),
            contentScale = ContentScale.Fit
        )
    }
}