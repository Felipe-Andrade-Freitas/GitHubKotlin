package com.example.github.ui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
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

            //var intent = Intent(this@LoginActivity, AutoresActivity::class.java)
           // startActivity(intent)
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
                        var intent = Intent(this@LoginActivity, AutoresActivity::class.java)
                        startActivity(intent)

                    } else {
                        MaterialDialog.Builder(this@LoginActivity)
                            .theme(Theme.DARK)
                            .title("Ops!")
                            .content("Erro ao realizar o login. Por favor, verifique suas credenciais e tente novamente.")
                            .positiveText("OK")
                            .show()

                    }

                }

            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {

                Toast.makeText(this@LoginActivity, "Ops", Toast.LENGTH_LONG).show()

            }

        })


    }
}
