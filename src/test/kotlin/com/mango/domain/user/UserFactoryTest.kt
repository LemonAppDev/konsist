package com.mango.domain.user

import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.model.User
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UserFactoryTest {
    private val uuidFactory: UUIDFactory = mockk()

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
