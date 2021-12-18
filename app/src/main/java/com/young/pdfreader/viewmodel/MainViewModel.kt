package com.young.pdfreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Create by Young on 12/04/2021
 **/
class MainViewModel() : ViewModel() {
    var urlLiveData = MutableLiveData<String>()
    private val _message = MutableLiveData("Hello Compose UI")
    val message: LiveData<String> = _message

    enum class State {
        LOADING, SUCCESS, ERROR
    }

    private val _uiState = MutableStateFlow(State.LOADING)
    val uiState: StateFlow<State> = _uiState

}