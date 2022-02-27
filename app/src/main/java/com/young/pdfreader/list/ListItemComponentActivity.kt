package com.young.pdfreader.list


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.pdfreader.base.BaseActivity

class ListItemComponentActivity : BaseActivity()  {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = mutableListOf(
                Items(
                    "Title",
                    "This component can be used to achieve the list item templates existing in the spec",
                    Icons.Rounded.Menu,
                    null,
                    "This is a long overLine Text  for the current list item"
                ), Items(
                    "text -- Title",
                    "This component can be used to achieve the list item templates existing in the spec",
                    Icons.Rounded.Favorite,
                    null,
                    "This is a long overLine Text  for the current list item"
                ), Items(
                    "text -- Title ",
                    "Secondary Text -- This component can be used to achieve the list item templates existing in the spec",
                    Icons.Rounded.Person,
                    null,
                    "overLine Text -- This is a long overLine Text  for the current list item"
                ), Items(
                    "text -- Title",
                    "This component can be used to achieve the list item templates existing in the spec",
                    Icons.Rounded.Email,
                    null,
                    "This is a long overLine Text  for the current list item"
                ), Items(
                    "Title",
                    "This component can be used to achieve the list item templates existing in the spec",
                    Icons.Rounded.Face,
                    null,
                    "This is a long overLine Text  for the current list item"
                ), Items(
                    "Title",
                    "This component can be used to achieve the list item templates existing in the spec",
                    Icons.Rounded.Call,
                    null,
                    "This is a long overLine Text  for the current list item"
                )
            )
            ListItems(list = list)
        }
    }

    @ExperimentalMaterialApi
    @Composable
    fun ListItems(list: MutableList<Items>) {
        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.Start,


            ) {
            list.forEach {
                /** ONE LINE**/
                var checkState by remember {
                    mutableStateOf(true)
                }
                ListItem(
                    modifier = Modifier.toggleable(
                        value = checkState,
                        onValueChange = { checkState = it }), icon = {
                        it.iconId?.let { iconId ->
                            Icon(
                                imageVector = iconId,
                                contentDescription = it.contentDescription,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(48.dp)
                            )
                        }
                    }, text = {
                        Text(
                            text = it.description,
                            style = TextStyle(
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp
                            ),
                            maxLines = 1
                        )
                    }, trailing = {
                        Switch(checked = checkState, onCheckedChange = { checkState = it })
                    }
                )
                Divider(modifier = Modifier.height(2.dp))


                /**
                 * Two lines item**/

                ListItem(modifier = Modifier.clickable { }, icon = {
                    it.iconId?.let { iconId ->
                        Icon(
                            imageVector = iconId,
                            contentDescription = it.contentDescription,
                            modifier = Modifier
                                .padding(10.dp)
                                .size(48.dp)
                        )
                    }
                }, secondaryText = {
                    Text(
                        text = it.description,
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            fontSize = 12.sp
                        ),
                        maxLines = 3
                    )

                }, singleLineSecondaryText = true, text = {
                    Text(
                        text = it.title,
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        ),
                        maxLines = 1
                    )
                })
                Divider(modifier = Modifier.height(2.dp))

                /**
                 * Three lines item**/

                ListItem(modifier = Modifier.clickable { }, icon = {
                    it.iconId?.let { iconId ->
                        Icon(
                            imageVector = iconId,
                            contentDescription = it.contentDescription,
                            modifier = Modifier.padding(10.dp).size(48.dp))
                    }
                }, secondaryText = {
                    Text(text = it.description, style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            fontSize = 12.sp
                        ), maxLines = 3
                    )

                }, singleLineSecondaryText = false, text = {
                    Text(
                        text = it.title,
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        ),
                        maxLines = 1
                    )
                }, overlineText = {
                    Text(text = it.overLineText)
                }, trailing = {
                    var checkState by remember { mutableStateOf(true) }
                    Checkbox(checked = checkState, onCheckedChange = { checkState = it })
                })
                Divider(modifier = Modifier.height(2.dp))
            }
        }
    }
}