package com.young.pdfreader.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Animation
 * https://developer.android.google.cn/jetpack/compose/animation
 */
class AnimationActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                AnimationInOut(true, true, true)
                Spacer(modifier = Modifier.height(2.dp))
                Divider()
                AnimationInOut(false, true, false)
                Spacer(modifier = Modifier.height(2.dp))
                Divider()
                AnimationState()
                Divider()
                ExpandText(
                    "SizeTransform",
                    "SizeTransform defines how the size should animate between the initial and the target contents. You have access to both the initial size and the target size when you are creating the animation. SizeTransform also controls whether the content should be clipped to the component size during animations."
                )
            }
        }

    }

    @ExperimentalAnimationApi
    @Composable
    fun AnimationInOut(isHorizon: Boolean, isExpandIn: Boolean, isShrinkOut: Boolean) {
        var editable by remember { mutableStateOf(true) }
        val density = LocalDensity.current
        Column() {
            TextButton(
                onClick = { editable = !editable },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = if (editable) "Compose Fade in" else "Compose Fade out")
            }
            AnimatedVisibility(
                visible = editable,
                enter = if (isHorizon) slideInHorizontally() + if (isExpandIn) expandIn() else fadeIn() else slideInVertically() + if (isExpandIn) expandIn() else fadeIn(),
                exit = if (isHorizon) slideOutHorizontally() + if (isShrinkOut) shrinkOut() else fadeOut() else slideOutVertically() + if (isShrinkOut) shrinkOut() else fadeOut()
            ) {
                Text(
                    text = "Animation Up/Down Left/Right",
                    modifier = Modifier
                        .padding(8.dp),
                    fontSize = 22.sp
                )
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun AnimationState() {
        val state = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }
        Column {
            AnimatedVisibility(visibleState = state, enter = fadeIn(), exit = fadeOut()) {
                Box(modifier = Modifier.background(Color.LightGray)) {
                    val background by transition.animateColor(label = "") { it ->
                        if (it == EnterExitState.Visible) Color.Red else Color.Blue
                    }
                    val b by transition.animateInt(label = "") {
                        if (it == EnterExitState.PostExit) 0 else 100
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .animateEnterExit(
                                enter = slideInVertically(),
                                exit = slideOutHorizontally()
                            )
                            .sizeIn(minWidth = 200.dp, minHeight = 200.dp)
                            .padding(60.dp)
                            .background(
                                background
                            )
                    ) {

                    }
                }
                Text(modifier = Modifier.padding(10.dp), text = "Animation Visibility")
            }
            var message by remember {
                mutableStateOf("Hello World Compose UI")
            }
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .animateContentSize()
            )
            {
                Text(text = message, modifier = Modifier.padding(10.dp))
            }

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .animateContentSize(),
                text = when {
                    state.isIdle && state.currentState -> "Visible"
                    !state.isIdle && state.currentState -> "Disappearing"
                    state.isIdle && !state.currentState -> "Invisible"
                    else -> "Appearing"
                }
            )

            var count by remember { mutableStateOf(0) }
            Button(onClick = { count++ }, modifier = Modifier.padding(6.dp)) {
                Text("Add")
            }
            Row() {
                AnimatedContent(targetState = count, transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically({ fullHeight -> fullHeight }) + fadeIn() with slideOutVertically(
                            { fullHeight -> -fullHeight }) + fadeOut()
                    } else {
                        slideInVertically({ fullHeight -> -fullHeight }) + fadeIn() with slideOutVertically(
                            { fullHeight -> fullHeight }) + fadeOut()
                    }.using(SizeTransform(clip = false))

                }) {
                    Row() {
                        Text(text = "Up/Down ", modifier = Modifier.padding(10.dp))
                        Box(modifier = Modifier.background(Color.LightGray)) {
                            Text(
                                text = "$it",
                                modifier = Modifier.padding(10.dp),
                                color = Color.Red
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(6.dp))
                AnimatedContent(targetState = count, transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally({ fullHeight -> fullHeight }) + fadeIn() with slideOutHorizontally(
                            { fullHeight -> -fullHeight }) + fadeOut()
                    } else {
                        slideInHorizontally({ fullHeight -> -fullHeight }) + fadeIn() with slideOutHorizontally(
                            { fullHeight -> fullHeight }) + fadeOut()
                    }.using(SizeTransform(clip = false))

                }) {
                    Row() {
                        Text(text = "Left/Right ", modifier = Modifier.padding(10.dp))
                        Box(modifier = Modifier.background(Color.LightGray)) {
                            Text(
                                text = "$it",
                                modifier = Modifier.padding(10.dp),
                                color = Color.Blue
                            )
                        }
                    }
                }
            }

        }
    }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun ExpandText(shortMessage: String, fullMessage: String) {
        var expand by remember { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colors.primary,
            onClick = { expand = !expand },
            modifier = Modifier.padding(8.dp)
        ) {
            AnimatedContent(targetState = expand, transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                    fadeOut(animationSpec = tween(150)) using SizeTransform { initialSize, targetSize ->
                    if (targetState) {
                        keyframes {
                            IntSize(targetSize.width, targetSize.height) at 150
                            durationMillis = 300
                        }
                    } else {
                        keyframes {
                            IntSize(initialSize.width, initialSize.height) at 150
                            durationMillis = 300
                        }
                    }
                }
            }) { targetState ->
                if (targetState) Text(
                    text = fullMessage,
                    fontSize = 16.sp
                ) else Text(text = shortMessage, fontSize = 16.sp)
            }
        }

    }
}