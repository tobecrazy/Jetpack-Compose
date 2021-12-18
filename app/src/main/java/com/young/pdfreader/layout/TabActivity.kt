package com.young.pdfreader.layout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.young.pdfreader.base.BaseActivity

class TabActivity : BaseActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTab()
        }
    }


    @Composable
    fun CustomTab() {
        val context = LocalContext.current
        var tabIndex by remember { mutableStateOf(0) }
        val titles = listOf("TAB 1", "TAB 2", "TAB 3")
        val icons = listOf(Icons.Outlined.Home, Icons.Outlined.Favorite, Icons.Outlined.Person)

        TabRow(selectedTabIndex = tabIndex) {
            titles.forEachIndexed { index, text ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = text
                        )
                    },
                    text = { Text(text = text) },
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.LightGray,
                )
            }
        }

    }
}