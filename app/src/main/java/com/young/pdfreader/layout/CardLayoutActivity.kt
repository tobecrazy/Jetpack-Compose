package com.young.pdfreader.layout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.pdfreader.R
import com.young.pdfreader.showToast

/**
 * For Card / ModalBottomSheetLayout
 */
class CardLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = "",
                    modifier = Modifier.padding(10.dp)
                )

                CustomCard(
                    title = "Rick and Morty",
                    "An animated series that follows the exploits of a super scientist and his not-so-bright grandson.",
                    "Rate this album"
                )
            }

        }

    }

    @Composable
    fun CustomCard(title: String, description: String, ratingTitle: String) {
        val content = LocalContext.current
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable(enabled = true, onClickLabel = "Clicked", role = null, onClick = {
                    Toast
                        .makeText(content, "Click on Card view", Toast.LENGTH_SHORT)
                        .show()
                }),
            elevation = 2.dp
        ) {
            Column() {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.bmp_image),
                        contentDescription = "Image",
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        modifier = Modifier.padding(
                            start = 20.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 12.dp
                        )
                    ) {
                        Text(
                            text = title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(text = description, modifier = Modifier.padding(4.dp))
                    }

                }
                Divider(modifier = Modifier.height(1.dp))
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = ratingTitle,
                        maxLines = 4,
                        style = TextStyle(color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.weight(2f)
                    ) {
                        repeat(5) {
                            val context = LocalContext.current
                            IconButton(onClick = {
                                showToast(context = context, "Click on rating star")
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = "Star"
                                )
                            }

                        }
                    }

                }

            }

        }
    }
}