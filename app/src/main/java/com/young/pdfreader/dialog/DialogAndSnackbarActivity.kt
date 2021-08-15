package com.young.pdfreader.dialog


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog


class DialogAndSnackbarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                Button(onClick = { }) {
                    Text(text = "Dialog1")

                }

                ProgressBarIndicator()
            }

        }
    }
    
    @Composable
    fun CustomDialog(){
        Dialog(onDismissRequest = { /*TODO*/ }) {

        }
    }
    @Composable
    fun CustomAlertDialog(){
//        AlertDialog(onDismissRequest = { /*TODO*/ }) {
//            Text(text = "Alert Dialog")
//        }
    }

    @Composable
    fun ProgressBarIndicator(){
        CircularProgressIndicator()
        CircularProgressIndicator(progress = 1f)
    }
}
