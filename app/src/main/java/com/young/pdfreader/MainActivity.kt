package com.young.pdfreader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.core.content.ContextCompat.startActivity
import com.young.pdfreader.data.ComponentItems
import com.young.pdfreader.image.ImagesComponentActivity
import com.young.pdfreader.text.TextComponentActivity
import com.young.pdfreader.ui.theme.PDFreaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PDFreaderTheme(darkTheme = false) {
                Scaffold(Modifier.background(Color.White)) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top
                    ) {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.title), maxLines = 1)
                            },
                            navigationIcon = {
                                ResourceIcon()
                            }
                        )
                        ViewItems(
                            values = mutableListOf(
                                ComponentItems.TEXT.name,
                                ComponentItems.IMAGE.name,
                                ComponentItems.BUTTON.name,
                                ComponentItems.LAYOUT.name,
                                ComponentItems.ANIMATION.name,
                                ComponentItems.LIST.name,
                                ComponentItems.TOOLBAR.name,
                                ComponentItems.GESTURE.name
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }
            }
        }
    }
}

@Composable
fun ViewItems(values: List<String>) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        values.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                ShowItem(value = it)
                CustomButton(text = it)
            }
            Divider(color = Color.Blue, modifier = Modifier.height(2.dp))
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ShowItem(value: String) {
    Text(
        text = value,
        textAlign = TextAlign.Left,
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
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
        ComponentItems.TEXT.name -> targetIntent =
            Intent(context, TextComponentActivity::class.java)
        ComponentItems.IMAGE.name -> targetIntent = Intent(context,ImagesComponentActivity::class.java)

    }
    targetIntent?.let {
        startActivity(context, it, null)
    }

}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}


@Composable
fun FloatingButton() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { showToast(context = context, "Floating button clicked") },
        modifier = Modifier
            .padding(8.dp),
        backgroundColor = Color.Green
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Button")
    }
}

@Composable
fun BottomBarComponent() {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(Modifier.weight(1f, true))
//        IconButton(onClick = { Icon(imageVector = Icons.Filled.Favorite,contentDescription = "Favorite")}) {
//        }
//        IconButton(onClick = { Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")}) {
//        }
        ResourceIcon()
        ResourceIcon()
    }
}


@Composable
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
    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
//    Icon(
//        painter = painterResource(R.drawable.ic_description_24),
//        contentDescription = "print"
//    )
}

@Composable
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