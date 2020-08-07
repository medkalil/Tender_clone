package com.example.android.demosignin_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up_activity.*

class SignUp_activity : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_activity)

        mAuth=FirebaseAuth.getInstance()
      //  firebaseauthstatelistener.onAuthStateChanged()


        signupbtn?.setOnClickListener {
            register()
        }
    }


    //register User
    private fun register(){
        if (!email.text.toString().isEmpty() && !password.text.toString().isEmpty() && !name.text.toString().isEmpty()  ) {
            mAuth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid

                    //Add Name
                    val currentUserDB:DatabaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("name")
                    currentUserDB.setValue(name.text.toString())

                    startActivity(Intent(this, First_activity::class.java))
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()

                }else {
                    Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                }
            })
        }else {
            Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
        }
    }



}