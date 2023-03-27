package com.lemon.mango.domain.user

import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.user.model.User
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UserFactoryTest {
    private val uuidFactory: UuidFactory = mockk()

    private val sut = UserFactory(uuidFactory)

    @Test
    fun `returns user`() {
        // given
        val name = "name"
        val userId = getUserId1()
        every { uuidFactory.createUserId() } returns userId

        // when
        val actual = sut(name)

        // then
        actual shouldBeEqualTo User(userId, name)
    }
}
