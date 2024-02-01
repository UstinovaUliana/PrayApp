package com.example.appone.data.database.pray

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appone.data.database.pray.dao.PrayDao
import com.example.appone.data.database.pray.model.PrayEntity

@Database(
    version = 1,
    entities = [PrayEntity::class],
    exportSchema = true
)
abstract class MyRoomDatabase: RoomDatabase() {
    abstract fun prayDao(): PrayDao
}