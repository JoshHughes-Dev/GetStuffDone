package com.jhughes.getstuffdone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jhughes.getstuffdone.data.model.GroupEntity
import com.jhughes.getstuffdone.data.model.TaskEntity
import com.jhughes.getstuffdone.data.seeder.DatabaseCallbackSeeder

@Database(
    entities = [GroupEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
    abstract fun groupDao() : GroupDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "get_stuff_done_database")
                .addCallback(DatabaseCallbackSeeder(context))
                .build()
        }
    }
}