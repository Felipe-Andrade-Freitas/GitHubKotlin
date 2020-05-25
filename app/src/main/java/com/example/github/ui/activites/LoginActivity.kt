package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.github.R
import com.example.github.models.Account
import com.example.github.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
                loginClick()
        }

    }

    fun loginClick() {

        var s = RetrofitInitializer().serviceAccount()

        var call = s.auth(email.text.toString(), password.text.toString())

        call.enqueue(object : retrofit2.Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                response?.let {

                    if (it.code() == 200) {
                        var intent = Intent(this@LoginActivity, RepositoriosActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@LoginActivity, "Error!", Toast.LENGTH_LONG).show()
                    }

                }

            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {

                Toast.makeText(this@LoginActivity, "Ops", Toast.LENGTH_LONG).show()

            }

        })


    }
}
