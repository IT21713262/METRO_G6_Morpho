package com.example.crudclient



import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.util.Base64

class itmAdapter(private  val  itmlst:ArrayList<UserData>):RecyclerView.Adapter<itmAdapter.itmHolder>() {
    class itmHolder(itmView:View):RecyclerView.ViewHolder(itmView){
        val itmtitle:EditText =itmView.findViewById(R.id.edttitle)
        val itmsorce:EditText =itmView.findViewById(R.id.edtsorce)
        val itmimg:ImageView = itmView.findViewById(R.id.itm_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itmHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent,false)
        return itmHolder(itemView)
    }

    override fun onBindViewHolder(holder: itmHolder, position: Int) {
        val  currentitem =itmlst[position]
        holder.itmtitle.setText(currentitem.title.toString())
        holder.itmsorce.setText(currentitem.sorce.toString())
        val bytes = android.util.Base64.decode(currentitem.itemImg,
            android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.itmimg.setImageBitmap(bitmap)

    }

    override fun getItemCount(): Int {
        return  itmlst.size
    }


}