package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.Priority
import com.mango.business.model.activity.task.UpdateTaskPriorityActivity
import com.mango.business.model.activity.task.UpdateTaskPriorityActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskPriorityUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskPriorityActivityFactory: UpdateTaskPriorityActivityFactory = mockk()

    private val sut = UpdateTaskPriorityUseCase(
        taskRepository,
        activityRepository,
        updateTaskPriorityActivityFactory,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId, mockk(), mockk()) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, priority = oldPriority)
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        justRun { activityRepository.addActivity(any()) }
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns mockk()

        // when
        sut(taskId, newPriority, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, priority = oldPriority)
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        justRun { activityRepository.addActivity(any()) }

        val activity: UpdateTaskPriorityActivity = mockk()
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns activity

        // when
        sut(taskId, newPriority, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
