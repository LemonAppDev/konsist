package com.mango.business.usecase.user

import com.mango.business.factory.UserFactory
import com.mango.business.model.User
import com.mango.persistence.repository.UserRepository
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
