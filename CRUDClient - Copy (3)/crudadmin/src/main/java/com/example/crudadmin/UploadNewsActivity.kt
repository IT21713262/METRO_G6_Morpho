package com.example.crudadmin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.crudadmin.databinding.ActivityUploadNewsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import android.util.Base64

class UploadNewsActivity : AppCompatActivity() {
    var sImage:String? = ""
    private lateinit var db: DatabaseReference
    private lateinit var binding: ActivityUploadNewsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    fun insert_data(view: View) {
        val title = binding.uploadTitle.text.toString()
        val description = binding.uploadDescription.text.toString()
        val sorce = binding.uploadSorce.text.toString()
        db = FirebaseDatabase.getInstance().getReference("News Update")
        val users = UserData(title,description,sorce,sImage)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(users).addOnSuccessListener {
            binding.uploadTitle.text.clear()
            binding.uploadDescription.text.clear()
            binding.uploadSorce.text.clear()
            sImage = ""

            Toast.makeText(this,"Data inserted Successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@UploadNewsActivity, NewsActivity::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }



    }

    fun insert_Img(view: View) {
        var myfileintent = Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)

    }
    private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val  uri = result.data!!.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val myBitmap =BitmapFactory.decodeStream(inputStream)
                val stream =ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes = stream.toByteArray()
                sImage =Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.imageView.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()
            }catch (ex:Exception){
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show()

            }

        }

    }

}