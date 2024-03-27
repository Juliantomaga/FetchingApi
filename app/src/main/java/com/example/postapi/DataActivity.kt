package com.example.postapi

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.loader.content.AsyncTaskLoader
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private lateinit var datalayout : ListView
private lateinit var adapter : DataAdapter

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        datalayout = findViewById(R.id.datalayout)
        adapter = DataAdapter(this) {data ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("userid", data[0])
            intent.putExtra("title", data[1])
            intent.putExtra("body", data[2])
            startActivity(intent)
        }

        datalayout.adapter = adapter
        fetchingApi()
    }

    private fun fetchingApi() {
      AsyncTask.execute {
          try {
              val url = URL("https://jsonplaceholder.typicode.com/posts")
              val conn = url.openConnection() as HttpURLConnection
              conn.requestMethod = "GET"

              val responcode = conn.responseCode
              if(responcode == HttpURLConnection.HTTP_OK) {
                  val reader = BufferedReader(InputStreamReader(conn.inputStream))
                  val response = StringBuilder()
                  var line : String?

                  while(reader.readLine().also{line = it}!= null) {
                        response.append(line)
                  }

                  reader.close()

                  val jsonArray = JSONArray(response.toString())
                  if(jsonArray.length() > 0) {
                      for(i in 0  until jsonArray.length()) {
                          val jsonObject = jsonArray.getJSONObject(i)
                          val userid = jsonObject.getString("userId")
                          val title = jsonObject.getString("title")
                          val body = jsonObject.getString("body")


                          runOnUiThread {
                              adapter.addData(listOf(userid,title,body))
                          }
                      }
                  } else {
                      runOnUiThread {
                          showToast("gagal cokk")
                      }
                  }
              }
          } catch (e:Exception) {
              runOnUiThread {
              showToast("ana masalah pas fetching api ${e.message}")
              }
          }
      }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}