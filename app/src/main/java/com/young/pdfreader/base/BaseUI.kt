package com.young.pdfreader.base

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.young.pdfreader.R

/**
 * Create by Young on 12/18/2021
 **/
object BaseUI {

    @Composable
    fun initialTopAppBar(activity: Activity) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title), maxLines = 1)
            },
            navigationIcon = {
                val context = LocalContext.current
                IconButton(onClick = {
                    showToast(context = context, "Click Close button")
                    activity.finish()
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

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    @Composable
    fun ResourceIcon() {
        val desc = stringResource(id = R.string.a11y_close)
        Icon(imageVector = Icons.Filled.Close, contentDescription = desc)
//    Icon(
//        painter = painterResource(R.drawable.ic_description_24),
//        contentDescription = "print"
//    )
    }

}