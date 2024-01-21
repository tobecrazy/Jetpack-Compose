package com.young.pdfreader.utils

import android.app.Activity
import android.content.Intent

/**
 * Create by Young on 11/28/2021
 **/
class FileUtils {
    fun openFile(activity: Activity, requestCode: Int) {
        activity.startActivityForResult(
            Intent(Intent.ACTION_OPEN_DOCUMENT).apply {

                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
                putExtra(
                    Intent.EXTRA_MIME_TYPES, arrayOf(
                        "application/pdf", // .pdf
                        "application/vnd.oasis.opendocument.text",
                        "text/plain" // .txt
                    )
                )
            },
            requestCode
        )
    }
}