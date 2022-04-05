package com.example.a3_roompractice.activities

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.a3_roompractice.R
import com.example.a3_roompractice.database.UserDatabase
import com.example.a3_roompractice.databinding.ActivityMainBinding
import com.example.a3_roompractice.utils.UserAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val userAdapter by lazy { UserAdapter() }
    private val db:UserDatabase by lazy {
        Room.databaseBuilder(this,UserDatabase::class.java,"mydatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            floatingActionButton.setOnClickListener {
                startActivity(Intent(this@MainActivity,AddUserActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            if (db.getContentFromDao().getAllUser().isNotEmpty()){
                textView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                userAdapter.differ.submitList(db.getContentFromDao().getAllUser())
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = userAdapter

            } else {
                textView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
    }
}