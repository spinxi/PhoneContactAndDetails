package com.example.phonecontactanddetails

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val contacts: List<ContactList>) :  RecyclerView.Adapter<MyAdapter.ViewHolder> (){

//    private var title = arrayOf("test1", "test2");
//    private var image = intArrayOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        // Use contact data to populate the views
        val contact = contacts[position]
        holder.itemTitle.text = contact.name
        if (contact.photoUri != null) {
            // Load the contact's thumbnail image using the photoUri
            holder.itemImage.setImageURI(Uri.parse(contact.photoUri))
        } else {
            // Set a default image if the contact has no photo
            holder.itemImage.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }



    override fun getItemCount(): Int {
        return contacts.size;
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView;
        var itemTitle: TextView;

        init{
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_number);

            itemView.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val context = itemView.context
                    Toast.makeText(context, "Clicked on ${contacts[position]}", Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(itemView.context, "Clicked on ${contacts[position]}", Toast.LENGTH_SHORT).show();
            }

        }
    }


}