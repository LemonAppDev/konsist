package com.mango.domain.user.usecase

import com.mango.domain.user.UserFactory
import com.mango.domain.user.UserRepository
import com.mango.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(
    private val userRepository: UserRepository,
    private val userFactory: UserFactory,
) {
    operator fun invoke(userName: String): User {
        require(userName.isNotBlank()) { "userName is blank" }
        val user = userFactory(userName)
        return userRepository.saveUser(user)
    }
}
