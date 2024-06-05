package com.example.nopshop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TotalCard() {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { /*TODO*/ }) {
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Sub-Total:",
                color = Color(0xFF001844),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$5,355.00", color = Color(0xFF001844), fontWeight = FontWeight.Bold
            )
        }
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Shipping:",
                color = Color(0xFF001844),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$0.00", color = Color(0xFF001844), fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 16.dp
            )
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Tax:",
                color = Color(0xFF001844),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$0.00", color = Color(0xFF001844), fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
            )
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Total:",
                color = Color(0xFF001844),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$5,355.00", color = Color(0xFF001844), fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF0AE5F2), Color(0xFF088EFA)
                        )
                    )
                )
        ) {
            Text(
                text = "Confirm",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun TotalCardPreview() {
    Surface {
        TotalCard()
    }

}