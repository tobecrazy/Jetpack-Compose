package com.young.pdfreader.button

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.young.pdfreader.base.BaseActivity
import com.young.pdfreader.base.BaseUI
import com.young.pdfreader.viewmodel.MainViewModel


class ButtonComponentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                BaseUI.initialTopAppBar(this@ButtonComponentActivity)
                Column(modifier = Modifier.padding(10.dp)) {
                    Row() {
                        Column() {
                            CustomButton(isEnable = true, text = "Click Me Enable")
                            CustomButton(isEnable = false, text = "Click Me Disable")
                        }
                        Column() {
                            FloatingButton(
                                isAddButton = true,
                                message = "Floating add button clicked"
                            )
                            FloatingButton(
                                isFilledType = false,
                                isAddButton = false,
                                message = "Floating edit button clicked"
                            )
                        }
                    }
                    CustomExtendFloatingButton()
                    CustomTextButton()
                    CustomCheckBox(isEnable = true, description = "Checkbox demo")
                    CustomCheckBox(isEnable = false, description = "Checkbox demo disable")
                    CustomTriStateCheckBox(isEnable = true)
                    CustomIconButton()
                    CustomRadioButton()
                    SwitchButton()
                    CustomOutLinedButton()
                }
            }
        }
    }

    @Composable
    fun CustomButton(isEnable: Boolean, text: String) {
        Button(onClick = {}, enabled = isEnable, modifier = Modifier.padding(10.dp)) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text)
        }

    }

    @Composable
    fun CustomTextButton() {
        val context = LocalContext.current
        Text(text = "Text buttons are typically used for less-pronounced actions, including those located in dialogs and cards. In cards, text buttons help maintain an emphasis on card content.")
        TextButton(
            onClick = {
                showToast(context = context, "You clicked TextButton")
            },
            enabled = true,
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.textButtonColors()
        ) {
            Text(text = "Learn more")
        }
    }

    @Composable
    fun FloatingButton(isFilledType: Boolean = true, isAddButton: Boolean, message: String) {
        val context = LocalContext.current
        val viewModel = MainViewModel()

        var count by remember {
            mutableStateOf(1)
        }
        FloatingActionButton(
            onClick = {
                showToast(context = context, "$message times $count")
                count ++
            },
            modifier = Modifier
                .padding(8.dp),
            backgroundColor = Color.Green,
            elevation = FloatingActionButtonDefaults.elevation()
        ) {
            if (isAddButton) Icon(
                imageVector = if (isFilledType) Icons.Filled.Add else Icons.Outlined.Add,
                contentDescription = "Add Button"
            ) else
                Icon(
                    imageVector = if (isFilledType) Icons.Filled.Edit else Icons.Outlined.Edit,
                    contentDescription = "Edit Button", tint = Color.Red
                )
        }
    }


    @Composable
    fun CustomExtendFloatingButton() {
        /**
         * The extended FAB is wider than a regular FAB, and it includes a text label.
         */
        val context = LocalContext.current

        ExtendedFloatingActionButton(
            icon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            text = { Text(text = "SEE ALL Results", color = Color.White, maxLines = 1) },
            onClick = {
                showToast(
                    context = context,
                    " The extended FAB is wider than a regular FAB, and it includes a text label."
                )
            },
            shape = MaterialTheme.shapes.medium,
            backgroundColor = Color.Black
        )
    }

    @Composable
    fun CustomCheckBox(isEnable: Boolean, description: String? = null) {
        val context = LocalContext.current
        val checkState = remember { mutableStateOf(true) }
        Row() {
            Checkbox(
                checked = checkState.value, onCheckedChange = {
                    showToast(context = context, "Check status is ${checkState.value}")
                    checkState.value = it
                },
                modifier = Modifier.padding(10.dp),
                enabled = isEnable, colors = CheckboxDefaults.colors()
            )
            description?.let {
                Text(text = it, modifier = Modifier.padding(10.dp))
            }
        }
    }

    @Composable
    fun CustomTriStateCheckBox(isEnable: Boolean) {
        val context = LocalContext.current
        val (childrenState1, onStateChanged1) = remember { mutableStateOf(true) }
        val (childrenState2, onStateChanged2) = remember { mutableStateOf(true) }
        val (childrenState3, onStateChanged3) = remember { mutableStateOf(true) }
        val parentState = remember(childrenState1, childrenState2, childrenState3) {
            if (childrenState1 && childrenState2 && childrenState3) ToggleableState.On
            else if (!childrenState1 && !childrenState2 && !childrenState3) ToggleableState.Off
            else {
                ToggleableState.Indeterminate
            }
        }
        val onParentClick = {
            val isOn = parentState != ToggleableState.On
            onStateChanged1(isOn)
            onStateChanged2(isOn)
            onStateChanged3(isOn)
        }
        Row() {
            TriStateCheckbox(parentState, onParentClick, colors = CheckboxDefaults.colors())
            Text(text = "Parent Checkbox", modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
        }

        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
            Row() {
                Checkbox(checked = childrenState1, onCheckedChange = onStateChanged1)
                Text(text = "Checkbox 1", modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
            }

            Row() {
                Checkbox(checked = childrenState2, onCheckedChange = onStateChanged2)
                Text(text = "Checkbox 2", modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
            }

            Row() {
                Checkbox(checked = childrenState3, onCheckedChange = onStateChanged3)
                Text(text = "Checkbox 3", modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
            }

        }
    }

    @Composable
    fun CustomIconButton() {
        val context = LocalContext.current
        var isChecked by remember { mutableStateOf(true) }
        Row() {
            IconButton(
                onClick = { showToast(context = context, "Click On Icon button") },
                enabled = true, modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.Favorite, "")
            }

            IconToggleButton(checked = isChecked, onCheckedChange = {
                isChecked = it
            }) {
                val tint by animateColorAsState(targetValue = if (isChecked) Color.Green else Color.Red)
                Icon(Icons.Default.Favorite, "", tint = tint)
            }
        }

    }

    @Composable
    fun CustomRadioButton() {
        val radioOptions = mutableListOf("Apple", "Huawei", "OPPO", "VIVO", "XiaoMi")
        val (optionSelected, onOptionSelected) = remember {
            mutableStateOf(radioOptions[0])
        }

        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp, 0.dp, 0.dp)
                .selectableGroup()
        ) {
            radioOptions.forEach {
                Row() {
                    RadioButton(selected = it == optionSelected, onClick = { onOptionSelected(it) })
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

            }

        }


    }


    @Composable
    fun SwitchButton() {
        var state by remember {
            mutableStateOf(true)
        }
        Switch(checked = state, onCheckedChange = { state = it }, colors = SwitchDefaults.colors())
    }

    @Composable
    fun CustomOutLinedButton() {
        val context = LocalContext.current
        OutlinedButton(onClick = { showToast(context = context, "Click on outline button") }) {
            Text(
                text = "Learn More",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = Color.Blue
            )

        }
    }

    private fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}