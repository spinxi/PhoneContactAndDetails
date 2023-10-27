package com.example.phonecontactanddetails

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.Manifest

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var adapter: RecyclerView.Adapter<MyAdapter.ViewHolder>? = null;

    private lateinit var recyclerView: RecyclerView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = LinearLayoutManager(this);
        recyclerView.layoutManager = layoutManager;


        // Check for READ_CONTACTS permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted, load contacts
            loadContacts()
        } else {
            // Request the READ_CONTACTS permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }

//        adapter = MyAdapter();
//
//        recyclerView.adapter = adapter;

    }
    private fun loadContacts() {
        // Query phone contacts and populate the data in your adapter
        val contacts = readContacts()
        adapter = MyAdapter(contacts)
        recyclerView.adapter = adapter
    }

    private fun readContacts(): List<ContactList> {
        val contactsList = ArrayList<ContactList>()

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )


        if (cursor != null && cursor.moveToFirst()) {
            val idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val photoUriColumn = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)

            do {
                val contactID = cursor.getString(idColumn)
                val name = cursor.getString(nameColumn)
                val photoUri = if (photoUriColumn != -1) cursor.getString(photoUriColumn) else null
                // Process contact data here
            } while (cursor.moveToNext())
        }

        return contactsList
    }
}