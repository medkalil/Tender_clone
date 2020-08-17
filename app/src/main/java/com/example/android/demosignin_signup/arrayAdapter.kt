package com.example.android.demosignin_signup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class arrayAdapter(context: Context, val ressourseId: Int, val items: ArrayList<CardsModel>?) :
    ArrayAdapter<CardsModel>(context, ressourseId, items!!) {

    override
    fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var _convertView = convertView
        val carditem: CardsModel? = getItem(position)
        if (convertView == null) {
            _convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        }

        val name: TextView = _convertView!!.findViewById(R.id.helloText)
        val image: ImageView = _convertView.findViewById(R.id.image)

        name.setText(carditem?.name)
        image.setImageResource(R.mipmap.ic_launcher)

        return _convertView
    }

}