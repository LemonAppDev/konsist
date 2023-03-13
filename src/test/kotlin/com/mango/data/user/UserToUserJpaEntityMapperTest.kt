package com.mango.data.user

import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.model.User
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
        with(actual) {
            this.name shouldBeEqualTo "name"
            this.id shouldBeEqualTo userId.value
        }
    }
}
