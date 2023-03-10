package com.example.fincode

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fincode.databinding.UserItemBinding

class UserAdapter(): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = listOf<User>()

    inner class UserViewHolder(binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.userNameTV
        val age: TextView = binding.userAgeTV
        val city: TextView = binding.userCityTV

    }

    fun updateList(mUserList: List<User>){
        userList = mUserList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]

        holder.name.text = user.name
        holder.age.text = user.age.toString()
        holder.city.text = user.city
    }
}