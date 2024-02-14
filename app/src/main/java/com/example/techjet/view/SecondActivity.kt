package com.example.techjet.view

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.techjet.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val webView = findViewById<WebView>(R.id.wv)

        val urlView = intent.getStringExtra("key")
        if (urlView != null) {
            webView.loadUrl(urlView)
        }
    }


}
