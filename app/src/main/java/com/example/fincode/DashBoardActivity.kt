package com.example.fincode

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.fincode.databinding.ActivityDashboradBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class DashBoardActivity : AppCompatActivity() {

    private var _binding: ActivityDashboradBinding? =null
    private val binding: ActivityDashboradBinding get() = _binding!!

    private lateinit var database: FirebaseDatabase
    lateinit var itemsRef: DatabaseReference
    private val items = arrayListOf<User>()

    private val adapter by lazy {
        UserAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboradBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Get a reference to the database
        database = FirebaseDatabase.getInstance()

        // Get a reference to the "items" child
         itemsRef = database.reference.child("User")
        initView()
    }

    private fun initView(){

        binding.progressBar.visibility = View.VISIBLE
        binding.userRV.adapter = adapter
        binding.userRV.setHasFixedSize(true)

        itemsRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                binding.progressBar.visibility = View.GONE

                for (childSnapshot in snapshot.children) {
                    Log.d("User Data", "onDataChange: ${childSnapshot.value}")
                    val item = childSnapshot.getValue(User::class.java)
                    items.add(item!!)
                }

                adapter.updateList(items)
                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBar.visibility = View.GONE

            }

        })

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.sortByName -> {

                val sortedBy = items.sortedBy { it.name }
                adapter.updateList(sortedBy)

                true
            }
            R.id.sortByAge -> {
                val sortedBy = items.sortedBy { it.age }
                adapter.updateList(sortedBy)
                true
            }
            R.id.sortByCity -> {
                val sortedBy = items.sortedBy { it.city }
                adapter.updateList(sortedBy)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}