package com.example.phonecontactanddetails

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val contacts: List<ContactList>) :  RecyclerView.Adapter<MyAdapter.ViewHolder> (){

//    private var title = arrayOf("test1", "test2");
//    private var image = intArrayOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {

        val contact = contacts[position];
        holder.itemTitle.text = contact.name;
        holder.itemNumber.text = contact.number;
        // check if there is any image
        if (contact.photoUri != null) {
            holder.itemImage.setImageURI(Uri.parse(contact.photoUri))
        } else {
            holder.itemImage.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }



    override fun getItemCount(): Int {
        return contacts.size;
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView;
        var itemTitle: TextView;
        var itemNumber: TextView;

        init{
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_name);
            itemNumber = itemView.findViewById(R.id.item_number);

            itemView.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val context = itemView.context

                    val intent = Intent(context, ContactDetails::class.java)

                    // Pass the contact details to the ContactDetailsActivity
                    intent.putExtra("NAME", contacts[position].name)
                    intent.putExtra("NUMBER", contacts[position].number)
                    intent.putExtra("PHOTO", contacts[position].photoUri)

                    context.startActivity(intent)
                }

            }

        }
    }


}