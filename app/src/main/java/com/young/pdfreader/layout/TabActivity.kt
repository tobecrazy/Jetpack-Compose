package com.young.pdfreader.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class TabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val titles = listOf("TAB 1", "TAB 2", "TAB 3")
            titles.forEach {
                CustomTab(selected = true, it)
            }
        }
    }


    @Composable
    fun CustomTab(selected: Boolean, title: String) {
        val content = LocalContext.current
        Tab(selected = selected, onClick = { /*TODO*/ }) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(color = if (selected) Color.Red else Color.LightGray)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

            }

        }

    }
}