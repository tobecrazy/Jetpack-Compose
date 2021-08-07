package com.young.pdfreader.button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.databinding.DataBindingUtil
import com.young.pdfreader.R
import com.young.pdfreader.databinding.ActivityButtonComponentBinding

class ButtonComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivityButtonComponentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_button_component
        )
        binding.buttonComposeView.setContent {

        }
    }

    @Composable
    fun CustomButton() {
        Button(onClick = {}) {

        }
    }
}