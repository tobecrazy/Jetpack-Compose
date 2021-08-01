package com.young.pdfreader.text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.young.pdfreader.R

class TextComponentActivity : AppCompatActivity() {
    lateinit var composeView:ComposeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_component)
        supportActionBar
        composeView = findViewById(R.id.compose_view)
        composeView.setContent {
            DisplayText(message = "Hello world")
        }
    }
    
    @Composable
    fun DisplayText(message:String){
        Text(message)
    }
}