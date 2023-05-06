package com.example.crudadmin
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adminusername : EditText
    lateinit var adminpassword: EditText
    lateinit var adminloginbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adminloginbutton.setOnClickListener(View.OnClickListener {
            if (binding.adminusername.text.toString() == "admin" && binding.adminpassword.text.toString() == "1234") {
                Toast.makeText(this, "Admin Login Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, NewsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}