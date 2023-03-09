package com.mango.data.user

import com.mango.domain.user.model.User
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class UserRepository(
    private val userJpaRepository: UserJpaRepository,
    private val userToUserJpaEntityMapper: UserToUserJpaEntityMapper,
    private val userJpaEntityToUserMapper: UserJpaEntityToUserMapper,
) {
    fun saveUser(user: User): User = userJpaRepository
        .save(userToUserJpaEntityMapper(user))
        .let { userJpaEntityToUserMapper(it) }

    fun getUser(userId: UserId) = userJpaRepository
        .findById(userId.value)
        .getOrNull()
        ?.let { userJpaEntityToUserMapper(it) }

    fun getCurrentUser(): User {
        saveUser(User(UserId(UUID.randomUUID()), "John"))

        return userJpaRepository
            .findAll()
            .first()
            .let { userJpaEntityToUserMapper(it) }
    }

    fun containsUser(userId: UserId) = userJpaRepository
        .existsById(userId.value)
}
