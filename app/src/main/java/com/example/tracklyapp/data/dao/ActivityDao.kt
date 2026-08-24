package com.example.tracklyapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tracklyapp.data.entity.Activity

@Dao
interface ActivityDao {
    @Insert
    suspend fun add(activity: Activity)

    @Query("Select* From activity_table Where cardId = :id")
    suspend fun getAll(id: Int): List<Activity>

    @Query("SELECT TOTAL(duration) FROM activity_table WHERE cardId = :cardId AND date>=:startDate")
    suspend fun getTotalDuration(cardId: Int, startDate: String): Int

    @Query("SELECT TOTAL(duration) FROM activity_table WHERE cardId =:cardId And date>=:startDate AND date<=:endDate")
    suspend fun getTotalDurationBetween(cardId: Int, startDate: String, endDate: String): Int
}