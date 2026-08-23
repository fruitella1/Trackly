package com.example.tracklyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracklyapp.data.entity.Activity
import com.example.tracklyapp.data.entity.Card
import com.example.tracklyapp.data.repository.TracklyRepository
import com.example.tracklyapp.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

class TracklyViewModel(private val tracklyRepository: TracklyRepository) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    val state: StateFlow<UiState> = _state.asStateFlow()

    private val _activities = MutableStateFlow<List<Activity>>(emptyList())

    val activities: StateFlow<List<Activity>> = _activities.asStateFlow()

    private val _duration = MutableStateFlow<Map<Int, Int>>(emptyMap())

    val duration: StateFlow<Map<Int, Int>> = _duration.asStateFlow()

    fun addCard(card: Card) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                tracklyRepository.insert(card)
                _state.value = UiState.Success(getCardList())
            } catch (e: Exception) {
                _state.value = UiState.Error()
            }
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                tracklyRepository.delete(card)
                _state.value = UiState.Success(getCardList())
            } catch (e: Exception) {
                _state.value = UiState.Error()
            }
        }
    }

    fun updateCard(card: Card) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                tracklyRepository.update(card)
                _state.value = UiState.Success(getCardList())
            } catch (e: Exception) {
                _state.value = UiState.Error()
            }
        }
    }

    fun loadCards() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                _state.value = UiState.Success(getCardList())
            } catch (e: Exception) {
                _state.value = UiState.Error()
            }
        }
    }

    fun addActivity(activity: Activity) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                tracklyRepository.add(activity)
                _state.value = UiState.Success(getCardList())
            } catch (e: Exception) {
                _state.value = UiState.Error()
            }
        }
    }

    private suspend fun getCardList(): List<Card> {
        return tracklyRepository.getAllCards()
    }

    fun getActivities(cardId: Int) {
        viewModelScope.launch {
            _activities.value = tracklyRepository.getAllActivity(cardId)
        }
    }

    fun loadTotalDuration(cards: List<Card>) {
        viewModelScope.launch {
            val today = LocalDate.now()
            val startDate = today.with(DayOfWeek.MONDAY).toString()
            val result = mutableMapOf<Int, Int>()
            cards.forEach { card->
                result[card.id] = tracklyRepository.getTotalDuration(card.id,startDate)
            }
            _duration.value = result
        }
    }
}