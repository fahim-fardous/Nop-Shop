package com.example.nopshop.model.products

data class CategoryBreadcrumb(
    val CustomProperties: CustomPropertiesXXX,
    val HaveSubCategories: Boolean,
    val Id: Int,
    val IncludeInTopMenu: Boolean,
    val Name: String,
    val NumberOfProducts: Any,
    val Route: Any,
    val SeName: String,
    val SubCategories: List<Any>
)