package com.mango.persistence.repository

import com.mango.business.model.User
import com.mango.business.model.value.UserId
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val _users = mutableListOf(
        User(UserId("1"), "Natalia"),
        User(UserId("2"), "Lukasz"),
    )

    val users get() = _users.toList()

    fun getCurrentUser(): User = _users.first()

    fun getUser(userId: UserId) = _users.firstOrNull { it.id == userId }

    fun containsUser(userId: UserId) = getUser(userId) != null

    fun addUser(user: User) {
        _users.add(user)
    }
}
