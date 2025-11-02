package com.scottwilliams.tenfore_golf.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottwilliams.tenfore_golf.R
import com.scottwilliams.tenfore_golf.features.login.viewModel.LoginUiState
import com.scottwilliams.tenfore_golf.features.login.viewModel.LoginViewModel

@Composable
fun LoginRoute(
    onSuccess: () -> Unit = {},
    vm: LoginViewModel = viewModel()
) {
    val state by vm.ui.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.onEmailChange("demo@acme.com")
        vm.onPasswordChange("password")
    }

    LaunchedEffect(state.success) {
        if (state.success) onSuccess()
    }

    LoginScreen(
        state = state,
        onEmail = vm::onEmailChange,
        onPassword = vm::onPasswordChange,
        onSubmit = vm::signIn
    )
}

@Composable
fun LoginScreen(
    state: LoginUiState,
    onEmail: (String) -> Unit,
    onPassword: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.tenfore_logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(Modifier.height(56.dp))

        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = onEmail,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = onPassword,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        if (state.error != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                state.error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onSubmit,
            enabled = state.isValid && !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Sign In")
            }
        }
    }
}
