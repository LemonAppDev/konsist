package com.mango.domain.user.usecase

import com.mango.data.user.UserRepository
import org.springframework.stereotype.Service

@Service
class GetCurrentUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke() = userRepository.getCurrentUser()
}
