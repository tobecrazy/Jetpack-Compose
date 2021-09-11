package com.young.pdfreader.other

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.young.pdfreader.R
import com.young.pdfreader.data.COMMON_REQUEST_CODE
import com.young.pdfreader.data.DOWNLOAD_REQUEST_CODE
import com.young.pdfreader.data.UPLOAD_REQUEST_CODE
import com.young.pdfreader.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityOtherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_other)
        binding.showBottomSheetDialog.setOnClickListener(this)
        binding.requestAct.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d("OtherActivity", "===>${view?.id}")
        if (view?.id == binding.showBottomSheetDialog.id) {
            CustomBottomSheetFragment.getInstance(this, null)
        } else if (view?.id == binding.requestAct.id) {
            val intent = Intent(this, RequestActivity::class.java)
            startActivityForResult(intent, COMMON_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            UPLOAD_REQUEST_CODE -> {
                Log.d("OtherActivity", "${requestCode}===>OtherActivity get upload result")
            }
            DOWNLOAD_REQUEST_CODE -> {
                Log.d("OtherActivity", "${requestCode}===>OtherActivity get download result")
            }
            COMMON_REQUEST_CODE -> {
                Log.d("OtherActivity", "${requestCode}===>OtherActivity normal activity result")
            }
            else -> {
                Log.d("OtherActivity", "${requestCode}===>OtherActivity other result")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}