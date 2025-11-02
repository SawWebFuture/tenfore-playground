package com.scottwilliams.tenfore_golf.features.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scottwilliams.tenfore_golf.features.login.respository.AuthRepository

class LoginViewModelFactory(
    private val repo: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == LoginViewModel::class.java)
        return LoginViewModel(repo) as T
    }
}