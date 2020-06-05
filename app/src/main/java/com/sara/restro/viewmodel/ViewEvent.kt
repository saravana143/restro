package com.sara.restro.viewmodel

import com.sara.restro.model.Beer

sealed class ViewEvent {
    object Loading : ViewEvent()
    data class Success(val result: List<Beer>) : ViewEvent()
    data class Error(val result: List<Beer>) : ViewEvent()
}