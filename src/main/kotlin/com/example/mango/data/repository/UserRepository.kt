package com.example.mango.data.repository

import com.example.mango.data.model.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val users = mutableListOf(
        User(1, "Natalia", "abcd"),
        User(2, "Lukasz", "abc"),
    )

    fun getUser(authToken: String): User = users.first {
        it.authToken == authToken
    }

    fun getUser(userId: Int): User = users.first {
        it.id == userId
    }
}
