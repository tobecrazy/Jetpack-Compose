package com.young.pdfreader.coreui.base

import android.app.Activity
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
 * Create by i321533 (young.liu@sap.com) on 03/19/2022
 **/
@Composable
fun CommonTopAppBar(activity: Activity) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.title), maxLines = 1)
        },
        navigationIcon = {
            val context = LocalContext.current
            IconButton(onClick = {
                BaseUI.showToast(context = context, "Click Close button")
                activity.finish()
            }) {
                ResourceIcon()
            }

        },
        actions = {
            val context = LocalContext.current
            IconButton(onClick = {
                BaseUI.showToast(context = context, "Click Add button")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
    )
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

