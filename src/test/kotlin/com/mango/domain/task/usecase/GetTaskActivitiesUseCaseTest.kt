package com.mango.domain.task.usecase

import com.mango.data.activity.ActivityRepositoryImpl
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetTaskActivitiesUseCaseTest {
    private val activityRepository: ActivityRepositoryImpl = mockk()
    private val checkTaskIdUseCase: CheckTaskIdUseCase = mockk()

    private val sut = GetTaskActivitiesUseCase(
        activityRepository,
        checkTaskIdUseCase,
    )

    @Test
    fun `get task activities for given user`() {
        // given
        val taskId1 = getTaskId1()
        val taskId2 = getTaskId2()
        justRun { checkTaskIdUseCase(taskId1) }

        val createTaskActivity1: com.mango.domain.task.activity.CreateTaskActivity = mockk()
        every { createTaskActivity1.taskId } returns taskId1

        val createTaskActivity2: com.mango.domain.task.activity.CreateTaskActivity = mockk()
        every { createTaskActivity2.taskId } returns taskId2

        val activities = listOf(createTaskActivity1, createTaskActivity2)
        every { activityRepository.taskActivities } returns activities

        // when
        val actual = sut(taskId1)

        // then
        actual shouldBeEqualTo listOf(createTaskActivity1)
    }
}
