package com.young.pdfreader.text

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.pdfreader.R

class TextComponentActivity : AppCompatActivity() {
    lateinit var composeView: ComposeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_component)
        supportActionBar
        composeView = findViewById(R.id.compose_view)
        composeView.setContent {
            Column {
                DisplayResourceText(id = R.string.text_in_compose)
                Spacer(modifier = Modifier.height(10.dp))
                DisplayText(message = "Display String Directly")
                MultipleStylesInText()
                ClickAText()
                Spacer(modifier = Modifier.height(10.dp))
                SelectableText()
                Spacer(modifier = Modifier.height(10.dp))
                ClickWithAnnotation()

            }
        }
    }


    @Composable
    fun DisplayText(message: String) {
        Text(
            text = message,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold
            ),
            fontSize = 20.sp,
            maxLines = 3,
            color = Color.DarkGray,
            overflow = TextOverflow.Ellipsis

        )
    }

    @Composable
    fun DisplayResourceText(id: Int) {
        //Display string from string resource
        val message = stringResource(id = id)
        DisplayText(message = message)
    }

    @Composable
    fun MultipleStylesInText() {
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("I")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Red)) {
                append(" love ")
            }
            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, color = Color.Magenta))
            {
                append(" you")
            }
        }, fontSize = 30.sp)
    }

    @Composable
    fun ClickAText() {
        val context = LocalContext.current
        val txt = remember {
            mutableStateOf(0)
        }
        Text(
            text = "Click Me ${txt.value} times",
            fontSize = 18.sp,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color.LightGray)
                .clickable(
                    enabled = true,
                    onClick = {
                        showToast(context = context, "Click on text ${txt.value} times!")
                        txt.value += 1
                    })
                .padding(10.dp)
        )
    }

    @Composable
    fun SelectableText() {
        SelectionContainer() {
            Column() {
                Text(
                    text = stringResource(id = R.string.text_in_compose),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                DisableSelection {
                    Text(
                        text = stringResource(id = R.string.text_not_selectable),
                        color = Color.Red
                    )
                }
                Text(text = stringResource(id = R.string.text_selectable), maxLines = 2)
            }
        }
    }

    @Composable
    fun ClickWithAnnotation() {
        val context = LocalContext.current
        val annotatedStr = buildAnnotatedString {
            append("Click")
            pushStringAnnotation(
                tag = "URL",
                annotation = "https://developer.android.com/jetpack/compose/text"
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("Here")
            }
            pop()
        }
        ClickableText(text = annotatedStr, onClick = { offset ->
            annotatedStr.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let {
                showToast(context = context, "Click on URL is ${it.item}")
            }
            showToast(context = context, "Click on $offset")

        })
    }

    @Composable
    fun InputText(){
        
    }

    fun showToast(context: Context, message: String) {
        Toast
            .makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            )
            .show()
    }
}