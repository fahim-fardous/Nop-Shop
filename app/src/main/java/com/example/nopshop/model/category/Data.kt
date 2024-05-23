package com.example.nopshop.model.category

data class Data(
    val CustomProperties: CustomProperties,
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory>
)