package com.mango.data.project

import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.user.model.User
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ProjectToProjectJpaEntityMapperTest {
    private val sut = ProjectToProjectJpaEntityMapper()

    @Test
    fun `map project to projectJpaEntity`() {
        // given
        val projectId = getProjectId1()
        val ownerId = getUserId1()
        val creationDate: LocalDateTime = mockk()
        val name = "name"
        val color = Color("color")
        val isFavourite = true
        val collaborators = listOf<User>()

        val project = Project(
            projectId,
            ownerId,
            creationDate,
            name,
            color,
            isFavourite,
            collaborators,
        )

        // when
        val actual = sut(project)

        // then
        actual.apply {
            this.id shouldBeEqualTo projectId.value
            this.ownerId shouldBeEqualTo ownerId.value
            this.creationDate shouldBeEqualTo creationDate
            this.name shouldBeEqualTo name
            this.color shouldBeEqualTo color
            this.isFavourite shouldBeEqualTo isFavourite
        }
    }
}
