package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class UpdateTaskTargetDateUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase = mockk()

    private val sut = UpdateTaskTargetDateUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        requireDateIsNowOrLaterUseCase,
        updateTaskActivityUseCase,
    )

    @Test
    fun `add task to repository if new target date is in the future`() {
        // given
        val taskId = getTaskId1()
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)
        justRun { requireDateIsNowOrLaterUseCase(newTargetDate) }

        val oldTask = getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
                TaskActivityType.UPDATE_TARGET_DATE,
            )
        } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `calls updateTaskActivityUseCase if new target date is in the future`() {
        // given
        val taskId = getTaskId1()
        val oldTargetDate: LocalDateTime = mockk()
        val newTargetDate = LocalDateTime.of(2023, Month.MARCH, 30, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)
        justRun { requireDateIsNowOrLaterUseCase(newTargetDate) }

        val oldTask = getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
                TaskActivityType.UPDATE_TARGET_DATE,
            )
        } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify {
            updateTaskActivityUseCase(
                taskId,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
                TaskActivityType.UPDATE_TARGET_DATE,
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldTargetDate: LocalDateTime = LocalDateTime.of(2023, Month.MARCH, 20, 21, 0, 0, 0)
        val newTargetDate = LocalDateTime.of(2023, Month.MARCH, 20, 21, 0, 0, 0)
        val date = LocalDateTime.of(2023, Month.MARCH, 1, 21, 0, 0, 0)
        justRun { requireDateIsNowOrLaterUseCase(newTargetDate) }

        val oldTask = getTask(id = taskId, targetDate = oldTargetDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(targetDate = newTargetDate)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
                TaskActivityType.UPDATE_TARGET_DATE,
            )
        } returns mockk()

        // when
        sut(taskId, newTargetDate, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            updateTaskActivityUseCase(
                taskId,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
                TaskActivityType.UPDATE_TARGET_DATE,
            )
        }
    }
}
