package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.example.github.R
import com.example.github.adapters.RepositorioAdapter
import com.example.github.helpers.putExtraJson
import com.example.github.models.AccessToken
import com.example.github.models.Repositorio
import com.example.github.models.Usuario
import com.example.github.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_repositorios.*
import retrofit2.Call
import retrofit2.Response

class RepositoriosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorios)

        var login = intent.getStringExtra("autor_login")
        getRepositorioPorUsuario(login)
    }

    fun getRepositorioPorUsuario(user : String) {
        var s = RetrofitInitializer().serviceRepositorio()
        var call = s.getRepositoriosPorUsuario(user)
        call.enqueue(object : retrofit2.Callback<List<Repositorio>> {
            override fun onResponse(call: Call<List<Repositorio>>?, response: Response<List<Repositorio>>?) {
                response?.let {

                    if(it.code() == 200) {
                        lista.adapter = RepositorioAdapter(this@RepositoriosActivity, it.body())

                        lista.setOnItemClickListener { parent, view, position, id ->

                            var repositorio = it.body()[position]
                            var intent = Intent(this@RepositoriosActivity, RepositorioActivity::class.java)
                            intent.putExtraJson("repositorio", repositorio)
                            startActivity(intent)
                        }
                    }

                }
            }
            override fun onFailure(call: Call<List<Repositorio>>?, t: Throwable?) {
                MaterialDialog.Builder(this@RepositoriosActivity)
                    .theme(Theme.DARK)
                    .title("Ops!")
                    .content("Ocorreu um erro ao processar a solicitação. Por favor, tente novamente.")
                    .positiveText("OK")
                    .show()
            }
        })
    }
}
