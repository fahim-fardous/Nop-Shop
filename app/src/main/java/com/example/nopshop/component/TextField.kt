package com.example.nopshop.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier, label: String, value: String, onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = "$label :", fontSize = 14.sp, color = Color((0xFF7D828B))
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color(0xFFCDD1D4),
            focusedIndicatorColor = Color(0xFFCDD1D4)
        )
    )
}

@Preview
@Composable
private fun TextFieldPreview() {
    TextFieldCustom(
        value = "",
        onValueChange = {},
        label = "Address",
    )

}