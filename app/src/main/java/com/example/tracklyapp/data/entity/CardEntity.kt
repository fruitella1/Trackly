package com.example.tracklyapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val timeScore: Int? = null
)