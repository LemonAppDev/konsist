package com.lemon.mango.data.user

import com.lemon.mango.domain.user.UserRepository
import com.lemon.mango.domain.user.model.User
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class UserRepositoryImpl(
    private val userJpaEntityToUserMapper: UserJpaEntityToUserMapper,
    private val userJpaRepository: UserJpaRepository,
    private val userToUserJpaEntityMapper: UserToUserJpaEntityMapper,
) : UserRepository {
    override fun saveUser(user: User): User = userJpaRepository
        .save(userToUserJpaEntityMapper(user))
        .let { userJpaEntityToUserMapper(it) }

    override fun getUser(userId: UserId) = userJpaRepository
        .findById(userId.value)
        .getOrNull()
        ?.let { userJpaEntityToUserMapper(it) }

    override fun getCurrentUser(): User {
        saveUser(User(UserId(UUID.randomUUID()), "John"))

        return userJpaRepository
            .findAll()
            .first()
            .let { userJpaEntityToUserMapper(it) }
    }

    override fun containsUser(userId: UserId) = userJpaRepository
        .existsById(userId.value)
}
