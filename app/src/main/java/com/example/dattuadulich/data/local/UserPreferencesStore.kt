package com.example.dattuadulich.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Tạo DataStore gắn với Context, tên file lưu trên máy là "user_prefs"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesStore(private val context: Context) {//datastore lưu trữ trạng thái của ứng dụng

    // Tên "chìa khóa" để lưu/đọc giá trị isDarkMode
    companion object {
        val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    // Đọc: trả về Flow<Boolean>, tự động cập nhật khi gá trị thay đổi
    val darkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_DARK_MODE] ?: false // mặc định là Light mode
        }

    // Ghi: lưu giá trị mới vào file
    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_DARK_MODE] = enabled
        }
    }
}