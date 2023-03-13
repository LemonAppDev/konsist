package com.mango.data.activity.project

import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.common.model.BusinessTestModel.getProjectActivityId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ProjectActivityToProjectActivityJpaEntityMapperTest {
    private val sut = ProjectActivityToProjectActivityJpaEntityMapper()

    @Test
    fun `map projectActivity to projectActivityJpaEntity`() {
        // given
        val id = getProjectActivityId1()
        val userId = getUserId1()
        val type = ProjectActivityType.CREATE
        val projectId = getProjectId1()
        val date: LocalDateTime = mockk()

        val projectActivity = ProjectActivity(
            id,
            userId,
            type,
            projectId,
            date,
        )

        // when
        val actual = sut(projectActivity)

        // then
        with(actual) {
            this.id shouldBeEqualTo id.value
            this.userId shouldBeEqualTo userId.value
            this.type shouldBeEqualTo type.value
            this.projectId shouldBeEqualTo projectId.value
            this.date shouldBeEqualTo date
        }
    }
}
