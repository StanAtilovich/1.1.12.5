package ru.stan.a11125.data.DTO

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

    @JsonClass(generateAdapter = true)
    data class CharacterDto(
        @Json(name = "id")
        val id: Int,
        @Json(name = "character")
        val name: String,
        @Json(name="hogwartsHouse")
        val hogwartsHouse: String,
        @Json(name = "image")
        val imageUrl: String
    )