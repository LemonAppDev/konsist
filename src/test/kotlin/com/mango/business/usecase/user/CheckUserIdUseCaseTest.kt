package com.mango.business.usecase.user

import com.mango.business.model.value.UserId
import com.mango.persistence.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckUserIdUseCaseTest {
    private val userRepository: UserRepository = mockk()

    private val sut = CheckUserIdUseCase(
        userRepository,
    )

    @Test
    fun `throw exception when user doesn't exist`() {
        // given
        val userId = UserId("id")
        every { userRepository.containsUser(userId) } returns false

        // when
        val actual = { sut(userId) }

        // then
        actual shouldThrow IllegalStateException::class withMessage "User with id: $userId doesn't exist"
    }
}
