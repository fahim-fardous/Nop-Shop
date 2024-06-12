package com.example.nopshop.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nopshop.R

@Composable
fun PaymentOptionCard() {
    var isCheckedCheck by remember {
        mutableStateOf(true)
    }

    var isCheckedCard by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp, color = Color(
                        0xFFD5D5DA
                    )
                )
        ) {
            Row(
                modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                CircleCheckBox(isChecked = isCheckedCheck, onCheckedChange = {
                    isCheckedCheck = it
                    isCheckedCard = !it
                })
                Image(
                    modifier = Modifier
                        .size(width = 35.dp, height = 25.dp)
                        .padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Check / Money Order",
                    color = colorResource(id = R.color.textColor)
                )

            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp, color = Color(
                        0xFFD5D5DA
                    )
                )
        ) {
            Row(
                modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                CircleCheckBox(isChecked = isCheckedCard, onCheckedChange = {
                    isCheckedCard = it
                    isCheckedCheck = !it
                })
                Image(
                    modifier = Modifier
                        .size(width = 35.dp, height = 25.dp)
                        .padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Credit Card",
                    color = colorResource(id = R.color.textColor)
                )

            }
        }

    }
}

@Preview
@Composable
private fun PaymentOptionCardPreview() {
    Surface {
        PaymentOptionCard()
    }

}