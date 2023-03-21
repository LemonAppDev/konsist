package com.lemon.mango.domain.project.usecase

import com.lemon.mango.data.activity.ActivityRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId2
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetProjectActivitiesUseCaseTest {
    private val activityRepository: ActivityRepositoryImpl = mockk()
    private val checkProjectIdUseCase: CheckProjectIdUseCase = mockk()

    private val sut = GetProjectActivitiesUseCase(
        activityRepository,
        checkProjectIdUseCase,
    )

    @Test
    fun `get project activities for given user`() {
        // given
        val projectId1 = getProjectId1()
        val projectId2 = getProjectId2()
        justRun { checkProjectIdUseCase(projectId1) }

        val createProjectActivity1: ProjectActivity = mockk()
        every { createProjectActivity1.projectId } returns projectId1

        val createProjectActivity2: ProjectActivity = mockk()
        every { createProjectActivity2.projectId } returns projectId2

        val activities = listOf(createProjectActivity1, createProjectActivity2)
        every { activityRepository.projectActivities } returns activities

        // when
        val actual = sut(projectId1)

        // then
        actual shouldBeEqualTo listOf(createProjectActivity1)
    }
}
