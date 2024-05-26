package com.example.nopshop.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PictureModel(
    val AlternateText: String,
    val CustomProperties: CustomProperties,
    val FullSizeImageUrl: String,
    val ImageUrl: String,
    val ThumbImageUrl: @RawValue Any,
    val Title: String
) : Parcelable