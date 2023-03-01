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

    fun getCurrentUser(): User = _users.first()
}
