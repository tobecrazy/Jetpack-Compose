package com.young.pdfreader.coreui.list

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Create by Young on 08/15/2021
 **/
data class Items(
    val title: String,
    val description: String,
    val iconId: ImageVector?,
    val contentDescription: String?,
    val overLineText: String
)
