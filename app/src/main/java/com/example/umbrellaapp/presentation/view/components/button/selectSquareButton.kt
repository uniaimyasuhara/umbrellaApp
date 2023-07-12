package com.example.umbrellaapp.presentation.view.components.button

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun selectSquareButton(text: String,icon: Painter?, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(50)
    ) {
        if (icon != null){
            Icon(painter = icon, contentDescription = "icon")
        }
        Text(text = text)
    }
}