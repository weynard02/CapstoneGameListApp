package com.weynard02.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("playtime")
    val playtime: Int,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("rating")
    val rating: Double,
)