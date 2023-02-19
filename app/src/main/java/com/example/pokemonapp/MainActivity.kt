package com.example.pokemonapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")

//        FirebaseDatabase.getInstance().getReference("users")
//            .child("alex").setValue("alexn@gmail.com").isSuccessful

//        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(object:
//            ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                var mail = snapshot.child("alexaderpinzon").value.toString()
//                Log.d("FirebaseDatabase", mail)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("FirebaseDatabase", "Failed to read value.", error.toException())
//            }
//        })

//        FirebaseDatabase.getInstance().getReference("users").child("alexaderpinzon")
//            .get().addOnSuccessListener {
//                Log.d("FirebaseDatabase", it.value.toString())
//            }
    }

}
