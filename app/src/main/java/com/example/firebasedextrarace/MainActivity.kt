package com.example.firebasedextrarace

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Import the necessary Firebase libraries

        // Get a reference to the button and set an OnClickListener on it
        val btn = findViewById<Button>(R.id.button)

        val database = Firebase.database


        val myRef = database.getReference("users")

        btn.setOnClickListener {

            // Get the email and password entered by the user
            val email = findViewById<EditText>(R.id.editTextTextPassword1).text.toString()
            val password = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

            // Create a new User object with the email and password
            val user = User(email, password)


            // Save the User object to the Firebase database
            myRef.setValue(user)
            // Create a new toast object
            // Create a new toast object
            val toast =
                Toast.makeText(applicationContext, "წარმატებით შეიქმნა", Toast.LENGTH_SHORT)

            // Show the toast

            // Show the toast
            toast.show()
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<User>()
                if (value != null) {
                    println("Inserted value is: ${value.email} ${value.password}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                println("Failed to read value.")
            }
        })
    }
}
class User(var email: String, var password: String) {
    constructor() : this("", "")
}