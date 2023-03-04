package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskDueDateActivity
import com.mango.business.model.activity.task.UpdateTaskDueDateActivityFactory
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

class UpdateTaskDueDateUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskDueDateActivityFactory: UpdateTaskDueDateActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = UpdateTaskDueDateUseCase(
        taskRepository,
        activityRepository,
        updateTaskDueDateActivityFactory,
        getTaskUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldDueDate: LocalDateTime = mockk()
        val newDueDate: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date: LocalDateTime = mockk()
        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskDueDateActivity = mockk()
        every { updateTaskDueDateActivityFactory(taskId, date, oldDueDate, newDueDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldDueDate: LocalDateTime = mockk()
        val newDueDate: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date: LocalDateTime = mockk()
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskDueDateActivity = mockk()
        every { updateTaskDueDateActivityFactory(taskId, date, oldDueDate, newDueDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
