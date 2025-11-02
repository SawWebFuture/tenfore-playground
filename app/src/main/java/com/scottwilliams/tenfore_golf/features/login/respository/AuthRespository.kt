package com.scottwilliams.tenfore_golf.features.login.respository

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
}

