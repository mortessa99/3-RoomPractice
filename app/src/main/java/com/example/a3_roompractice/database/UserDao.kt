package com.example.a3_roompractice.database

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table ORDER BY id ASC") //DESC
    fun getAllUser():MutableList<User>

    @Query("SELECT * FROM user_table WHERE id LIKE :userId")
    fun getUser(userId:Int):User
}