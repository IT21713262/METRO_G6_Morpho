package com.example.myapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MyAdapter(private val jobList : ArrayList<Job>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_item_layout,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = jobList[position]

        holder.jobPosition.text = currentitem.jobPosition
        holder.organizationName.text = currentitem.organizationName
        holder.city.text = currentitem.city



        holder.deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Delete Job")
            builder.setMessage("Are you sure you want to delete this job?")
            builder.setPositiveButton("Yes") { dialog, which ->
                // Delete the job
                deleteJob(position)
            }
            builder.setNegativeButton("No", null)
            builder.show()
        }
    }

    private fun deleteJob(position: Int) {
        // Get the job at the given position
        val jobToDelete = jobList[position]

        // Get a reference to the Firebase database
        val database = Firebase.database.reference

        // Build the path to the job to delete
        val jobPath = "jobs/${jobToDelete.jobPosition}"

        // Delete the job from the Firebase database
        database.child(jobPath).removeValue()

        // Remove the job from the jobList
        jobList.removeAt(position)

        // Notify the adapter that an item has been removed
        notifyItemRemoved(position)
    }






    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        val jobPosition : TextView = itemView.findViewById(R.id.jobPositionName)
        val organizationName : TextView = itemView.findViewById(R.id.companyName)
        val city : TextView = itemView.findViewById(R.id.cityName)
        val deleteButton: ImageButton = itemView.findViewById(R.id.imageDeleteButton)



    }



}