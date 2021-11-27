package com.young.pdfreader.other

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import java.io.*


class RequestActivity : ComponentActivity() {
    private lateinit var filePath: String
    private val REQUEST_CODE = 0x1111
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    fun verifyStoragePermissions(activity: Activity?) {
        // Check if we have write permission
        activity?.let { act ->
            val permission = ActivityCompat.checkSelfPermission(
                act,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    act,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifyStoragePermissions(activity = this)
        filePath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/test.txt"
        setContent {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                TextButton(onClick = { readFile(filePath = filePath) }) {
                    Text(text = "Click Me")
                }
                TextButton(onClick = { writeFile(filePath = filePath) }) {
                    Text(text = "Write file")
                }
                Divider()
                Text(text = readFile(filePath = filePath))
            }
        }
    }

    fun openFile() {
        startActivityForResult(
            Intent(Intent.ACTION_OPEN_DOCUMENT).apply {

                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
                putExtra(
                    Intent.EXTRA_MIME_TYPES, arrayOf(
                        "application/pdf", // .pdf
                        "application/vnd.oasis.opendocument.text", // .odt
                        "text/plain" // .txt
                    )
                )
            },
            REQUEST_CODE
        )
    }

    fun readFile(filePath: String): String {
        //for ANR
        Thread.sleep(10000)
        var bufferedReader: BufferedReader? = null
        var tmp = StringBuilder()
        val file = File(filePath)
        if (file.exists()) {
            Log.i("RequestActivity", "$filePath File exist")
        }
        try {
            bufferedReader = BufferedReader(
                FileReader(file)
            )
            var line = bufferedReader.readLine()
            while (line != null) {
                repeat(10) {
                    tmp.append(line)
                }

                line = bufferedReader.readLine()
                Log.i("RequestActivity", "Result value $line")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return tmp.toString()
    }

    fun writeFile(filePath: String) {
        var bufferWriter: BufferedWriter? = null
        val file = File(filePath)
        if (file.exists()) {
            Log.i("RequestActivity", "File exist")
        }
        val desc =
            "Android 12 is our most personal OS ever, featuring dynamic color capabilities that can transform your experience based on your wallpaper and responsive motion that responds to your touch. Even the widgets have been given a facelift, with your favorite people always available right there on your home screen. And with a more spacious redesign, color contrast optimizations and new features to help those with low vision, Android 12 is designed to be accessible for even more users."
        try {
            bufferWriter = BufferedWriter(FileWriter(file))
            var line = bufferWriter.appendLine(desc)
            repeat(500) {
                line.append(desc)
            }
            Log.i("RequestActivity", "Result value $line")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (bufferWriter != null) {
                try {
                    bufferWriter.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }
}