package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType.UPDATE_TARGET_DATE
import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class UpdateTaskTargetDateUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateTaskTargetDateUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        requireDateIsNowOrLaterUseCase,
        taskActivityFactory,
        activityRepository,
    )

    @Test
    fun `add task to repository if new target date is in the future`() {
        // given
        val taskId = getTaskId1()
        val oldTargetDate = getFutureDate1()
        val newTargetDate = getFutureDate2()
        val date = getCurrentDate()
        justRun { requireDateIsNowOrLaterUseCase(newTargetDate) }

        val oldTask = getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_TARGET_DATE,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository if new target date is in the future`() {
        // given
        val taskId = getTaskId1()
        val oldTargetDate = getFutureDate1()
        val newTargetDate = getFutureDate2()
        val date = getCurrentDate()
        justRun { requireDateIsNowOrLaterUseCase(newTargetDate) }

        val oldTask = getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_TARGET_DATE,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { activityRepository.addTaskActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldTargetDate = getFutureDate1()
        val newTargetDate = getFutureDate1()
        val date = getCurrentDate()
        justRun { requireDateIsNowOrLaterUseCase(newTargetDate) }

        val oldTask = getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_TARGET_DATE,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            activityRepository.addTaskActivity(activity)
        }
    }
}
