package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.helpers.getJsonExtra
import com.example.github.helpers.putExtraJson
import com.example.github.models.Repositorio
import com.example.github.models.Usuario
import com.example.github.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_repositorio.*
import kotlinx.android.synthetic.main.item_repositorio.image
import kotlinx.android.synthetic.main.item_repositorio.name
import retrofit2.Call
import retrofit2.Response

class RepositorioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorio)

        var repositorio = intent.getJsonExtra("repositorio", Repositorio::class.java)

        name.text = repositorio?.name
        description.text = repositorio?.description
        star.text = repositorio?.stargazers_count.toString() + " stars"
        fork.text = repositorio?.forks.toString() + " forks"

        Glide.with(this).load(repositorio?.owner?.avatar_url).into(image)
        getUsuario(repositorio?.owner?.login)

        autor_pai.setOnClickListener {
            var intent = Intent(this, AutorActivity::class.java)
            intent.putExtra("login", repositorio?.owner?.login)
            startActivity(intent)
        }
    }


    fun getUsuario(user : String?) {
        var s = RetrofitInitializer().serviceRepositorio()
        var call = s.getUsuarioPorNome(user.toString())
        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                response?.let {

                    if(it.code() == 200)
                    {
                        autor.text = it.body()?.name
                    }
                }
            }
            override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                MaterialDialog.Builder(this@RepositorioActivity)
                    .theme(Theme.DARK)
                    .title("Ops!")
                    .content("Ocorreu um erro ao processar a solicitação. Por favor, tente novamente.")
                    .positiveText("OK")
                    .show()
            }
        })
    }
}
