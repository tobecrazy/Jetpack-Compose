package com.young.pdfreader.other

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.young.pdfreader.R

class DownloadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        val downloadBtn = findViewById<AppCompatButton>(R.id.close_download_btn)
        downloadBtn.setOnClickListener {
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    Log.d("OtherActivity", "===>finish  === downloadActivity")
                    this.setResult(Activity.RESULT_OK)
                    finish()
                }, 2000)
            }
        }
    }

    override fun onResume() {
        Log.d("OtherActivity", "===>onResume  === DownloadActivity")
        super.onResume()
    }


    override fun onStop() {
        Log.d("OtherActivity", "===>onStop  === DownloadActivity")
        this.setResult(Activity.RESULT_CANCELED)
        super.onStop()
    }
}