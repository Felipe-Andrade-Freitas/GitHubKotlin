package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.github.R
import com.example.github.adapters.RepositorioAdapter
import com.example.github.helpers.IntentUtil
import com.example.github.helpers.putExtraJson
import com.example.github.models.AccessToken
import com.example.github.models.Repositorio
import com.example.github.models.Usuario
import com.example.github.services.RetrofitInitializer
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var client_id = "35e01f6c816aaf91dd1c"
    private var client_secret = "7f6e84313a29378e985234395710eff0cc3e455b"
    private var redirect_uri = "github://callback"
    var accessToken = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id=" + client_id + "&scope=repo&redirect_uri=" + redirect_uri));
        //startActivity(intent);
        getRepositorioPorUsuario("Felipe-Andrade-Freitas")
        //getRepositorio()
    }

    @Override
    override fun onResume() {
        super.onResume()

        var uri = intent.data

        if(uri != null && uri.toString().startsWith(redirect_uri)) {
            var code = uri.getQueryParameter("code")
            getAutentication(code)
        }
    }

    fun getRepositorio() {
        var s = RetrofitInitializer().serviceRepositorio()
        var call = s.getRepositorios()
        call.enqueue(object : retrofit2.Callback<List<Repositorio>> {
            override fun onResponse(call: Call<List<Repositorio>>?, response: Response<List<Repositorio>>?) {
                response?.let {

                    if(it.code() == 200) {
                        lista.adapter = RepositorioAdapter(this@MainActivity, it.body())
                    }

                }
            }
            override fun onFailure(call: Call<List<Repositorio>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getRepositorioPorUsuario(user : String) {
        var s = RetrofitInitializer().serviceRepositorio()
        var call = s.getRepositoriosPorUsuario(user)
        call.enqueue(object : retrofit2.Callback<List<Repositorio>> {
            override fun onResponse(call: Call<List<Repositorio>>?, response: Response<List<Repositorio>>?) {
                response?.let {

                    if(it.code() == 200) {
                        lista.adapter = RepositorioAdapter(this@MainActivity, it.body())
                        
                        lista.setOnItemClickListener { parent, view, position, id ->

                            var repositorio = it.body()[position]
                            var intent = Intent(this@MainActivity, RepositorioActivity::class.java)
                            intent.putExtraJson("repositorio", repositorio)
                            startActivity(intent)
                        }
                    }

                }
            }
            override fun onFailure(call: Call<List<Repositorio>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getUsuario(type_token : String, access_token : String) {
        var s = RetrofitInitializer().serviceRepositorio()
        var call = s.getUsuario(type_token, access_token)
        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                response?.let {

                    if(it.code() == 200) {
                        getRepositorioPorUsuario(response.body().login)
                    }

                }
            }
            override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getAutentication(code : String?){
        var s = RetrofitInitializer().serviceAutentication()
        var call = s.getAccessToken(client_id, client_secret, code)
        call.enqueue(object : retrofit2.Callback<AccessToken> {
            override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {
                accessToken = response?.body()?.getAccessToken().toString()
                getUsuario(response?.body()?.getTokenType().toString(), accessToken)
            }
            override fun onFailure(call: Call<AccessToken>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })
    }
}
