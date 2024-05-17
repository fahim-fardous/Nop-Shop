package com.example.nopshop.model

data class CategoryItem(
    val id: Long,
    val categoryImage: Int,
    val categoryName: String,
    val productList: MutableList<ProductItem> = mutableListOf()
)
