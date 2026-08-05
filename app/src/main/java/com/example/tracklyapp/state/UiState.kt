package com.example.tracklyapp.state

import com.example.tracklyapp.data.entity.Card

sealed class UiState {
    object Loading : UiState()

    data class Success(
        val cards: List<Card>
    ) : UiState()

    data class Error(
        val message: String = "error"
    ) : UiState()
}