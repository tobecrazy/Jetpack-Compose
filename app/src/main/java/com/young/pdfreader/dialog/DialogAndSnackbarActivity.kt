package com.young.pdfreader.dialog


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.young.pdfreader.base.BaseActivity
import kotlinx.coroutines.launch


class DialogAndSnackbarActivity : BaseActivity()  {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showDialog1 by remember { mutableStateOf(false) }
            var showDialog2 by remember { mutableStateOf(false) }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                CustomSlider()
                Divider()
                Row() {
                    Button(onClick = { showDialog1 = true }, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Dialog1")
                    }
                    if (showDialog1) {
                        AlertDialog(onDismissRequest = { showDialog1 = false },
                            title = { Text(text = "My Dialog") },
                            text = { Text(text = "This is an AlertDialog demo") },
                            confirmButton = {
                                TextButton(onClick = { showDialog1 = false }) {
                                    Text(text = "Confirm")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog1 = false }) {
                                    Text(text = "Cancel")
                                }
                            }
                        )
                    }

                    Button(onClick = { showDialog2 = true }, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Dialog2")
                    }

                    if (showDialog2) {
                        AlertDialog(onDismissRequest = { showDialog2 = false },
                            buttons = {
                                Row(horizontalArrangement = Arrangement.Center) {
                                    Button(
                                        onClick = { showDialog2 = false },
                                        modifier = Modifier.padding(10.dp),
                                    ) {
                                        Text(text = "Yes")
                                    }
                                    Button(
                                        onClick = { showDialog2 = false },
                                        modifier = Modifier.padding(10.dp)
                                    ) {
                                        Text(text = "No")
                                    }
                                    Button(
                                        onClick = { showDialog2 = false },
                                        modifier = Modifier.padding(10.dp)
                                    ) {
                                        Text(text = "Abort")
                                    }
                                }

                            },
                            title = { Text(text = "My Dialog 2") },
                            text = { Text(text = "This is a three button dialog demo") }
                        )
                    }
                }
                LinearProgressBarIndicator()
                ProgressBarIndicator()
                Divider()
//              CustomSnackBar(message = "This is a snack bar", "This is snackbar body")
                Divider()

            }
        }
    }

    @Composable
    fun CustomSnackBar(message: String, body: String) {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Snackbar(modifier = Modifier.padding(10.dp)) {
            Text(text = message)
        }
        /**
         * This components provides only the visuals of the Snackbar.
         * If you need to show a Snackbar with defaults on the screen,
         * use ScaffoldState.snackbarHostState and SnackbarHostState.showSnackbar:
         */
        Scaffold(scaffoldState = scaffoldState, floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(text = { Text(text = "Floating button") },
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = "Snack bar label ${++clickCount}",
                            duration = SnackbarDuration.Short
                        )
                    }
                })
        }, content = { it ->
            Text(
                text = body,
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .wrapContentSize()
            )
        }, snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(2.dp, MaterialTheme.colors.secondary)
                )
            }
        })

    }


    @Composable
    fun ProgressBarIndicator() {
        var progress by remember {
            mutableStateOf(0.1f)
        }
        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )
        Row() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    progress = animatedProgress,
                    color = Color.Red,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(10.dp),
                    strokeWidth = 20.dp
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedButton(onClick = { if (progress < 1f) progress += 0.1f }) {
                    Text(text = "Increase")

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            /**
             * Progress indicators express an unspecified wait time or display the length of a process.
             */
            CircularProgressIndicator(
                color = Color.Green,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(10.dp),
                strokeWidth = 15.dp
            )

        }


    }

    @Composable
    fun LinearProgressBarIndicator() {
        var progress by remember {
            mutableStateOf(0.1f)
        }
        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )
        Row() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LinearProgressIndicator(
                    progress = animatedProgress,
                    color = Color.Red,
                    modifier = Modifier
                        .height(10.dp)
                        .width(200.dp)
                        .padding(2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedButton(onClick = { if (progress < 1f) progress += 0.1f }) {
                    Text(text = "Increase")

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            /**
             * Progress indicators express an unspecified wait time or display the length of a process.
             */
            LinearProgressIndicator(
                color = Color.Blue,
                modifier = Modifier
                    .height(10.dp)
                    .width(200.dp)
                    .padding(2.dp),
                backgroundColor = Color.White
            )

        }
    }

    @ExperimentalMaterialApi
    @Composable
    fun CustomSlider() {
        var sliderPosition by remember { mutableStateOf(0f) }
        Column() {
            Text(text = sliderPosition.toInt().toString(), modifier = Modifier.padding(6.dp))
            Slider(
                value = sliderPosition, onValueChange = { sliderPosition = it },
                valueRange = 0f..100f,
                onValueChangeFinished = {

                }, steps = 10,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.primary,
                    activeTickColor = MaterialTheme.colors.secondary
                )
            )

            var sliderPositions by remember { mutableStateOf(0f..100f) }
            Text(text = sliderPositions.toString(), modifier = Modifier.padding(6.dp))
            RangeSlider(
                values = sliderPositions,
                onValueChange = { sliderPositions = it },
                valueRange = 0f..100f,
                onValueChangeFinished = {

                }, steps = 5,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.primary,
                    activeTickColor = MaterialTheme.colors.secondary
                )
            )
        }

    }
}
