package com.example.postapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.TextView

class DataAdapter(private val context: Context, private val itemClickListener: (List<String>) -> Unit) : BaseAdapter() {
    private val data = mutableListOf<List<String>>()
    override fun getCount(): Int = data.size
    override fun getItem(position: Int): Any = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {

        val view = convertview ?: LayoutInflater.from(context).inflate(R.layout.activity_data, parent, false)
        val holder = ViewHolder(view)

        val rowdata = data[position]
        holder.userid.text = "Userid : " + rowdata[0]
        holder.title.text =  "Title :" +rowdata[1]
        holder.body.text = "Body : " + rowdata[2]

        view.setOnClickListener {
            itemClickListener(rowdata)
        }

        return view
    }

    fun addData(rowdata: List<String>) {
        data.add(rowdata)
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) {
    val userid: TextView = view.findViewById(R.id.userid)
    val title: TextView = view.findViewById(R.id.title)
    val body: TextView = view.findViewById(R.id.body)

    init {
        userid.isFocusable = true
        title.isFocusable = true
        body.isFocusable = true
    }
}
