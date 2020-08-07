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
import kotlinx.android.synthetic.main.activity_first_activity.*
import kotlinx.android.synthetic.main.item.*
import java.util.ArrayList;


class First_activity : AppCompatActivity() {

    //private  var al: ArrayList<String>? =null


    private var i = 0
    lateinit var arrayAdapter: ArrayAdapter<String>

    private lateinit var flingContainer:SwipeFlingAdapterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_activity)

        val al=ArrayList<String>()


        al?.add("php")
        al?.add("c")
        al?.add("python")
        al?.add("java")
        al?.add("html")
        al?.add("c++")
        al?.add("css")
        al?.add("javascript")


        arrayAdapter =  ArrayAdapter(this,R.layout.item,R.id.helloText,al!!)

        flingContainer=frame

        flingContainer.setAdapter(arrayAdapter)
        flingContainer.setFlingListener(object : onFlingListener {
            override fun removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!")
                al!!.removeAt(0)
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
                al!!.add("XML $i")
                arrayAdapter!!.notifyDataSetChanged()
                Log.d("LIST", "notified")
                i++
            }

            override fun onScroll(scrollProgressPercent: Float) {
                val view: View = flingContainer.getSelectedView()
                item_swipe_right_indicator.setAlpha(if (scrollProgressPercent < 0) -scrollProgressPercent else 0F)
                item_swipe_left_indicator.setAlpha(if (scrollProgressPercent > 0) scrollProgressPercent else 0F)
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



}