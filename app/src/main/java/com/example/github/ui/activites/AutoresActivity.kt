package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.github.R
import com.example.github.adapters.AutorAdapter
import com.example.github.adapters.RepositorioAdapter
import com.example.github.helpers.putExtraJson
import com.example.github.models.AutorSearch
import com.example.github.models.Repositorio
import com.example.github.models.Usuario
import com.example.github.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_autores.*
import kotlinx.android.synthetic.main.activity_repositorios.*
import kotlinx.android.synthetic.main.activity_repositorios.lista
import retrofit2.Call
import retrofit2.Response

class AutoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autores)

        pesquisar.setOnClickListener {
            getUsuarios()
        }

    }

    fun getUsuarios() {
        var s = RetrofitInitializer().serviceRepositorio()

        var nome = pesquisar_campo.text.toString()

            var call = s.getUsuarioPorNomeQuery(nome)
            call.enqueue(object : retrofit2.Callback<AutorSearch> {
                override fun onResponse(call: Call<AutorSearch>?, response: Response<AutorSearch>?) {
                    response?.let {

                        if(it.code() == 200) {
                            lista.adapter = AutorAdapter(this@AutoresActivity, it.body().items)

                            lista.setOnItemClickListener { parent, view, position, id ->

                                var autor = it.body().items[position].login
                                var intent = Intent(this@AutoresActivity, RepositoriosActivity::class.java)
                                intent.putExtra("autor_login", autor)
                                startActivity(intent)
                            }
                        }

                    }
                }
                override fun onFailure(call: Call<AutorSearch>?, t: Throwable?) {
                    Toast.makeText(this@AutoresActivity, "Ops", Toast.LENGTH_LONG).show()
                }
            })

    }
}
