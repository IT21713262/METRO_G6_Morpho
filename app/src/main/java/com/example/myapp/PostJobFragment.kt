package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class PostJobFragment : Fragment() {

    private lateinit var organizationNameEditText: TextInputEditText
    private lateinit var contactNumberEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var cityEditText: TextInputEditText
    private lateinit var jobPositionEditText: TextInputEditText
    private lateinit var jobDescriptionEditText: TextInputEditText
    private lateinit var qualificationsEditText: TextInputEditText

    private lateinit var database: DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_post_job, container, false)
        organizationNameEditText = view.findViewById(R.id.organization_name)
        contactNumberEditText = view.findViewById(R.id.contact_number)
        emailEditText = view.findViewById(R.id.email)
        jobPositionEditText = view.findViewById(R.id.jobPosition)
        cityEditText = view.findViewById(R.id.city)
        jobDescriptionEditText = view.findViewById(R.id.jobDescription)
        qualificationsEditText = view.findViewById(R.id.qualifications)

        val prevBtn : Button = view.findViewById(R.id.prev)
        val postBtn: Button = view.findViewById(R.id.post)
        val emailEditText: TextInputEditText = view.findViewById(R.id.email)

        database = FirebaseDatabase.getInstance().reference


        prevBtn.setOnClickListener{
            val fragment = ProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container,fragment)?.commit()
        }

        postBtn.setOnClickListener {

            if (validateInputs()) {
                // Inputs are valid, proceed with posting job
                val organizationName = organizationNameEditText.text.toString().trim()
                val contactNumber= contactNumberEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()
                val city = cityEditText.text.toString().trim()
                val jobPosition= jobPositionEditText.text.toString().trim()
                val jobDescription= jobDescriptionEditText.text.toString().trim()
                val qualifications= qualificationsEditText.text.toString().trim()

                val job = Job(
                    organizationName,
                    contactNumber,
                    email,
                    city,
                    jobPosition,
                    jobDescription,
                    qualifications
                )

                // Save the job to the database
                database.child("jobs").push().setValue(job)

                showSuccessDialog()
            }
        }
        return view

    }
    private fun validateInputs(): Boolean {
        var isValid = true
        val organizationName = organizationNameEditText.text.toString().trim()
        val contactNumber= contactNumberEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val city = cityEditText.text.toString().trim()
        val jobPosition= jobPositionEditText.text.toString().trim()
        val jobDescription= jobDescriptionEditText.text.toString().trim()
        val qualifications= qualificationsEditText.text.toString().trim()

        if (organizationName.isEmpty()) {
            organizationNameEditText.error = "Organization name is required"
            isValid = false
        }

        if (contactNumber.isEmpty()) {
            contactNumberEditText.error = "Contact number is required"
            isValid = false
        }

        if (email.isEmpty()) {
            emailEditText.error = "Email is required"
            isValid = false
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Invalid email address"
            isValid = false
        }

        if (city.isEmpty()) {
            cityEditText.error = "City is required"
            isValid = false
        }

        if (jobPosition.isEmpty()) {
            jobPositionEditText.error = "Job position is required"
            isValid = false
        }


        if (jobDescription.isEmpty()) {
            jobDescriptionEditText.error = "Job description is required"
            isValid = false
        }

        if (qualifications.isEmpty()) {
            qualificationsEditText.error = "Qualifications are required"
            isValid = false
        }

        return isValid
    }

    private fun showSuccessDialog() {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.success_dialog, null)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .create()

        view.findViewById<Button>(R.id.ok_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }




}