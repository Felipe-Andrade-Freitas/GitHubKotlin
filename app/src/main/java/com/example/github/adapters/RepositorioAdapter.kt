package com.example.github.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.models.Repositorio

class RepositorioAdapter(var context: Context, var list : List<Repositorio>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = LayoutInflater.from(context).inflate(R.layout.item_repositorio, null)

        var name = view.findViewById<TextView>(R.id.name)
        name.text = list[position].name
        var description = view.findViewById<TextView>(R.id.description)
        description.text = list[position].description
        var image = view.findViewById<ImageView>(R.id.image)
        Glide.with(context).load(list[position].owner.avatar_url).into(image)
        return view;
    }

    override fun getItem(position: Int): Any {
        return "";
    }

    override fun getItemId(position: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return list.size;
    }

}