package com.example.phonecontactanddetails

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ContactDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        val contactName = intent.getStringExtra("NAME")
        val contactPhotoUri = intent.getStringExtra("PHOTO")
        val contactNumber = intent.getStringExtra("NUMBER")


        val nameTextView = findViewById<TextView>(R.id.contact_name)

        val numberTextView = findViewById<TextView>(R.id.contact_number)

        val photoImageView = findViewById<ImageView>(R.id.contact_image)


        nameTextView.text = contactName;
        numberTextView.text = contactNumber;

        if (contactPhotoUri != null) {
            photoImageView.setImageURI(Uri.parse(contactPhotoUri))
        } else {
            // Set a default image if there's no photo
            photoImageView.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}
