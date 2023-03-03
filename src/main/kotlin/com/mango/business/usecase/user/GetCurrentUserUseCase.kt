package com.mango.business.usecase.user

import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetCurrentUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke() = userRepository.getCurrentUser()
}
