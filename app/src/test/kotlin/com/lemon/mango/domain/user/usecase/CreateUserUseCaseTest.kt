package com.lemon.mango.domain.user.usecase

import com.lemon.mango.data.user.UserRepositoryImpl
import com.lemon.mango.domain.user.UserFactory
import com.lemon.mango.domain.user.model.User
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CreateUserUseCaseTest {
    private val userFactory: UserFactory = mockk()
    private val userRepository: UserRepositoryImpl = mockk()

    private val sut = CreateUserUseCase(
        userFactory,
        userRepository,
    )

    @Test
    fun `throws exception when user name is blank`() {
        // given
        val userName = ""

        // when
        val actual = { sut(userName) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "userName is blank"
    }

    @Test
    fun `returns user`() {
        // given
        val userName = "userName"
        val user: User = mockk()
        val repositoryUser: User = mockk()
        every { userFactory(userName) } returns user
        every { userRepository.saveUser(user) } returns repositoryUser

        // when
        val actual = sut(userName)

        // then
        actual shouldBeEqualTo repositoryUser
    }
}
