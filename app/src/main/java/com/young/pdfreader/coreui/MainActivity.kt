package com.young.pdfreader.coreui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.layoutId
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.young.pdfreader.R
import com.young.pdfreader.coreui.animation.AnimationActivity
import com.young.pdfreader.coreui.base.BaseActivity
import com.young.pdfreader.coreui.base.CommonTopAppBar

import com.young.pdfreader.coreui.button.ButtonComponentActivity
import com.young.pdfreader.data.ComponentItems
import com.young.pdfreader.data.DateUtils
import com.young.pdfreader.coreui.dialog.DialogAndSnackbarActivity
import com.young.pdfreader.coreui.gesture.GestureActivity
import com.young.pdfreader.coreui.image.ImagesComponentActivity
import com.young.pdfreader.coreui.layout.LayoutActivity
import com.young.pdfreader.coreui.list.ListItemComponentActivity
import com.young.pdfreader.coreui.migrate.MigrateActivity
import com.young.pdfreader.coreui.opencv.OpenCVActivity
import com.young.pdfreader.coreui.other.OtherActivity
import com.young.pdfreader.coreui.text.TextComponentActivity
import com.young.pdfreader.coreui.toolbar.ToolbarComponentActivity
import com.young.pdfreader.ui.theme.PDFreaderTheme
import com.young.pdfreader.coreui.webview.WebViewActivity

