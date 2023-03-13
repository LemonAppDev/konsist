package com.mango.data.user

import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.user.model.User
import com.mango.domain.user.model.UserId
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UserJpaEntityToUserMapperTest {
    private val sut = UserJpaEntityToUserMapper()

    @Test
    fun `map userJpaEntity to user`() {
        // given
        val uuid = getUUID1()
        val userJpaEntity = UserJpaEntity(
            id = uuid,
            name = "name",
        )

        // when
        val actual = sut(userJpaEntity)

        // then
        actual shouldBeEqualTo User(
            UserId(uuid),
            "name",
        )
    }
}
