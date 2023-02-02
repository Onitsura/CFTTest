package com.onitsura12.cfttest.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onitsura12.cfttest.data.models.CardDetails
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel

@Database(entities = [CardDetailsDatabaseModel::class], version = 2, exportSchema = false)
abstract class DetailsDatabase : RoomDatabase() {

    abstract fun detailsDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: DetailsDatabase? = null


        fun getDatabase(context: Context): DetailsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = DetailsDatabase::class.java,
                    name = "details_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}