class MainActivity : BaseActivity() {
    private val isDarkTheme = false

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //set Dark mode
            PDFreaderTheme(darkTheme = isDarkTheme) {
                Scaffold(Modifier.background(Color.White)) {
                    Column(
                        verticalArrangement = Arrangement.Top
                    ) {
                        CommonTopAppBar(
                            activity = this@MainActivity as Activity
                        )
                        val context = LocalContext.current
                        Tab(
                            selected = false,
                            onClick = { showToast(context = context, "Click on tab") },
                            modifier = Modifier.padding(1.dp)
                        ) {
                            val values = mutableListOf(
                                ComponentItems.OPENCV.name,
                                ComponentItems.WEBVIEW.name,
                                ComponentItems.MIGRATE.name,
                                ComponentItems.TEXT.name,
                                ComponentItems.IMAGE.name,
                                ComponentItems.BUTTON.name,
                                ComponentItems.LIST.name,
                                ComponentItems.DIALOG.name,
                                ComponentItems.TOOLBAR.name,
                                ComponentItems.ANIMATION.name,
                                ComponentItems.LAYOUT.name,
                                ComponentItems.GESTURE.name,
                                ComponentItems.THEMING.name,
                                ComponentItems.OTHER.name
                            )
                            ViewItems(
                                values = values, isDarkTheme
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CardComponent()
                            MyToast(msg = "Test By Young")
                        }
                    }

                }
            }
        }
    }


    /**
     *  Caution: Experimental APIs can change in the future or may be removed entirely.
     */
    @ExperimentalFoundationApi
    @Composable
    @Preview
    fun ViewItems(values: List<String>, isDarkTheme: Boolean = false) {
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            stickyHeader {
                //set header
                SetHeader()
            }
            //set list item
            items(values.size) {
                UIComponentItem(name = values[it], isDarkTheme = false)
            }
        }
    }

    @Composable
    @Preview
    fun SetHeader() {
        val context = LocalContext.current
        val resources: Resources = context.resources
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .alpha(0.9f)
                .wrapContentHeight()
                .fillMaxWidth()
                .layoutId(layoutId = "Header", tag = "Header name")
        ) {
            Text(
                text = resources.getQuantityString(R.plurals.sometimes_ago, 1),
                modifier = Modifier
                    .alpha(0.9f)
                    .padding(6.dp)
                    .background(Color.Blue, RoundedCornerShape(4.dp))
                    .shadow(elevation = 2.dp)
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .rotate(10f)
                    .scale(0.8f),
                color = Color.Red
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .size(300.dp)
                    .fillMaxHeight()
                    .padding(1.dp)
            )
            Text(
                text = resources.getString(
                    R.string.date,
                    DateUtils.getYear(),
                    DateUtils.getMonth(),
                    DateUtils.getDay()
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

    @Composable
    @Preview
    fun UIComponentItem(name: String, isDarkTheme: Boolean = false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            ShowItem(value = name, isDarkTheme)
            CustomButton(text = name)
        }
        Divider(color = Color.Blue, modifier = Modifier.height(2.dp))
        Spacer(modifier = Modifier.padding(8.dp))
    }

    @Composable
    fun ShowItem(value: String, isDarkTheme: Boolean = false) {
        Text(
            text = value,
            textAlign = TextAlign.Left,
            style = TextStyle(
                fontSize = 16.sp,
                color = if (isDarkTheme) Color.White else Color.Black
            ),
            color = if (isDarkTheme) Color.White else Color.Black,
            modifier = Modifier.padding(12.dp)
        )
    }

    @Composable
    @Preview
    fun CustomButton(text: String) {
        val context = LocalContext.current
        Button(
            onClick = {
                showToast(context, "Button clicked")
                navigateToActivity(context, text)
            },
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            Text(text = text)
        }
    }

    fun navigateToActivity(context: Context, text: String) {
        var targetIntent: Intent? = null
        when (text) {
            ComponentItems.OPENCV.name -> targetIntent =
                Intent(context, OpenCVActivity::class.java)

            ComponentItems.WEBVIEW.name -> targetIntent =
                Intent(context, WebViewActivity::class.java)

            ComponentItems.MIGRATE.name -> targetIntent =
                Intent(context, MigrateActivity::class.java)

            ComponentItems.TEXT.name -> targetIntent =
                Intent(context, TextComponentActivity::class.java)

            ComponentItems.IMAGE.name -> targetIntent =
                Intent(context, ImagesComponentActivity::class.java)

            ComponentItems.BUTTON.name -> targetIntent =
                Intent(context, ButtonComponentActivity::class.java)

            ComponentItems.DIALOG.name -> targetIntent =
                Intent(context, DialogAndSnackbarActivity::class.java)

            ComponentItems.LIST.name -> targetIntent =
                Intent(context, ListItemComponentActivity::class.java)

            ComponentItems.TOOLBAR.name -> targetIntent =
                Intent(context, ToolbarComponentActivity::class.java)

            ComponentItems.ANIMATION.name -> targetIntent =
                Intent(context, AnimationActivity::class.java)

            ComponentItems.GESTURE.name -> targetIntent =
                Intent(context, GestureActivity::class.java)

            ComponentItems.LAYOUT.name -> targetIntent =
                Intent(context, LayoutActivity::class.java)

            ComponentItems.OTHER.name -> targetIntent =
                Intent(context, OtherActivity::class.java)


        }
        targetIntent?.let {
            startActivity(context, it, null)
        }

    }

    @Composable
    fun MyToast(msg: String) {
        Snackbar(modifier = Modifier.padding(10.dp)) {
            Text(text = msg)
        }
    }

    private fun showToast(context: Context, msg: String) {
        val snackbar = Snackbar.make(context, window.decorView, msg, Snackbar.LENGTH_LONG)
//        val callback = object : Snackbar.Callback() {
//            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                Log.d("OtherActivity", "===>$event")
//                super.onDismissed(transientBottomBar, event)
//            }
//
//            override fun onShown(sb: Snackbar?) {
//                Log.d("OtherActivity", "===>on show")
//                super.onShown(sb)
//            }
//        }
//
//
//        snackbar.addCallback(callback)
        snackbar.view.postDelayed({
            snackbar.view.requestFocus()
            snackbar.view.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        }, 100)
        snackbar.show()

//        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


    @Composable
    @Preview
    fun CardComponent() {
        Card(
            shape = RoundedCornerShape(6.dp),
            backgroundColor = Color(0xFFFFA867.toInt()),
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Green)),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Simple Card",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(16.dp)
            )
            CustomButton("Card Button")
        }
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

    @Composable
    @Preview(showBackground = true)
    fun NewsRepo(name: String) {
        MaterialTheme {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                CustomButton("Done")
                Image(
                    painter = painterResource(id = R.drawable.opencv),
                    contentDescription = "Icon weather",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .fillMaxHeight()
                        .clip(shape = RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "123",
                    style = typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "456", style = typography.body1)
                Text(text = "789", style = typography.body2)
                Text(text = "Hello $name!", style = typography.button)
                Divider(color = Color.Red)
                CardComponent()
                Divider(color = Color.Blue)
                Row(modifier = Modifier.padding(50.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Icon weather",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .fillMaxHeight()
                            .clip(shape = RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "123",
                        style = typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = "456", style = TextStyle(color = Color.Blue, fontSize = 16.sp))
                    Text(text = "789", style = typography.body2)
                    Text(text = "Hello $name!", style = typography.button)
                }
            }
            Divider(color = Color.Black)

        }

    }


    @Preview(showBackground = true, name = "Text")
    @Composable
    fun DefaultPreview() {
        PDFreaderTheme {
            NewsRepo("Android")
        }
    }
}