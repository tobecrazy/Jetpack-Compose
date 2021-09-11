package com.young.pdfreader.data

enum class ComponentItems(name: String) {
    TEXT("text component"),
    IMAGE("image component"),
    BUTTON("button component"),
    DIALOG("dialog and snackbar component"),
    LAYOUT("layout"),
    TOOLBAR("toolbar component"),
    LIST("list"),
    GESTURE("gesture"),
    ANIMATION("animation"),
    THEMING("theming"),
    OTHER("other")
}

val UPLOAD_REQUEST_CODE = 0x1001
val DOWNLOAD_REQUEST_CODE = 0x1002
val COMMON_REQUEST_CODE = 0x1111