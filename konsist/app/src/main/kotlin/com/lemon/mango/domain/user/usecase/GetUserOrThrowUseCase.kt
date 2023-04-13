package com.lemon.mango.domain.user.usecase

import com.lemon.mango.domain.user.UserRepository
import com.lemon.mango.domain.user.model.User
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class GetUserOrThrowUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userId: UserId): User {
        val user = userRepository.getUser(userId)
        requireNotNull(user) { "User with id: $userId doesn't exist" }
        return user
    }
}
