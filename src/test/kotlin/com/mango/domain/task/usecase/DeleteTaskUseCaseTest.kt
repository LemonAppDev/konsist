package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType.DELETE
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
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
    fun `deletes task from repository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { taskRepository.tasks } returns mockk(relaxed = true)
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, date, DELETE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { taskRepository.deleteTask(task) }
    }

    @Test
    fun `deletes child tasks from repository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        val childTask1: Task = mockk()
        val id1 = getTaskId2()
        every { childTask1.id } returns id1
        every { childTask1.parentTaskId } returns taskId
        val childTask2: Task = mockk()
        val id2 = getTaskId2()
        every { childTask2.id } returns id2
        every { childTask2.parentTaskId } returns taskId
        every { taskRepository.tasks } returns listOf(childTask1, childTask2)
        justRun { taskRepository.deleteTask(childTask1) }
        justRun { taskRepository.deleteTask(childTask2) }
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, date, DELETE) } returns activity
        every { taskActivityFactory(id1, date, DELETE) } returns activity
        every { taskActivityFactory(id2, date, DELETE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId)

        // then
        verifyOrder {
            taskRepository.deleteTask(childTask1)
            taskRepository.deleteTask(childTask2)
        }
    }

    @Test
    fun `adds activity to repository three times`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        val childTask1: Task = mockk()
        val id1 = getTaskId2()
        every { childTask1.id } returns id1
        every { childTask1.parentTaskId } returns taskId
        val childTask2: Task = mockk()
        val id2 = getTaskId2()
        every { childTask2.id } returns id2
        every { childTask2.parentTaskId } returns taskId
        every { taskRepository.tasks } returns listOf(childTask1, childTask2)
        justRun { taskRepository.deleteTask(childTask1) }
        justRun { taskRepository.deleteTask(childTask2) }
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, date, DELETE) } returns activity
        every { taskActivityFactory(id1, date, DELETE) } returns activity
        every { taskActivityFactory(id2, date, DELETE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId)

        // then
        verify(exactly = 3) { activityRepository.addTaskActivity(activity) }
    }
}
