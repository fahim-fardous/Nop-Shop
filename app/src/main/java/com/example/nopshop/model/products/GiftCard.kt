package com.example.nopshop.model.products

data class GiftCard(
    val CustomProperties: CustomPropertiesXXX,
    val GiftCardType: Int,
    val IsGiftCard: Boolean,
    val Message: Any,
    val RecipientEmail: Any,
    val RecipientName: Any,
    val SenderEmail: Any,
    val SenderName: Any
)