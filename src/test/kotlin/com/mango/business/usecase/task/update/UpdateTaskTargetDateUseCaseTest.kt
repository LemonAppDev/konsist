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
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

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
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val date: LocalDateTime = mockk()

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
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val date: LocalDateTime = mockk()

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
}
