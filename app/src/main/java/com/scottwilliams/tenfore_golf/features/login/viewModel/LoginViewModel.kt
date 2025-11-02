package com.scottwilliams.tenfore_golf.features.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scottwilliams.tenfore_golf.features.login.respository.AuthRepository
import com.scottwilliams.tenfore_golf.features.login.respository.FakeAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: AuthRepository = FakeAuthRepository() // replace with real
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui

    fun onEmailChange(value: String) {
        _ui.update { it.copy(email = value, error = null).validate() }
    }

    fun onPasswordChange(value: String) {
        _ui.update { it.copy(password = value, error = null).validate() }
    }

    fun signIn() {
        val current = _ui.value
        if (!current.isValid || current.isLoading) return

        _ui.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = repo.signIn(current.email.trim(), current.password)

            _ui.update { state ->
                result.fold(
                    onSuccess = { state.copy(isLoading = false, success = true) },
                    onFailure = { err -> state.copy(isLoading = false, error = err.message) }
                )
            }
        }
    }

    private fun LoginUiState.validate(): LoginUiState {
        val emailOk = email.contains("@") && email.contains(".")
        val passOk = password.length >= 6
        return copy(isValid = emailOk && passOk)
    }
}