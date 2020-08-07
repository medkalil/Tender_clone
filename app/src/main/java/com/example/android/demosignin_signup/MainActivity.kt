package com.example.android.demosignin_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.email
import kotlinx.android.synthetic.main.activity_main.password
import kotlinx.android.synthetic.main.activity_main.signupbtn
import kotlinx.android.synthetic.main.activity_sign_up_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth=FirebaseAuth.getInstance()

        signupbtn?.setOnClickListener {
            val i: Intent = Intent(this, SignUp_activity::class.java)
            startActivity(i)
            finish()
        }

        signinbtn?.setOnClickListener {

            login()

        }//listener


    }


    private fun login() {
        if (!email.text.toString().isEmpty() && !password.text.toString().isEmpty()) {
            auth.signInWithEmailAndPassword(email?.text.toString(), password?.text.toString())
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val email_getted: String? = FirebaseAuth.getInstance().currentUser?.email
                        val i: Intent = Intent(this, First_activity::class.java)
                        startActivity(i)
                        finish()
                        Toast.makeText(this, "Login Success )", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(this, "Login Failed )", Toast.LENGTH_LONG).show()
                    }

                })


        } else {
            Toast.makeText(this, "Check your input )", Toast.LENGTH_LONG).show()
        }

    }//login fun


}