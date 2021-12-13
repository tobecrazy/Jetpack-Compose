package com.young.pdfreader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Create by Young on 12/12/2021
 **/
class PDFReaderViewModelFactory : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the given `Class`
    </T> */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}