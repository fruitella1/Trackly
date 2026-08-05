package com.example.tracklyapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_table")
data class Activity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val duration: Int,
    val cardId: Int,
    val note: String
)