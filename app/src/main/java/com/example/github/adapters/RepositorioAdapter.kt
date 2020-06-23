package com.example.github.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.helpers.putExtraJson
import com.example.github.models.Repositorio
import com.example.github.ui.activites.RepositorioActivity

class RepositorioAdapter(var context: Context, var list: List<Repositorio>) :
    RecyclerView.Adapter<RepositorioAdapter.ViewHolderAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repositorio, parent, false)
        return ViewHolderAdapter(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
        holder.bind(context, list[position])
    }

    class ViewHolderAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, item: Repositorio) {

            itemView.setOnClickListener {
                var repositorio = item
                var intent = Intent(context, RepositorioActivity::class.java)
                intent.putExtraJson("repositorio", repositorio)
                context.startActivity(intent)
            }

            var name = itemView.findViewById<TextView>(R.id.name)
            name.text = item.name
            var image = itemView.findViewById<ImageView>(R.id.image)
            Glide.with(context).load(item.owner.avatar_url).into(image)
        }
    }

}