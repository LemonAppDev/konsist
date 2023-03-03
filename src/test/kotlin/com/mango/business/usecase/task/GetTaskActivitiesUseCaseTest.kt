package com.mango.business.usecase.task

import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetTaskActivitiesUseCaseTest {
    private val activityRepository: ActivityRepository = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = GetTaskActivitiesUseCase(
        activityRepository,
        getTaskUseCase,
    )

    @Test
    fun `get task activities for given user`() {
        // given
        val taskId1 = TaskId("id1")
        val taskId2 = TaskId("id2")
        every { getTaskUseCase(taskId1) } returns mockk()

        val createTaskActivity1: CreateTaskActivity = mockk()
        every { createTaskActivity1.taskId } returns taskId1

        val createTaskActivity2: CreateTaskActivity = mockk()
        every { createTaskActivity2.taskId } returns taskId2

        val activities = listOf(createTaskActivity1, createTaskActivity2)
        every { activityRepository.taskActivities } returns activities

        // when
        val actual = sut(taskId1)

        // then
        actual shouldBeEqualTo listOf(createTaskActivity1)
    }
}
