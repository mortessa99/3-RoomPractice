package com.example.a3_roompractice.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase:RoomDatabase (){
    abstract fun getContentFromDao(): UserDao
}