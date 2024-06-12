package com.example.nopshop.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductSpecificationModel(
    val CustomProperties: CustomProperties,
) : Parcelable