package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskTargetDateActivity
import com.mango.business.model.activity.task.UpdateTaskTargetDateActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
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
import java.time.Month

class UpdateTaskTargetDateUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskTargetDateActivityFactory: UpdateTaskTargetDateActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = UpdateTaskTargetDateUseCase(
        taskRepository,
        activityRepository,
        updateTaskTargetDateActivityFactory,
        getTaskUseCase,
    )

    @Test
    fun `throws exception when target date is in the past`() {
        // given
        val taskId = TaskId("1")
        val newTargetDate = LocalDateTime.of(2023, Month.FEBRUARY, 20, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)

        // when
        val actual = { sut(taskId, newTargetDate, date) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Given date is in the past: $newTargetDate"
    }

    @Test
    fun `add task to repository if new target date is in the future`() {
        // given
        val taskId = TaskId("1")
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)

        val oldTask = BusinessTestModel.getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)

        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskTargetDateActivity = mockk()
        every { updateTaskTargetDateActivityFactory(taskId, date, oldTargetDate, newTargetDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository if new target date is in the future`() {
        // given
        val taskId = TaskId("1")
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)

        val oldTask = BusinessTestModel.getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskTargetDateActivity = mockk()
        every { updateTaskTargetDateActivityFactory(taskId, date, oldTargetDate, newTargetDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = TaskId("1")
        val oldTargetDate: LocalDateTime = LocalDateTime.of(2023, Month.MARCH, 20, 21, 0, 0, 0)
        val newTargetDate = LocalDateTime.of(2023, Month.MARCH, 20, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)

        val oldTask = BusinessTestModel.getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskTargetDateActivity = mockk()
        every { updateTaskTargetDateActivityFactory(taskId, date, oldTargetDate, newTargetDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify(exactly = 0) {
            taskRepository.updateTask(newTask)
            activityRepository.addActivity(activity)
        }
    }
}
