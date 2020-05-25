package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.helpers.getJsonExtra
import com.example.github.helpers.putExtraJson
import com.example.github.models.Usuario
import com.example.github.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_autor.*
import kotlinx.android.synthetic.main.item_repositorio.image
import kotlinx.android.synthetic.main.item_repositorio.name
import retrofit2.Call
import retrofit2.Response

class AutorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autor)

        var loginCode = intent.getStringExtra("login")

        var s = RetrofitInitializer().serviceRepositorio()
        var call = s.getUsuarioPorNome(loginCode)
        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                response?.let {

                    if(it.code() == 200)
                    {
                        var autorModel = it?.body()

                        autor.text = autorModel?.name
                        login.text = autorModel?.login
                        followers.text = autorModel?.following.toString() + " seguidores"
                        following.text = autorModel?.followers.toString() + " seguindo"

                        repositorio_value.text = autorModel?.public_repos
                        seguindo_value.text = autorModel?.following.toString()
                        seguidores_value.text = autorModel?.followers.toString()

                        Glide.with(this@AutorActivity).load(autorModel?.avatar_url).into(image)
                    }
                }
            }
            override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                Toast.makeText(this@AutorActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })

    }
}
