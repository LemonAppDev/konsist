package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getUserId1
import com.mango.business.model.User
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UserToUserJpaEntityMapperTest {
    private val sut = UserToUserJpaEntityMapper()

    @Test
    fun `map user to userJpaEntity`() {
        // given
        val userId = getUserId1()
        val user = User(
            id = userId,
            name = "name",
        )

        // when
        val actual = sut(user)

        // then
        actual.apply {
            this.name shouldBeEqualTo "name"
            this.id shouldBeEqualTo userId.value
        }
    }
}
