package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudadmin.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            val intent = Intent(this@NewsActivity, UploadNewsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@NewsActivity, UpdateNewsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@NewsActivity, DeleteNewsActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}