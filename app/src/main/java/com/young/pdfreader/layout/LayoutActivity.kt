package com.young.pdfreader.layout

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.young.pdfreader.R

/**
 * All Layout
 */
class LayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutOption()
        }

    }

    @Composable
    fun LayoutOption() {
        val context = LocalContext.current
        val items = mutableListOf(
            "Card Layout",
            "Constraint Layout",
            "Scaffold Layout",
            "Scrollable Tab Layout"
        )
        Column() {
            Image(
                painter = painterResource(id = R.drawable.basic),
                contentDescription = "",
                modifier = Modifier.padding(10.dp)
            )
            items.forEach {
                Button(modifier = Modifier.padding(10.dp),
                    onClick = {
                        val intent: Intent =
                            when (it) {
                                "Card Layout" -> Intent(context, CardLayoutActivity::class.java)
                                "Constraint Layout" -> Intent(
                                    context,
                                    ConstraintLayoutActivity::class.java
                                )
                                "Scaffold Layout" -> Intent(
                                    context,
                                    ScaffoldLayoutActivity::class.java
                                )
                                "Scrollable Tab Layout" -> Intent(
                                    context,
                                    ScaffoldLayoutActivity::class.java
                                )
                                else -> Intent(context, CardLayoutActivity::class.java)
                            }
                        startActivity(intent)
                    }) {
                    Text(text = it)
                }
            }
        }
    }
}