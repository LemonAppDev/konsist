package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskDueDateActivity
import com.mango.business.model.activity.task.UpdateTaskDueDateActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.common.RequireDateIsNowOrLaterUseCase
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class UpdateTaskDueDateUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskDueDateActivityFactory: UpdateTaskDueDateActivityFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()

    private val sut = UpdateTaskDueDateUseCase(
        taskRepository,
        activityRepository,
        updateTaskDueDateActivityFactory,
        getTaskOrThrowUseCase,
        requireDateIsNowOrLaterUseCase,
    )

    @Test
    fun `add task to repository if new due date is in the future`() {
        // given
        val taskId = TaskId("1")
        val oldDueDate: LocalDateTime = mockk()
        val newDueDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)

        val oldTask = BusinessTestModel.getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)
        justRun { requireDateIsNowOrLaterUseCase(newDueDate, date) }
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
    fun `add activity to activity repository if new due date is in the future`() {
        // given
        val taskId = TaskId("1")
        val oldDueDate: LocalDateTime = mockk()
        val newDueDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)

        val oldTask = BusinessTestModel.getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)
        justRun { requireDateIsNowOrLaterUseCase(newDueDate, date) }
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskDueDateActivity = mockk()
        every { updateTaskDueDateActivityFactory(taskId, date, oldDueDate, newDueDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = TaskId("1")
        val oldDueDate: LocalDateTime = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)
        val newDueDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)

        val oldTask = BusinessTestModel.getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)
        justRun { requireDateIsNowOrLaterUseCase(newDueDate, date) }
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskDueDateActivity = mockk()
        every { updateTaskDueDateActivityFactory(taskId, date, oldDueDate, newDueDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify(exactly = 0) {
            taskRepository.updateTask(newTask)
            activityRepository.addActivity(activity)
        }
    }
}
