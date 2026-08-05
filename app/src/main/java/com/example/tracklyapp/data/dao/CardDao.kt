package com.example.tracklyapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tracklyapp.data.entity.Card

@Dao
interface CardDao {
    @Delete
    suspend fun delete(card: Card)

    @Insert
    suspend fun insert(card: Card)

    @Query("SELECT * FROM cards_table")
    suspend fun getAll(): List<Card>

    @Update
    suspend fun update(card: Card)
}