package com.example.nopshop.model.category

import com.example.nopshop.db.dbmodel.CategoryEntity

data class Data(
    val CustomProperties: CustomProperties = CustomProperties(),
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory> = emptyList()
)

fun Data.asEntity() = CategoryEntity(
    Id = Id,
    Name = Name,
    SeName = SeName,
    Products = Products
)