package com.young.pdfreader.other

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.young.pdfreader.R
import com.young.pdfreader.data.COMMON_REQUEST_CODE
import com.young.pdfreader.data.DOWNLOAD_REQUEST_CODE
import com.young.pdfreader.data.TRANSLUCENT_REQUEST_CODE
import com.young.pdfreader.data.UPLOAD_REQUEST_CODE
import com.young.pdfreader.databinding.ActivityOtherBinding
import com.young.pdfreader.viewmodel.MainViewModel
import com.young.pdfreader.viewmodel.PDFReaderViewModelFactory

class OtherActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityOtherBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_other)
        setUpViewModel()
        binding.showBottomSheetDialog.setOnClickListener(this)
        binding.requestAct.setOnClickListener(this)
        binding.translucentBtn.setOnClickListener(this)
        binding.snackbarBtn.setOnClickListener(this)
        binding.tvContentDesc.contentDescription = getString(R.string.title)
        binding.textviewContentDesc.contentDescription = getString(R.string.app_name)
        binding.tvContentDesc.postDelayed(Runnable {
            binding.tvContentDesc.contentDescription = null
        }, 1000)

        binding.textviewContentDesc.postDelayed(Runnable {
//            binding.textviewContentDesc.requestFocus()
            binding.textviewContentDesc.sendAccessibilityEvent(
                AccessibilityEvent.TYPE_VIEW_FOCUSED
            )
        }, 800)

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewModel.setProgress(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.switchBtn.text = "On"

        binding.switchBtn.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                binding.seekbar.progress = 50
                binding.slider.value = 0.0f
            } else {
                binding.seekbar.progress = 0
                binding.slider.value = 60.0f
            }
        }
        binding.seekbarValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
//                    val progressValue = p0.toString().toInt()
//                    viewModel.setProgress(progressValue)
//                    binding.seekbar.progress = progressValue
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        viewModel.progress.observe(this, Observer {
            if (it in 0..100) {
                binding.seekbar.progress = it
                binding.seekbarValue.setText("$it%")
            } else {
                binding.seekbar.progress = 0
                binding.seekbarValue.setText("0%")
            }
        })
    }

    private fun setUpViewModel() {
        viewModel = PDFReaderViewModelFactory().create(MainViewModel::class.java)
    }

    override fun onClick(view: View?) {
        Log.d("OtherActivity", "===>${view?.id}")
        when (view?.id) {
            binding.showBottomSheetDialog.id -> {
                CustomBottomSheetFragment.getInstance(this, null)
            }
            binding.requestAct.id -> {
                val intent = Intent(this, RequestActivity::class.java)
                startActivityForResult(intent, COMMON_REQUEST_CODE)
            }
            binding.translucentBtn.id -> {
                val intent = Intent(this, TranslucentActivity::class.java)
                startActivityForResult(intent, TRANSLUCENT_REQUEST_CODE)
            }
            binding.snackbarBtn.id -> {
                val bottomView = binding.snackbarBtn
//                val bottomView = this.window.decorView.rootView
                var snackbar = Snackbar.make(
                    bottomView,
                    "Show Toast,click to show bottom sheet dialog!",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("Show") {
                    CustomBottomSheetFragment.getInstance(this, null)
                }
                val callback = object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        Log.d("OtherActivity", "===>$event")
                        super.onDismissed(transientBottomBar, event)
                    }

                    override fun onShown(sb: Snackbar?) {
                        Log.d("OtherActivity", "===>on show")
                        super.onShown(sb)
                    }
                }

                snackbar.addCallback(callback)
                snackbar.show()
            }
            else -> {

            }
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