package com.example.audioboom.models.recommended

data class BodyRecommended(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val title: String,
    val description: String,
    val formattedDescription: String,
    val parentChannelID: Long? = null,
    val urls: Urls,
    val recommendation: Recommendation
)

data class Recommendation (
    val position: Long,
    val categoryID: Long,
    val defaultFollow: Boolean
)

data class Urls (
    val webURL: String,
    val logo_image: Image,
    val banner_image: Image
)

data class Image (
    val original: String? = null
)

