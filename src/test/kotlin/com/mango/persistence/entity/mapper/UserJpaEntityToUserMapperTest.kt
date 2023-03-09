package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getUUID1
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.UserJpaEntity
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
        actual.apply {
            this.name shouldBeEqualTo "name"
            this.id shouldBeEqualTo UserId(uuid)
        }
    }
}
