package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getUUID1
import com.mango.business.common.model.BusinessTestModel.getUUID2
import com.mango.business.model.value.Color
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.ProjectJpaEntity
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
        actual.apply {
            this.id shouldBeEqualTo ProjectId(projectUUID)
            this.owner shouldBeEqualTo UserId(ownerId)
            this.creationDate shouldBeEqualTo creationDate
            this.name shouldBeEqualTo name
            this.color shouldBeEqualTo color
            this.isFavourite shouldBeEqualTo true
            this.collaborators shouldBeEqualTo null
        }
    }
}
