package com.mango.data.activity.project

import com.mango.domain.activity.model.ProjectActivityId
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.common.model.BusinessTestModel.getUUID2
import com.mango.domain.common.model.BusinessTestModel.getUUID3
import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.model.UserId
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ProjectActivityJpaEntityToProjectActivityMapperTest {
    private val sut = ProjectActivityJpaEntityToProjectActivityMapper()

    @Test
    fun `map projectActivityJpaEntity to projectActivity`() {
        // given
        val id = getUUID1()
        val userId = getUUID2()
        val type = "create"
        val projectId = getUUID3()
        val date: LocalDateTime = mockk()

        val projectActivityJpaEntity = ProjectActivityJpaEntity(
            id,
            userId,
            type,
            projectId,
            date,
        )

        // when
        val actual = sut(projectActivityJpaEntity)

        // then
        actual.apply {
            this.id shouldBeEqualTo ProjectActivityId(id)
            this.userId shouldBeEqualTo UserId(userId)
            this.type shouldBeEqualTo ProjectActivityType.getByValue(type)
            this.projectId shouldBeEqualTo ProjectId(projectId)
            this.date shouldBeEqualTo date
        }
    }
}
