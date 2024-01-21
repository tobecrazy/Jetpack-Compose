package com.young.pdfreader.coreui.opencv

import android.os.Bundle
import com.young.pdfreader.coreui.base.BaseActivity
import com.young.pdfreader.databinding.ActivityOpenCvactivityBinding

class OpenCVActivity : BaseActivity() {
    private lateinit var binding: ActivityOpenCvactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenCvactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}