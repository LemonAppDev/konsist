package com.mango.domain.task.usecase

import com.mango.data.activity.ActivityRepositoryImpl
import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteTaskUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val deleteTaskActivityFactory: com.mango.domain.task.activity.DeleteTaskActivityFactory = mockk()
    private val activityRepository: ActivityRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()

    private val sut = DeleteTaskUseCase(
        taskRepository,
        deleteTaskActivityFactory,
        activityRepository,
        localDateTimeFactory,
    )

    @Test
    fun `delete task in TaskRepository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task

        justRun { taskRepository.deleteTask(task) }

        val activity: com.mango.domain.task.activity.DeleteTaskActivity = mockk()
        every { deleteTaskActivityFactory(taskId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId)

        // then
        verify { taskRepository.deleteTask(task) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        val activity: com.mango.domain.task.activity.DeleteTaskActivity = mockk()
        every { deleteTaskActivityFactory(taskId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
