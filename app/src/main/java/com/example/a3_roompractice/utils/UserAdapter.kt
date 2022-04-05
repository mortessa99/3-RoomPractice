package com.example.a3_roompractice.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_roompractice.activities.UpdateUserActivity
import com.example.a3_roompractice.database.User
import com.example.a3_roompractice.databinding.UserItemBinding

class UserAdapter:RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    private lateinit var binding:UserItemBinding
    private lateinit var  context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = UserItemBinding.inflate(inflater,parent,false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        fun bind(item: User){
            binding.apply {
                txtItem.text = "${item.id}-${item.name} ${item.age}"
                root.setOnClickListener {
                    val intent = Intent(context,UpdateUserActivity::class.java)
                    intent.putExtra("userid",item.id)
                    context.startActivity(intent)
                }
            }
        }
    }

   private val diffUtilCallBack = object : DiffUtil.ItemCallback<User>(){
       override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
           return oldItem.id == newItem.id
       }

       override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
           return oldItem == newItem
       }
   }

    val differ = AsyncListDiffer(this,diffUtilCallBack)
}