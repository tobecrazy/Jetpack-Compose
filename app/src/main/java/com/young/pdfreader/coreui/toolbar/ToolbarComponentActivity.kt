package com.young.pdfreader.coreui.toolbar


import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.pdfreader.coreui.base.BaseActivity
import kotlinx.coroutines.launch

class ToolbarComponentActivity : BaseActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                CustomToolBar("Toolbar")
                CustomBottomBar()
                CustomBottomNavigation()
                CustomDropDownMenu()
                CustomBottomSheetScaffold("First title", "Second title")
            }
        }
    }

    @Composable
    fun CustomToolBar(title: String) {
        TopAppBar(title = { Text(text = title) }, navigationIcon = {
            IconButton(onClick = { finish() }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            }
        },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                }
            },
            backgroundColor = Color.Green,
            elevation = 2.dp
        )
    }

    @Composable
    fun CustomBottomBar() {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.Transparent
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }

    @Composable
    fun CustomBottomNavigation() {
        val icons = listOf(
            Icons.Filled.Favorite,
            Icons.Filled.Home,
            Icons.Filled.Search,
            Icons.Filled.Person
        )
        val tabItems = mutableListOf("Favorites", "Music", "News", "Person")
        var selectedItem by remember { mutableStateOf(0) }
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Green,
            contentColor = Color.Blue
        ) {
            tabItems.forEachIndexed { index, item ->
                BottomNavigationItem(selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    icon = {
                        Icon(
                            imageVector = icons.get(index = index),
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }, label = {
                        Text(
                            item,
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                )
            }
        }
    }

    @ExperimentalMaterialApi
    @Composable
    fun CustomBottomSheetScaffold(title: String, subTitle: String) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()
        BottomSheetScaffold(
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = title)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(64.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = subTitle)
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = { scope.launch { scaffoldState.bottomSheetState.collapse() } }) {
                        Text(text = "Click to collapse")
                    }
                }
            },
            scaffoldState = scaffoldState,
            topBar = {
                CustomToolBar(title = title)
            },
            floatingActionButton = {
                var clickCount by remember {
                    mutableStateOf(0)
                }

                FloatingActionButton(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Snackbar ===> ${++clickCount}")
                    }
                }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            sheetPeekHeight = 130.dp,
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Drawer content")
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = { scope.launch { scaffoldState.drawerState.close() } }) {
                        Text(text = "Click to close drawer")
                    }

                }
            }, backgroundColor = Color.LightGray
        ) {

        }

    }

    @Composable
    fun CustomDropDownMenu() {
        val context = LocalContext.current
        var expandStatus by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        ) {
            IconButton(onClick = { expandStatus = true }) {
                Icon(Icons.Default.MoreVert, "Expand", tint = Color.Blue)
            }
            DropdownMenu(expanded = expandStatus, onDismissRequest = { expandStatus = false }) {
                DropdownMenuItem(onClick = {
                    showToast(context = context, "Refresh")
                }) {
                    Text(text = "Refresh")
                }
                DropdownMenuItem(onClick = {
                    showToast(context = context, "Settings")
                }) {
                    Text(text = "Settings")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    showToast(context = context, "Send Feedback")
                }) {
                    Text(text = "Send Feedback")
                }
            }
        }

    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}