package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapp.R.id.postJob


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val postJob: Button = view.findViewById(postJob)
        postJob.setOnClickListener{
            val fragment = PostJobFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment)?.commit()
        }
        return view
    }

}