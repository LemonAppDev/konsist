package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.model.Priority
import com.mango.business.model.activity.task.UpdateTaskPriorityActivity
import com.mango.business.model.activity.task.UpdateTaskPriorityActivityFactory
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
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
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = UpdateTaskPriorityUseCase(
        taskRepository,
        activityRepository,
        updateTaskPriorityActivityFactory,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: UpdateTaskPriorityActivity = mockk()
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val activity: UpdateTaskPriorityActivity = mockk()
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldPriority = Priority.PRIORITY_1
        val newPriority = Priority.PRIORITY_1
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val activity: UpdateTaskPriorityActivity = mockk()
        every { updateTaskPriorityActivityFactory(taskId, date, oldPriority, newPriority) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.saveTask(newTask)
        }
    }
}
