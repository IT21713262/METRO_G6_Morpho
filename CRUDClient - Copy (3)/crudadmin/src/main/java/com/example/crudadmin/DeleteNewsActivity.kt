package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityDeleteNewsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DeleteNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteNewsBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteNewsButton.setOnClickListener {
            val title = binding.deleteTitle.text.toString()
            if (title.isNotEmpty())
                deleteData(title)
            else
                Toast.makeText(this, "Please enter the Title", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(title: String){
        database = FirebaseDatabase.getInstance().getReference("News Update")
        database.child(title).removeValue().addOnSuccessListener {
            binding.deleteTitle.text.clear()
            Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@DeleteNewsActivity, NewsActivity::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}