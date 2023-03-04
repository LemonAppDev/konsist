package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.Priority
import com.mango.business.model.activity.task.UpdateTaskPriorityActivity
import com.mango.business.model.activity.task.UpdateTaskPriorityActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskPriorityUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskPriorityActivityFactory: UpdateTaskPriorityActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = UpdateTaskPriorityUseCase(
        taskRepository,
        activityRepository,
        updateTaskPriorityActivityFactory,
        getTaskUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, priority = oldPriority)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskPriorityActivity = mockk()
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns activity
        justRun { activityRepository.addActivity(activity) }

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
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskPriorityActivity = mockk()
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
