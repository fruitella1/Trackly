package com.example.tracklyapp.data.repository

import com.example.tracklyapp.data.dao.ActivityDao
import com.example.tracklyapp.data.dao.CardDao
import com.example.tracklyapp.data.entity.Activity
import com.example.tracklyapp.data.entity.Card

class TracklyRepositoryImpl(private val cardDao: CardDao, private val activityDao: ActivityDao) :
    TracklyRepository {

    override suspend fun add(activity: Activity) {
        return activityDao.add(activity)
    }

    override suspend fun getAllActivity(id: Int): List<Activity> {
        return activityDao.getAll(id)
    }

    override suspend fun delete(card: Card) {
        return cardDao.delete(card)
    }

    override suspend fun insert(card: Card) {
        return cardDao.insert(card)
    }

    override suspend fun update(card: Card) {
        return cardDao.update(card)
    }

    override suspend fun getAllCards(): List<Card> {
        return cardDao.getAll()
    }

    override suspend fun getTotalDuration(cardId: Int, startDate: String): Int {
        return activityDao.getTotalDuration(cardId, startDate)
    }

    override suspend fun getTotalDurationBetween(
        cardId: Int,
        startDate: String,
        endDate: String
    ): Int {
        return activityDao.getTotalDurationBetween(cardId, startDate, endDate)
    }
}