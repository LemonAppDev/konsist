package com.mango.persistence.repository

import com.mango.business.model.User
import com.mango.business.model.value.UserId
import com.mango.persistence.datasource.UserJpaRepository
import com.mango.persistence.entity.mapper.UserJpaEntityToUserMapper
import com.mango.persistence.entity.mapper.UserToUserJpaEntityMapper
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class UserRepository(
    private val userJpaRepository: UserJpaRepository,
    private val userToUserJpaEntityMapper: UserToUserJpaEntityMapper,
    private val userJpaEntityToUserMapper: UserJpaEntityToUserMapper,
) {
    val users
        get() = userJpaRepository
            .findAll()
            .map { userJpaEntityToUserMapper(it) }

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
