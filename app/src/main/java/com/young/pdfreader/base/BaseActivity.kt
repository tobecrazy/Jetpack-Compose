package com.young.pdfreader.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.young.pdfreader.R
import com.young.pdfreader.ResourceIcon
import com.young.pdfreader.showToast

/**
 * Create by Young on 12/18/2021
 **/
open class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setUpTopAppBar()
        }
    }

    @Composable
    private fun setUpTopAppBar() {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title), maxLines = 1)
            },
            navigationIcon = {
                val context = LocalContext.current
                IconButton(onClick = {
                    showToast(context = context, "Click Close button")
                    finish()
                }) {
                    ResourceIcon()
                }

            },
            actions = {
                val context = LocalContext.current
                IconButton(onClick = {
                    showToast(context = context, "Click Add button")
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "")
                }
            }
        )
    }
}