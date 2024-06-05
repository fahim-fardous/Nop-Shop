package com.example.nopshop.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleCheckBox(
    isChecked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .size(width = 20.dp, height = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, color = Color(0xFFD5D5DA), shape = RoundedCornerShape(10.dp))
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "Checked",
                modifier = Modifier.size(15.dp)
            )
        }
    }

}

@Preview
@Composable
private fun CircleCheckBoxPreview() {
    Surface {
        CircleCheckBox()
    }

}