package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskTargetDateActivity
import com.mango.business.model.activity.task.UpdateTaskTargetDateActivityFactory
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

class UpdateTaskTargetDateUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskTargetDateActivityFactory: UpdateTaskTargetDateActivityFactory = mockk()

    private val sut = UpdateTaskTargetDateUseCase(
        taskRepository,
        activityRepository,
        updateTaskTargetDateActivityFactory,
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
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, targetDate = oldTargetDate)
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        justRun { activityRepository.addActivity(any()) }
        every { updateTaskTargetDateActivityFactory(taskId, date, oldTargetDate, newTargetDate) } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, targetDate = oldTargetDate)
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        justRun { activityRepository.addActivity(any()) }

        val activity: UpdateTaskTargetDateActivity = mockk()
        every { updateTaskTargetDateActivityFactory(taskId, date, oldTargetDate, newTargetDate) } returns activity

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
