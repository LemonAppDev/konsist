package com.mango.presentation.controller

import com.mango.business.model.User
import com.mango.business.usecase.user.GetCurrentUserUseCase
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UserControllerTest {
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()

    private val sut = UserController(
        getCurrentUserUseCase,
    )

    @Test
    fun `should return current user`() {
        // given
        val user: User = mockk()
        every { getCurrentUserUseCase() } returns user

        // when
        val actual = sut.getCurrentUser()

        // then
        actual shouldBeEqualTo user
    }
}
