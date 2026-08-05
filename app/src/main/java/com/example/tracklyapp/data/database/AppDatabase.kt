package com.example.tracklyapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tracklyapp.data.dao.ActivityDao
import com.example.tracklyapp.data.dao.CardDao
import com.example.tracklyapp.data.entity.Activity
import com.example.tracklyapp.data.entity.Card

@Database(entities = [Card::class, Activity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun cardDao(): CardDao
}