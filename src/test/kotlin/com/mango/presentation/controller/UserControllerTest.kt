package com.mango.presentation.controller

import com.mango.business.usecase.user.CreateUserUseCase
import com.mango.business.usecase.user.GetCurrentUserUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class UserControllerTest {
    private val createUserUseCase: CreateUserUseCase = mockk()
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()

    private val sut = UserController(
        createUserUseCase,
        getCurrentUserUseCase,
    )

    @Test
    fun `createUser() calls createUserUseCase()`() {
        // given
        val userName = "userName"
        every { createUserUseCase.invoke(userName) } returns mockk()

        // when
        sut.createUser(userName)

        // then
        verify { createUserUseCase.invoke(userName) }
    }

    @Test
    fun `getCurrentUser() calls getCurrentUserUseCase()`() {
        // given
        every { getCurrentUserUseCase.invoke() } returns mockk()

        // when
        sut.getCurrentUser()

        // then
        verify { getCurrentUserUseCase.invoke() }
    }
}
