package com.example.a3_roompractice.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.a3_roompractice.R
import com.example.a3_roompractice.database.User
import com.example.a3_roompractice.database.UserDatabase
import com.example.a3_roompractice.databinding.ActivityUpdatUserBinding
import com.google.android.material.snackbar.Snackbar

class UpdateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatUserBinding
    private val db:UserDatabase by lazy {
        Room.databaseBuilder(this,UserDatabase::class.java,"mydatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    var id = 0
    var n =""
    var a =0
    lateinit var user:User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            id=it.getInt("userid")
        }



        binding.apply {
            n = db.getContentFromDao().getUser(id).name
            a = db.getContentFromDao().getUser(id).age

            edtUpdateName.setText(n)
            edtUpdateAge.setText(a.toString())

            fabDelete.setOnClickListener {
                user = User(id,n,a)
                db.getContentFromDao().deleteUser(user)
                finish()
            }

            fabSaveUpdate.setOnClickListener {
                val checkName = edtUpdateName.text.toString()
                val checkAge = edtUpdateAge.text.toString()
                if (checkName.isNotEmpty()&&checkAge.isNotEmpty()) {
                    user = User(id,checkName,checkAge.toInt())
                    db.getContentFromDao().updateUser(user)
                    finish()
                } else {
                    Snackbar.make(it,"Name and Age Can not be empty", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}