package com.example.postapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postapi.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkpass = binding.checkbox
        binding.button.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Inputan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if(username == "admin" && password == "12345") {
                Toast.makeText(this, "Login berhasil ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DataActivity::class.java )
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login gagal username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        checkpass.setOnCheckedChangeListener{_, isChecked ->
            binding.password.transformationMethod = if(isChecked) null else android.text.method.PasswordTransformationMethod.getInstance()
        }

    }
}