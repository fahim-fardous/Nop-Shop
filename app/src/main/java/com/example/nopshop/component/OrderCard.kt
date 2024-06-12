package com.example.nopshop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nopshop.db.dbmodel.order.OrderEntity
import com.example.nopshop.model.products.Data
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun OrderCard(
    modifier: Modifier = Modifier, order: OrderEntity
) {
    OutlinedCard(onClick = { /*TODO*/ }, elevation = CardDefaults.outlinedCardElevation()) {
        Column(modifier = Modifier) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8E9F2))
            ) {
                Text(
                    text = "Order Details",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Row(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp)) {
                Text(
                    text = "Order Number:",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = order.id.toString(),
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                )
            }
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    text = "Order Status:",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "In Progress",
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                )
            }
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    text = "Order Date:",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = order.orderDate,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                )
            }
            Row(modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 16.dp)) {
                Text(
                    text = "Order Total:",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = order.totalAmount,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                )
            }
        }

    }
}

@Preview
@Composable
private fun OrderCardPreview() {
    Column(modifier = Modifier
        .padding(16.dp, 16.dp)
        .background(Color.White)) {
        OrderCard(
            order = OrderEntity(
                id = 0,
                token = "",
                email = "",
                totalAmount = "$1983.00",
                orderId = "8df98fdf9usfjdklfsdlfjlfjlfjlfdjljfldfjdlsfjlfjfjdsljljfljfljfldsjlf",
                orderDate = Date().toString(),
                products = emptyList()
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OrderCard(
            order = OrderEntity(
                id = 0,
                token = "",
                email = "",
                totalAmount = "$1983.00",
                orderId = "8df98fdf9usfjdklfsdlfjlfjlfjlfdjljfldfjdlsfjlfjfjdsljljfljfljfldsjlf",
                orderDate = Date().toString(),
                products = emptyList()
            )
        )
    }
}