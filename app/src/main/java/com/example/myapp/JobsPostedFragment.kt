package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class JobsPostedFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<Job>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jobs_posted, container, false)

        jobRecyclerView = view.findViewById(R.id.jobListRecyclerView)
        jobRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        jobRecyclerView.setHasFixedSize(true)

        jobArrayList = arrayListOf<Job>()
        getJobData()

        return view


    }

    private fun getJobData() {
        dbref = FirebaseDatabase.getInstance().getReference("jobs")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(jobSnapshot in snapshot.children){

                        val job = jobSnapshot.getValue(Job::class.java)
                        jobArrayList.add(job!!)
                    }
                    jobRecyclerView.adapter = MyAdapter(jobArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


}