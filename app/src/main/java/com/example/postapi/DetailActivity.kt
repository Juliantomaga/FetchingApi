package com.example.postapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.postapi.databinding.ActivityDetailBinding

private lateinit var binding : ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val useridText = binding.userid
        val titleText = binding.title
        val bodyText = binding.body


        val stringUser = intent.getStringExtra("userid")
        val stringTitle = intent.getStringExtra("title")
        val stringBody = intent.getStringExtra("body")


        useridText.text = "Userid : $stringUser"
        titleText.text = "Title : $stringTitle"
        bodyText.text = "Body : $stringBody"
    }
}