package com.scottwilliams.tenfore_golf.features.login.respository

import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {
    override suspend fun signIn(email: String, password: String): Result<Unit> {
        delay(1200)
        return if (email == "demo@acme.com" && password == "password") {
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("Invalid email or password"))
        }
    }
}