package com.lemon.mango.data.activity.project

import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.activity.model.ProjectActivityId
import com.lemon.mango.domain.activity.model.ProjectActivityType.Companion.getByValue
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID2
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID3
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.user.model.UserId
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
        val newValue = "newValue"
        val oldValue = "oldValue"

        val projectActivityJpaEntity = ProjectActivityJpaEntity(
            id,
            userId,
            type,
            projectId,
            date,
            newValue,
            oldValue,
        )

        // when
        val actual = sut(projectActivityJpaEntity)

        // then
        actual shouldBeEqualTo ProjectActivity(
            ProjectActivityId(id),
            UserId(userId),
            getByValue(type),
            ProjectId(projectId),
            date,
            newValue,
            oldValue,
        )
    }
}
