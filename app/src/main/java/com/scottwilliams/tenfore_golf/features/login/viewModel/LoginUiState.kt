package com.scottwilliams.tenfore_golf.features.login.viewModel

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isValid: Boolean = false,
    val success: Boolean = false
)