package com.mango.business.usecase.user

import com.mango.business.model.User
import com.mango.persistence.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetCurrentUserUseCaseTest {
    private val userRepository: UserRepository = mockk()

    private val sut = GetCurrentUserUseCase(
        userRepository,
    )

    @Test
    fun `returns current user from userRepository`() {
        // given
        val user: User = mockk()
        every { userRepository.getCurrentUser() } returns user

        // when
        val actual = sut()

        // then
        actual shouldBeEqualTo user
    }
}
