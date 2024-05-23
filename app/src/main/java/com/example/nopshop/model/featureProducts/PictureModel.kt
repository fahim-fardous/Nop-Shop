package com.example.nopshop.model.featureProducts

data class PictureModel(
    val AlternateText: String,
    val CustomProperties: CustomProperties,
    val FullSizeImageUrl: String,
    val ImageUrl: String,
    val ThumbImageUrl: Any,
    val Title: String
)