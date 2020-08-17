package com.example.android.demosignin_signup


import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.lorentzos.flingswipe.SwipeFlingAdapterView.onFlingListener
import java.util.*
import android.app.Activity;
import android.content.Context;
import android.content.Intent
import android.view.View;
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_first_activity.*
import kotlinx.android.synthetic.main.item.*
import java.util.ArrayList;


class First_activity : AppCompatActivity() {

    //private  var al: ArrayList<String>? =null
    private lateinit var mAuth: FirebaseAuth

    private lateinit var al: ArrayList<String>


    private var i = 0
    lateinit var arrayAdapter: ArrayAdapter<CardsModel>

    // private lateinit var rowitems: ArrayList<CardsModel>

    lateinit var rowitems: ArrayList<CardsModel>

    private lateinit var flingContainer: SwipeFlingAdapterView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_activity)

        mAuth = FirebaseAuth.getInstance()

        //val al=ArrayList<String>()

        //  val rowitems=ArrayList<CardsModel>()

        //Inisialize the ArrayList as an empty as First
        //  al = ArrayList()


        /*     al?.add("php")
             al?.add("c")
             al?.add("python")
             al?.add("java")
             al?.add("html")
             al?.add("c++")
             al?.add("css")
             al?.add("javascript") */

        rowitems= ArrayList()

        //Get All the name in the Users Tab
        getAll()

        arrayAdapter =  arrayAdapter(this, R.layout.item, rowitems)


        flingContainer = frame

        flingContainer.setAdapter(arrayAdapter)
        flingContainer.setFlingListener(object : onFlingListener {
            override fun removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!")
                rowitems!!.removeAt(0)
                arrayAdapter!!.notifyDataSetChanged()
            }

            override fun onLeftCardExit(dataObject: Any) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(this@First_activity, "Left!")
            }

            override fun onRightCardExit(dataObject: Any) {
                makeToast(this@First_activity, "Right!")
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                // Ask for more data here
                /*  al!!.add("XML $i")
                  arrayAdapter!!.notifyDataSetChanged()
                  Log.d("LIST", "notified")
                  i++ */
            }

            override fun onScroll(scrollProgressPercent: Float) {
                val view: View = flingContainer.getSelectedView()
                /* item_swipe_right_indicator.setAlpha(if (scrollProgressPercent < 0) -scrollProgressPercent else 0F)
                 item_swipe_left_indicator.setAlpha(if (scrollProgressPercent > 0) scrollProgressPercent else 0F)*/
            }
        })


        // Optionally add an OnItemClickListener

        flingContainer.setOnItemClickListener(SwipeFlingAdapterView.OnItemClickListener { itemPosition, dataObject ->
            makeToast(
                this@First_activity,
                "Clicked!"
            )
        })

    }//onCreate

    fun makeToast(ctx: Context?, s: String?) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show()
    }

    //SignOut
    fun signout(view: View) {
        mAuth.signOut()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
        return
    }


     fun getAll() {
         val currentUserDB: DatabaseReference =
             FirebaseDatabase.getInstance().getReference().child("Users")
         currentUserDB.addChildEventListener(object : ChildEventListener {
             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

             override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                 TODO("Not yet implemented")
             }

             override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                 TODO("Not yet implemented")
             }

             override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                 //Get All the name in the Users Tab
                 if (snapshot.exists()) {
                     var itt:CardsModel= CardsModel(snapshot.key!!,snapshot.child("name").getValue().toString())
                     rowitems?.add(itt)
                     arrayAdapter.notifyDataSetChanged()
                 }
             }

             override fun onChildRemoved(snapshot: DataSnapshot) {
                 TODO("Not yet implemented")
             }

         })//addChildEventListener

     }//getAll

}