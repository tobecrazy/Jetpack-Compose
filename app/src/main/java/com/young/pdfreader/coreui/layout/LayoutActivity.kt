package com.young.pdfreader.coreui.layout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import coil.compose.rememberImagePainter
import com.young.pdfreader.R
import com.young.pdfreader.coreui.base.BaseActivity
import com.young.pdfreader.viewmodel.MainViewModel
import kotlinx.coroutines.launch

/**
 * All Layout
 * https://developer.android.google.cn/codelabs/jetpack-compose-layouts?continue=https%3A%2F%2Fdeveloper.android.google.cn%2Fcourses%2Fpathways%2Fcompose%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-layouts#2
 */
class LayoutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.padding(2.dp)) {
                LayoutOption()
                BoxLayout()
                SetUpListView()
            }
        }

    }

    @Composable
    fun BoxLayout() {
        Box(
            Modifier
                .height(80.dp)
                .width(100.dp)) {
            Text("This Box Layout", modifier = Modifier.align(Alignment.TopCenter))
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .height(50.dp)
                    .width(
                        100.dp
                    )
                    .background(Color.Blue)
            )
            Text("Box Layout", modifier = Modifier.align(Alignment.Center))
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp),
                onClick = {}
            ) {
                Text("+")
            }
        }
    }

    @Preview
    @Composable
    fun SetUpListView() {
        val context = LocalContext.current
        val viewModel = MainViewModel()
        if (viewModel.uiState.value == MainViewModel.State.LOADING) {
            RecyclerViewComposeUI("https://www.sap.cn/content/dam/application/shared/images/country-flags/cn@2x.png")
        }
        viewModel.urlLiveData.observe(this, Observer {
            if (it != null) {
//                RecyclerViewComposeUI(it)
            }
        })
    }

    @Composable
    fun RecyclerViewComposeUI(url: String) {
        val lazySize = 100
        val scrollState = rememberLazyListState()
        // We save the coroutine scope where our animated scroll will be executed
        val coroutineScope = rememberCoroutineScope()
        Column() {
            Row {
                Button(onClick = {
                    coroutineScope.launch {
                        // 0 is the first item index
                        scrollState.animateScrollToItem(0)
                    }
                }, modifier = Modifier.padding(6.dp)) {
                    Text("Scroll to the top")
                }

                Button(onClick = {
                    coroutineScope.launch {
                        // listSize - 1 is the last index of the list
                        scrollState.animateScrollToItem(lazySize - 1)
                    }
                }, modifier = Modifier.padding(6.dp)) {
                    Text("Scroll to the end")
                }
            }

            LazyColumn(state = scrollState, modifier = Modifier.padding(2.dp)) {
                items(lazySize) {
                    Text(text = "Item is #$it", modifier = Modifier.padding(2.dp))
                    CardView(
                        it,
                        url = url
                    )
                }
            }
        }
    }

    @Composable
    fun LayoutOption() {
        val context = LocalContext.current
        val items = mutableListOf(
            "Card Layout",
            "Tab Layout",
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
                                "Tab Layout" -> Intent(context, TabActivity::class.java)
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

    @Composable
    fun CardView(index: Int, url: String) {
        val context = LocalContext.current
        Row(
            Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
                .clickable(onClick = {
                    // set click event
                    Toast
                        .makeText(context, "Click on $index", Toast.LENGTH_SHORT)
                        .show()
                })
                .padding(16.dp)
        ) {
            // set placeholder
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = url
                    ),
                    contentDescription = url,
                    modifier = Modifier.size(480.dp)
                )


            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
                    .horizontalScroll(rememberScrollState())
            ) {
                Text("Compose UI CardView Title is #$index", fontWeight = FontWeight.Bold)
                // LocalContentAlpha
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("Compose UI", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}