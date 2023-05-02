package com.example.chapter7

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1) // 데이터베이스는 싱글톤 패턴 (데이터베이스를 여러개 뱉을 필요가 없음) -> object 이용
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) { // INSTANCE 가 null 이면 초기화
                synchronized(AppDatabase::class.java) { //AppDatabase 가 여러개 생기지 않게 하기 위해
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}