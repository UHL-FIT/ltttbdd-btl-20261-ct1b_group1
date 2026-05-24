package com.example.dattuadulich.ui.screen.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

    fun updateNotifyPromo(enabled: Boolean) {
        _uiState.update { it.copy(notifyPromo = enabled) }
    }

    fun updateNotifyReminder(enabled: Boolean) {
        _uiState.update { it.copy(notifyReminder = enabled) }
    }

    fun updateNotifyWeather(enabled: Boolean) {
        _uiState.update { it.copy(notifyWeather = enabled) }
    }

    fun updateDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
    }

    fun updateLanguage(language: String) {
        _uiState.update { it.copy(selectedLanguage = language) }
    }

    fun updateCurrency(currency: String) {
        _uiState.update { it.copy(currency = currency) }
    }
}
