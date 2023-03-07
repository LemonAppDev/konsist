package com.mango.business.usecase.user

import com.mango.business.model.User
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetUserOrThrowUseCaseTest {
    private val userRepository: UserRepository = mockk()

    private val sut = GetUserOrThrowUseCase(
        userRepository,
    )

    @Test
    fun `throws exception when user doesn't exist`() {
        // given
        val userId = UserId("id")
        every { userRepository.getUser(userId) } returns null

        // when
        val actual = { sut(userId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "User with id: $userId doesn't exist"
    }

    @Test
    fun `returns user from userRepository`() {
        // given
        val userId = UserId("userId")
        val user: User = mockk()
        every { userRepository.getUser(userId) } returns user

        // when
        val actual = sut(userId)

        // then
        actual shouldBeEqualTo user
    }
}
