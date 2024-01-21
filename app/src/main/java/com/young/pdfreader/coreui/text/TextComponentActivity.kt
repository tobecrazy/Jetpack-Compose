package com.young.pdfreader.coreui.text

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
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
                DisplayResourceText(id = R.string.current_year, 2021)
                DisplayResourceText(id = R.string.text_in_compose)
                Spacer(modifier = Modifier.height(10.dp))
                DisplayText(message = "Display String Directly")
                MultipleStylesInText()
                ClickAText()
                Spacer(modifier = Modifier.height(10.dp))
                SelectableText()
                Spacer(modifier = Modifier.height(10.dp))
                ClickWithAnnotation()
                Spacer(modifier = Modifier.height(10.dp))
                InputText(message = "TextField")
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
            overflow = TextOverflow.Visible

        )
    }

    @Composable
    fun DisplayResourceText(id: Int) {
        //Display string from string resource
        val message = stringResource(id = id)
        DisplayText(message = message)
    }

    @Composable
    fun DisplayResourceText(id: Int, any: Any) {
        //Display string from string resource
        val message = stringResource(id = id, any)
        DisplayText(message = message)
    }

    @Composable
    fun MultipleStylesInText() {
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue, fontStyle = FontStyle.Normal)) {
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
                .padding(10.dp),
            textAlign = TextAlign.Justify
        )
    }

    @Composable
    fun SelectableText() {
        val combineDecoration = listOf(TextDecoration.Underline, TextDecoration.LineThrough)
        SelectionContainer() {
            Column() {
                Text(
                    text = stringResource(id = R.string.text_in_compose),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = TextDecoration.LineThrough
                )
                DisableSelection {
                    Text(
                        text = stringResource(id = R.string.text_not_selectable),
                        color = Color.Red,
                        textDecoration = TextDecoration.combine(combineDecoration)
                    )
                }
                Text(
                    text = stringResource(id = R.string.text_selectable),
                    textDecoration = TextDecoration.Underline, maxLines = 2
                )
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
    fun InputText(message: String) {
        val context = LocalContext.current

        var text by remember {
            mutableStateOf(message)
        }

        var password by rememberSaveable {
            mutableStateOf(message)
        }

        Column() {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Label $text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(2.dp), maxLines = 2,
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Outline Label") },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), maxLines = 2,
                textStyle = TextStyle(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(2.dp), maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onAny = {
                    showToast(context = context, password)
                }),
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                ),
                shape = RoundedCornerShape(6.dp)
            )

        }


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