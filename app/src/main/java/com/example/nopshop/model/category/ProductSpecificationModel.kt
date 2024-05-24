package com.example.nopshop.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductSpecificationModel(
    val CustomProperties: CustomProperties
):Parcelable