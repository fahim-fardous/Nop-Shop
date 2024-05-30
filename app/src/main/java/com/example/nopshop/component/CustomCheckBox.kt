package com.example.nopshop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckBox(
    isChecked: Boolean,
    onCheckChange: (Boolean) -> Unit = {}
) {
    Box(modifier = Modifier
        .width(20.dp)
        .height(20.dp)
        .border(1.dp, Color(0xFFD5D5DA), shape = RoundedCornerShape(2.dp))
        .padding(horizontal = 2.dp, vertical = 2.dp)
        .clickable {
            onCheckChange(!isChecked)
        }
    )
    {
        if(isChecked){
            Icon(
                Icons.Filled.Check,
                contentDescription = "Check"
            )
        }
    }
}

@Preview
@Composable
private fun CustomCheckBoxPreview() {
    Surface(modifier = Modifier.background(Color.White)) {
        CustomCheckBox(
            isChecked = true,
            onCheckChange = {}
        )
    }
}