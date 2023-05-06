package com.example.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil;
import com.example.user.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {

            val searchUser : String = binding.searchUser.text.toString()
            if  (searchUser.isNotEmpty()){
                readData(searchUser)
            }else{
                Toast.makeText(this,"PLease enter the user name",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(user: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(user).get().addOnSuccessListener {

            if (it.exists()){

                val name = it.child("name").value
                val job = it.child("job").value
                val location = it.child("location").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchUser.text.clear()
                binding.readName.text = name.toString()
                binding.readJob.text = job.toString()
                binding.readLocation.text = location.toString()
            }else{
                Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}