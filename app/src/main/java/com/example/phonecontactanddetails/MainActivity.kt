
package com.example.phonecontactanddetails
import android.Manifest
import android.R.id
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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

        val contacts = readContacts()
        adapter = MyAdapter(contacts)
        recyclerView.adapter = adapter
    }

    private fun readContacts(): List<ContactList> {
        val contactsList = mutableListOf<ContactList>()

        // Define the columns you want to retrieve for the CommonDataKinds.Phone table
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  // Use the correct URI
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val nameColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val phoneColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val photoUriColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI)

            while (cursor.moveToNext()) {
                val name = cursor.getString(nameColumn)
                val phone = cursor.getString(phoneColumn)
                val photoUri = cursor.getString(photoUriColumn)

                val contact = ContactList(name, photoUri, phone)
                contactsList.add(contact)
            }
        }

        return contactsList
    }


}