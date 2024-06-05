package com.example.nopshop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Title(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF0BF7EB),
                        Color(0xFF07C5FB),
                        Color(0xFF088DF9),
                    )
                ), shape = RoundedCornerShape(4.dp)
            )

    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            text = label,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun TitlePreview() {
    Surface {
        Title(label = "Wow!")
    }
}