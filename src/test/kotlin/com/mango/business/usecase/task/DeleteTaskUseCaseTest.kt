package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.DeleteTaskActivity
import com.mango.business.model.activity.task.DeleteTaskActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val deleteTaskActivityFactory: DeleteTaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
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
        val taskId = TaskId("id")
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task

        justRun { taskRepository.deleteTask(task) }

        val activity: DeleteTaskActivity = mockk()
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
        val taskId = TaskId("id")
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        val activity: DeleteTaskActivity = mockk()
        every { deleteTaskActivityFactory(taskId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
