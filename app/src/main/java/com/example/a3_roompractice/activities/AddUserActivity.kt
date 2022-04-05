package com.example.a3_roompractice.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.a3_roompractice.R
import com.example.a3_roompractice.database.User
import com.example.a3_roompractice.database.UserDatabase
import com.example.a3_roompractice.databinding.ActivityAddUserBinding
import com.google.android.material.snackbar.Snackbar

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private val db:UserDatabase by lazy {
        Room.databaseBuilder(this,UserDatabase::class.java,"mydatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fabSave.setOnClickListener {
                val userName =edtName.text.toString()
                val userAge = edtAge.text.toString()
                val user = User(0,userName, userAge.toInt())
                if(userName.isNotEmpty() && userAge.isNotEmpty() ){
                    db.getContentFromDao().insertUser(user)
                    Snackbar.make(it,"Saved",Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(it,"Name and Age Can not be empty",Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}