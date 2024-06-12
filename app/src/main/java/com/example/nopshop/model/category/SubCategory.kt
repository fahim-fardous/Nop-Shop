package com.example.nopshop.model.category

data class SubCategory(
    val CustomProperties: CustomProperties,
    val Id: Int,
    val Name: String,
    val Products: List<Any>,
    val SeName: String,
    val SubCategories: List<Any>
)