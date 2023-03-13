package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
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
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = DeleteTaskUseCase(
        taskRepository,
        localDateTimeFactory,
        taskActivityFactory,
        activityRepository,
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
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, date, TaskActivityType.DELETE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { taskRepository.deleteTask(task) }
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, date, TaskActivityType.DELETE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { activityRepository.addTaskActivity(activity) }
    }
}
