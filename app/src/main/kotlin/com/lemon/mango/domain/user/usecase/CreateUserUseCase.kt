package com.lemon.mango.domain.user.usecase

import com.lemon.mango.domain.user.UserFactory
import com.lemon.mango.domain.user.UserRepository
import com.lemon.mango.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(
    private val userFactory: UserFactory,
    private val userRepository: UserRepository,
) {
    operator fun invoke(userName: String): User {
        require(userName.isNotBlank()) { "userName is blank" }
        val user = userFactory(userName)
        return userRepository.saveUser(user)
    }
}
