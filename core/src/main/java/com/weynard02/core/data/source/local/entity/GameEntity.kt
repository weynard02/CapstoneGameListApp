package com.weynard02.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "released")
    var released: String? = "",

    @ColumnInfo(name = "playtime")
    var playtime: Int? = 0,

    @ColumnInfo(name = "background_image")
    var backgroundImage: String?,

    @ColumnInfo(name = "rating")
    var rating: Double? = 0.0,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)