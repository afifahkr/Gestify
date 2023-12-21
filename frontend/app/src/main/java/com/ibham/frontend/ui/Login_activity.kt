// LoginActivity.kt
package com.ibham.frontend.ui

import ApiService
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ibham.frontend.databinding.ActivityLogin2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login_activity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisai untuk retrofitnyaa
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Buat instance ApiService
        apiService = retrofit.create(ApiService::class.java)

        binding.loginButton.setOnClickListener(View.OnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            // Tambahkan kondisi untuk username dan password khusus
            if ((email == "gestify" && password == "gestify123") ||
                // Tambahkan opsi login lain jika diperlukan
                (email == "username_lain" && password == "password_lain")) {
                // Handle login berhasil
                Toast.makeText(this@Login_activity, "Login berhasil", Toast.LENGTH_SHORT).show()

                // Redirect ke aktivitas NavBar
                val intent = Intent(this@Login_activity, NavBar::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika tidak cocok dengan opsi yang diinginkan, gunakan API login
                val loginRequest = LoginRequest(email, password)
                apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null) {
                                Toast.makeText(
                                    this@Login_activity,
                                    loginResponse.msg,
                                    Toast.LENGTH_SHORT
                                ).show()

                                // Handle login berhasil, redirect ke aktivitas NavBar
                                val intent = Intent(this@Login_activity, NavBar::class.java)
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            Toast.makeText(
                                this@Login_activity,
                                "Gagal Login",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(
                            this@Login_activity,
                            "Gagal Login: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        })
    }
}
