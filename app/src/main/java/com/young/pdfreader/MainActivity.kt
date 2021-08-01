package com.young.pdfreader

import android.content.Context
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.pdfreader.ui.theme.PDFreaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PDFreaderTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    NewsRepo("Young")
//                }

                Scaffold(Modifier.background(Color.White)) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top
                    ) {
                        TopAppBar(
                            title = {
                                Text(text = "Page title", maxLines = 2)
                            },
                            navigationIcon = {
                                ResourceIcon()
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        FloatingButton()
                        NewsRepo("Young")
                        Spacer(modifier = Modifier.height(10.dp))
                        BottomBarComponent()
                    }

                }
            }
        }


    }
}

@Composable
fun CustomButton(text: String) {
    val context = LocalContext.current
    Button(
        onClick = { showToast(context, "Button clicked") }, modifier = Modifier
            .height(50.dp)
            .width(100.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun FloatingButton() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { showToast(context = context, "Floating button clicked") },
        modifier = Modifier.padding(8.dp)
    ) {

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

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
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

    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
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