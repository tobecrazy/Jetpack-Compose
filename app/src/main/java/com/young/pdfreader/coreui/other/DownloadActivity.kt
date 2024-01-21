package com.young.pdfreader.coreui.other

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.young.pdfreader.R

class DownloadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        val downloadBtn = findViewById<AppCompatButton>(R.id.close_download_btn)
        val textViewDesc = findViewById<AppCompatTextView>(R.id.tv_content_desc)
        textViewDesc.setText(R.string.app_name)
        textViewDesc.setTextAppearance(R.style.TextAppearance_AppCompat_Body1)
        downloadBtn.setTextAppearance(R.style.TextAppearance_AppCompat_Button)
        downloadBtn.setOnClickListener {
            textViewDesc.setTextAppearance(R.style.TextAppearance_AppCompat_Caption)
            textViewDesc.setText(R.string.migrate_desc)
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    Log.d("OtherActivity", "===>finish  === downloadActivity")
                    this.setResult(Activity.RESULT_OK)
                    finish()
                }, 10000)
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