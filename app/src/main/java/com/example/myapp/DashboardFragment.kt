package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val jobsPosted: Button = view.findViewById(R.id.JobsPosted)
        jobsPosted.setOnClickListener{
            val fragment = JobsPostedFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment)?.commit()
        }
        return view
    }


}