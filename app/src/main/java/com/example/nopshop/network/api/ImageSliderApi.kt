package com.example.nopshop.network.api

import com.example.nopshop.model.slider.ImageSliderItem
import retrofit2.Response
import retrofit2.http.GET

interface ImageSliderApi {
    @GET("slider/homepageslider")
    suspend fun getSliderImages(): Response<ImageSliderItem>
}