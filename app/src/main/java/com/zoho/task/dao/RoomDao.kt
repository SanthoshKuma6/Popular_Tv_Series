package com.zoho.task.dao

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zoho.task.network.RoomInterface

abstract class RoomDao : RoomDatabase() {
    abstract fun roomInterface(): RoomInterface

    companion object {
        private var Instant: RoomDao? = null
        fun getDatabase(context: Context): RoomDao {
            return Instant ?: synchronized(this) {
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDao::class.java,
                    "database-name"
                ).fallbackToDestructiveMigration()
                    .build()
                Instant = instant
                instant
            }
        }
    }
}

