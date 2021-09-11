package com.young.pdfreader.other

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.young.pdfreader.R

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        val uploadBtn = findViewById<AppCompatButton>(R.id.close_upload_btn)
        uploadBtn.setOnClickListener {
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    Log.d("OtherActivity", "===>finish  === uploadActivity")
                    this.setResult(Activity.RESULT_CANCELED)
                    finish()
                }, 2000)
            }
        }
    }

    override fun onResume() {
        Log.d("OtherActivity", "===>Resume  === uploadActivity")
        super.onResume()
    }


    override fun onStop() {
        Log.d("OtherActivity", "===>onStop  === uploadActivity")
        this.setResult(Activity.RESULT_CANCELED)
        super.onStop()
    }

}