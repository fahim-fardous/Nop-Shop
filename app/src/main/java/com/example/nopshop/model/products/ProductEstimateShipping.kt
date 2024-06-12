package com.example.nopshop.model.products

data class ProductEstimateShipping(
    val AvailableCountries: List<Any>,
    val AvailableStates: List<Any>,
    val City: Any,
    val CountryId: Any,
    val CustomProperties: CustomPropertiesXXX,
    val Enabled: Boolean,
    val ProductId: Int,
    val RequestDelay: Int,
    val StateProvinceId: Any,
    val UseCity: Boolean,
    val ZipPostalCode: Any
)