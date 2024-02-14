package com.example.techjet.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techjet.R
import com.example.techjet.model.DataResponse
import com.example.techjet.model.Item
import com.example.techjet.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var context: Context

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var dataResponse: DataResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppSharedPreference.init(applicationContext)
        context = this@MainActivity
        val recyclerView: RecyclerView = findViewById(R.id.rv_list)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        dataResponse = AppSharedPreference.getResponse()
        if(dataResponse?.items?.isEmpty() == true){

            mainActivityViewModel.getflickrData()?.observe(this, Observer {
                if(it.items.isNotEmpty()) {
                    AppSharedPreference.saveResponse(it)
                    showAdapter(recyclerView)
                }
            })
        }else{
            showAdapter(recyclerView)
        }

    }

    private fun showAdapter(recyclerView: RecyclerView) {
        dataResponse = AppSharedPreference.getResponse()
        val customAdapter =
            dataResponse?.items?.toMutableList()?.let { it1 -> ShowAdapter(it1, context, this) }
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
    }

    override fun onItemClick(item: Item?) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("key", item?.link)
        startActivity(intent)
    }
}
