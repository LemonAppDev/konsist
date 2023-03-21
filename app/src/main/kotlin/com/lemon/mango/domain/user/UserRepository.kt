package com.lemon.mango.domain.user

import com.lemon.mango.domain.user.model.User
import com.lemon.mango.domain.user.model.UserId

interface UserRepository {
    fun saveUser(user: User): User
    fun getUser(userId: UserId): User?
    fun getCurrentUser(): User
    fun containsUser(userId: UserId): Boolean
}
