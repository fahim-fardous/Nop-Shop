package com.example.nopshop.model.common

import com.example.nopshop.db.dbmodel.CategoryEntity
import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.model.category.Data

sealed class BaseCategoryItem {
    data class CategoryApi(val categoryWiseProductsItem: Data):BaseCategoryItem()
    data class CategoryDb(val categoryWiseProductsItemFromDb:CategoryEntity):BaseCategoryItem()
}