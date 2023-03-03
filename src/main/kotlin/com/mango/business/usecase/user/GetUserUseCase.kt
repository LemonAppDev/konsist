package com.mango.business.usecase.user

import com.mango.business.model.User
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userId: UserId): User {
        val user = userRepository.getUser(userId)
        requireNotNull(user) { "User with id: $userId doesn't exist" }

        return user
    }
}
