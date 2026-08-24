package com.example.tracklyapp.data.repository

import com.example.tracklyapp.data.entity.Activity
import com.example.tracklyapp.data.entity.Card

interface TracklyRepository {
    suspend fun add(activity: Activity)
    suspend fun getAllActivity(id: Int): List<Activity>

    suspend fun delete(card: Card)
    suspend fun insert(card: Card)
    suspend fun update(card: Card)
    suspend fun getAllCards(): List<Card>

    suspend fun getTotalDuration(cardId: Int, startDate: String): Int

    suspend fun getTotalDurationBetween(cardId: Int, startDate: String, endDate: String): Int
}