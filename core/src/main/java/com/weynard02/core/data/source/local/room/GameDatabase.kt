package com.weynard02.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weynard02.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 5, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

}