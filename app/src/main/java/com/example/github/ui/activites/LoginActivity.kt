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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            loginClick();
        }

    }

    fun loginClick() {

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = email.text.toString()
        account.password = password.text.toString()

        var call = s.auth(account)

        call.enqueue(object : retrofit2.Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                response?.let {

                    if (it.code() == 200) {
                        Toast.makeText(this@MainActivity, "Ok", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_LONG).show()
                    }

                }

            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {

                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()

            }

        })


    }
}
