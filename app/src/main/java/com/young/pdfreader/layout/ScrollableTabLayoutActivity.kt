package com.young.pdfreader.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable

/**
 *  ScrollableTab / tab/ slider
 */
class ScrollableTabLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { }

    }

    @Composable
    fun CustomTabLayout() {
        Tab(selected = true, onClick = {  }) {


        }
    }
}