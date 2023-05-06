package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUpdateNewsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNewsBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateNewsButton.setOnClickListener {
            val referenceTitle = binding.referenceTitle.text.toString()
            val updateDescription = binding.updateDescription.text.toString()
            val updateSorce = binding.updateSorce.text.toString()

            updateData(referenceTitle,updateDescription,updateSorce)
        }
    }
    private fun updateData(title: String, description: String, sorce:String) {
        database = FirebaseDatabase.getInstance().getReference("News Update")
        val user = mapOf<String,String>(
            "description" to description,
            "sorce" to sorce,

        )
        database.child(title).updateChildren(user).addOnSuccessListener {
            binding.referenceTitle.text.clear()
            binding.updateDescription.text.clear()
            binding.updateSorce.text.clear()

            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()

            val intent = Intent(this@UpdateNewsActivity, NewsActivity::class.java)
            startActivity(intent)
            finish()



        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}