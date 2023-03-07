package com.mango.business.usecase.user

import com.mango.business.model.value.UserId
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CheckUserIdUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userId: UserId) {
        check(userRepository.containsUser(userId)) { "User with id: $userId doesn't exist" }
    }
}
