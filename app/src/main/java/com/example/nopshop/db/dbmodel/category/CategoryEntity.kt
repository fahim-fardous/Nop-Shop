package com.example.nopshop.db.dbmodel.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopshop.model.category.CustomProperties
import com.example.nopshop.model.category.Data
import com.example.nopshop.model.category.Product
import com.example.nopshop.model.category.SubCategory


@Entity(tableName = "category")
data class CategoryEntity(
    val CustomProperties: CustomProperties = CustomProperties(),
    @PrimaryKey
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory> = emptyList()
)

fun CategoryEntity.toData(): Data {
    return Data(
        CustomProperties = this.CustomProperties,
        Id = this.Id,
        Name = this.Name,
        Products = this.Products,
        SeName = this.SeName,
        SubCategories = this.SubCategories
    )
}



