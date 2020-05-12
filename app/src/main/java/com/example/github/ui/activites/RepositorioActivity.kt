package com.example.github.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.helpers.getJsonExtra
import com.example.github.models.Repositorio
import kotlinx.android.synthetic.main.activity_repositorio.*
import kotlinx.android.synthetic.main.item_repositorio.*
import kotlinx.android.synthetic.main.item_repositorio.description
import kotlinx.android.synthetic.main.item_repositorio.image
import kotlinx.android.synthetic.main.item_repositorio.name

class RepositorioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorio)

        var repositorio = intent.getJsonExtra("repositorio", Repositorio::class.java)

        name.text = repositorio?.name
        description.text = repositorio?.description
        Glide.with(this).load(repositorio?.owner?.avatar_url).into(image)
        autor.text = repositorio?.owner?.login

    }
}
