package com.lemon.mango.domain.user.usecase

import com.lemon.mango.data.user.UserRepositoryImpl
import com.lemon.mango.domain.user.model.User
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetCurrentUserUseCaseTest {
    private val userRepository: UserRepositoryImpl = mockk()

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
