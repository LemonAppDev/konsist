package com.lemon.mango.domain.user.usecase

import com.lemon.mango.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class GetCurrentUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke() = userRepository.getCurrentUser()
}
