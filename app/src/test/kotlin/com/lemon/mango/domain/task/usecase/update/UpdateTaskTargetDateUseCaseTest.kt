package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.data.task.TaskRepositoryImpl
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_TARGET_DATE
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.lemon.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.lemon.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.lemon.mango.domain.common.model.BusinessTestModel.getTask
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class UpdateTaskTargetDateUseCaseTest {
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskTargetDateUseCase(
        addTaskActivityUseCase,
        getTaskOrThrowUseCase,
        requireDateIsNowOrLaterUseCase,
        taskRepository,
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
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_TARGET_DATE,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        }

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
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_TARGET_DATE,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        }

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify {
            addTaskActivityUseCase(
                taskId,
                UPDATE_TARGET_DATE,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        }
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
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_TARGET_DATE,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        }

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(
                taskId,
                UPDATE_TARGET_DATE,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        }
    }
}
