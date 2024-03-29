package com.example.appone.data.database.pray.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appone.data.database.pray.model.PrayEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PrayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: List<PrayEntity>)

    @Query("SELECT * FROM pray WHERE city = :city AND time >= :from AND time <= :to")
    fun getPraySchedule(city: String, from: Long, to: Long): Flowable<List<PrayEntity>>
}