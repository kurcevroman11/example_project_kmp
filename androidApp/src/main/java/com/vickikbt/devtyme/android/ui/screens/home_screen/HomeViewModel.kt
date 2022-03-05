package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _currentUser: MutableState<CurrentUserDto?> = mutableStateOf(null)
    val currentUser: State<CurrentUserDto?> get() = _currentUser

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage: MutableState<String> = mutableStateOf("Unknown error")
    val errorMessage: State<String> get() = _errorMessage

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            try {
                val response = authRepository.getUserProfile()
                response.collectLatest {
                    _currentUser.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }
}
