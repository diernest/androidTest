package com.example.ejercicio.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ejercicio.R

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier.fillMaxWidth().height(110.dp),
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.logo_marca),
            contentDescription = stringResource(R.string.emovie),
            modifier = Modifier.padding(vertical = 30.dp)
        )
    }
}