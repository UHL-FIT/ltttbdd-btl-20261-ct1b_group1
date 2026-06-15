    package com.example.dattuadulich.ui.screen.setting

    import android.app.Application
    import androidx.lifecycle.AndroidViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.dattuadulich.data.local.UserPreferencesStore
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.flow.asStateFlow
    import kotlinx.coroutines.flow.update
    import kotlinx.coroutines.launch

    // Đổi từ ViewModel → AndroidViewModel để lấy được Context
    class SettingViewModel(app: Application) : AndroidViewModel(app) {

        // Khởi tạo UserPreferencesStore với context
        private val prefsStore = UserPreferencesStore(app.applicationContext)

        private val _uiState = MutableStateFlow(SettingUiState())
        val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

        init {
            // Đọc giá trị darkMode đã lưu từ DataStore khi app khởi động
            viewModelScope.launch {
                prefsStore.darkModeFlow.collect { savedValue ->
                    _uiState.update { it.copy(isDarkMode = savedValue) }
                }
            }
        }

        // Ghi vào DataStore thay vì chỉ update RAM
        fun updateDarkMode(enabled: Boolean) {
            viewModelScope.launch {
                prefsStore.setDarkMode(enabled)
                // darkModeFlow.collect bên trên sẽ tự cập nhật uiState
            }
        }

        fun updateNotifyPromo(enabled: Boolean) {
            _uiState.update { it.copy(notifyPromo = enabled) }
        }

        fun updateNotifyReminder(enabled: Boolean) {
            _uiState.update { it.copy(notifyReminder = enabled) }
        }

        fun updateNotifyWeather(enabled: Boolean) {
            _uiState.update { it.copy(notifyWeather = enabled) }
        }

        fun updateLanguage(language: String) {
            _uiState.update { it.copy(selectedLanguage = language) }
        }

        fun updateCurrency(currency: String) {
            _uiState.update { it.copy(currency = currency) }
        }
    }