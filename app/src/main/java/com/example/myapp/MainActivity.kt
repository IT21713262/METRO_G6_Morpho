package com.example.myapp

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.databinding.FragmentPostJobBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(DashboardFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }
                R.id.news -> {
                    loadFragment(NewsFragment())
                    true
                }
                R.id.notifications -> {
                    loadFragment(NotificationsFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> {
                    error("ERROR")
                }
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.container,ProfileFragment()).commit()



    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }


}