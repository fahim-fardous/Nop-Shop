package com.example.nopshop.db.dbmodel.order

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopshop.model.cart.Item

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val token:String,
    val email:String,
    val totalAmount:String,
    val orderId:String,
    val orderDate:String,
    val products:List<Item>
)
