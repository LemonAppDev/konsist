package com.mango.domain.user.usecase

import com.mango.domain.user.UserRepository
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class CheckUserIdUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userId: UserId) {
        check(userRepository.containsUser(userId)) { "User with id: $userId doesn't exist" }
    }
}
