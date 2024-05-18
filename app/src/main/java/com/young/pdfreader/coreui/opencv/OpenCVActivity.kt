package com.young.pdfreader.coreui.opencv

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.young.pdfreader.R
import com.young.pdfreader.coreui.base.BaseActivity
import com.young.pdfreader.databinding.ActivityOpenCvactivityBinding

class OpenCVActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityOpenCvactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenCvactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imagesResize.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.images_resize) {
            Toast.makeText(this, "Resize Image", Toast.LENGTH_SHORT).show()
            val newParams = binding.imagesResize.layoutParams
            newParams.width = resources.getDimensionPixelSize(R.dimen.common_200dp)
            newParams.height = resources.getDimensionPixelSize(R.dimen.common_200dp)
            binding.imagesResize.layoutParams = newParams
        }
    }
}