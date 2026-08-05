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
}