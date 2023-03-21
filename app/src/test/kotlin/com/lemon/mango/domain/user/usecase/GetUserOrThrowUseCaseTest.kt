package com.lemon.mango.domain.user.usecase

import com.lemon.mango.data.user.UserRepositoryImpl
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.user.model.User
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetUserOrThrowUseCaseTest {
    private val userRepository: UserRepositoryImpl = mockk()

    private val sut = GetUserOrThrowUseCase(
        userRepository,
    )

    @Test
    fun `throws exception when user doesn't exist`() {
        // given
        val userId = getUserId1()
        every { userRepository.getUser(userId) } returns null

        // when
        val actual = { sut(userId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "User with id: $userId doesn't exist"
    }

    @Test
    fun `returns user from userRepository`() {
        // given
        val userId = getUserId1()
        val user: User = mockk()
        every { userRepository.getUser(userId) } returns user

        // when
        val actual = sut(userId)

        // then
        actual shouldBeEqualTo user
    }
}
