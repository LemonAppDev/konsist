package com.mango.persistence.repository

import com.mango.business.model.User
import com.mango.business.model.value.UserId
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class UserRepositoryTest {
    private val sut = UserRepository()

    @Test
    fun `addUser() adds user to users`() {
        // given
        val user: User = mockk()

        // when
        sut.addUser(user)

        // then
        sut.users shouldContain user
    }

    @Test
    fun `getUser() returns user when it exist`() {
        // given
        val userId = UserId("id")
        val user: User = mockk()
        every { user.id } returns userId
        sut.addUser(user)

        // when
        val actual = sut.getUser(userId)

        // then
        actual shouldBeEqualTo user
    }

    @Test
    fun `getUser() returns null when user doesn't exist`() {
        // given
        val userId = UserId("id")
        val user: User = mockk()
        every { user.id } returns userId

        // when
        val actual = sut.getUser(userId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `containsUser() returns true when user exist`() {
        // given
        val userId = UserId("id")
        val user: User = mockk()
        every { user.id } returns userId
        sut.addUser(user)

        // when
        val actual = sut.containsUser(userId)

        // then
        actual shouldBeEqualTo true
    }

    @Test
    fun `containsUser() returns false when user doesn't exist`() {
        // given
        val userId = UserId("id")

        // when
        val actual = sut.containsUser(userId)

        // then
        actual shouldBeEqualTo false
    }
}
