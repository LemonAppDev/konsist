package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskPriorityUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateTaskPriorityUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        taskActivityFactory,
        activityRepository,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: com.mango.domain.task.model.Priority = mockk()
        val newPriority: com.mango.domain.task.model.Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                TaskActivityType.UPDATE_PRIORITY,
                newPriority.toString(),
                oldPriority.toString(),
            )
        } returns activity
        justRun { activityRepository.addTaskActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: com.mango.domain.task.model.Priority = mockk()
        val newPriority: com.mango.domain.task.model.Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                TaskActivityType.UPDATE_PRIORITY,
                newPriority.toString(),
                oldPriority.toString(),
            )
        } returns activity
        justRun { activityRepository.addTaskActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify { activityRepository.addTaskActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldPriority = com.mango.domain.task.model.Priority.PRIORITY_1
        val newPriority = com.mango.domain.task.model.Priority.PRIORITY_1
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                TaskActivityType.UPDATE_PRIORITY,
                newPriority.toString(),
                oldPriority.toString(),
            )
        } returns activity
        justRun { activityRepository.addTaskActivity(activity) }

        // when
        sut(taskId, newPriority, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            activityRepository.addTaskActivity(activity)
        }
    }
}
