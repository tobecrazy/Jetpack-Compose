package com.young.pdfreader.other

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.young.pdfreader.R
import com.young.pdfreader.data.COMMON_REQUEST_CODE
import com.young.pdfreader.data.DOWNLOAD_REQUEST_CODE
import com.young.pdfreader.data.UPLOAD_REQUEST_CODE

class TranslucentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translucent)
        initialBottomSheet()
    }

    private fun initialBottomSheet() {
        CustomBottomSheetFragment.getInstance(this, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            UPLOAD_REQUEST_CODE -> {
                Log.d("OtherActivity", "${requestCode}===>TranslucentActivity get upload result")
            }
            DOWNLOAD_REQUEST_CODE -> {
                Log.d("OtherActivity", "${requestCode}===>TranslucentActivity get download result")
            }
            COMMON_REQUEST_CODE -> {
                Log.d("OtherActivity", "${requestCode}===>TranslucentActivity normal activity result")
            }
            else -> {
                Log.d("OtherActivity", "${requestCode}===>TranslucentActivity other result")
            }
        }
        if (null != data) {
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}