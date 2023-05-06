package com.example.crudclient


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.R


class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemArrayList: ArrayList<UserData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.example.crudclient.R.layout.activity_main)

        itemRecyclerView =findViewById(com.example.crudclient.R.id.item_list)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.hasFixedSize()
        itemArrayList = arrayListOf<UserData>()
        getItemData()

    }

    private fun getItemData() {
        db = FirebaseDatabase.getInstance().getReference("News Update")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (itmsnapshot in snapshot.children){
                        var item = itmsnapshot.getValue(UserData::class.java)
                        itemArrayList.add(item!!)
                    }
                    itemRecyclerView.adapter = itmAdapter(itemArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
