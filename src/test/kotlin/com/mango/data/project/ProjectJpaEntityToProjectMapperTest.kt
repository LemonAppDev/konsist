package com.mango.data.project

import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.common.model.BusinessTestModel.getUUID2
import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import com.mango.domain.user.model.UserId
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ProjectJpaEntityToProjectMapperTest {
    private val sut = ProjectJpaEntityToProjectMapper()

    @Test
    fun `map projectJpaEntity to project`() {
        // given
        val projectUUID = getUUID1()
        val ownerId = getUUID2()
        val creationDate: LocalDateTime = mockk()
        val name = "name"
        val color = Color("color")
        val isFavourite = true

        val projectJpaEntity = ProjectJpaEntity(
            projectUUID,
            ownerId,
            creationDate,
            name,
            color,
            isFavourite,
        )

        // when
        val actual = sut(projectJpaEntity)

        // then
        actual shouldBeEqualTo Project(
            ProjectId(projectUUID),
            UserId(ownerId),
            creationDate,
            name,
            color,
            true,
        )
    }
}
