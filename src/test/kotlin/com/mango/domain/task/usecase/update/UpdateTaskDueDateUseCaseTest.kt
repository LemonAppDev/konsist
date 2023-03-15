package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.TaskActivityType.UPDATE_DUE_DATE
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
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

class UpdateTaskDueDateUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()

    private val sut = UpdateTaskDueDateUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        requireDateIsNowOrLaterUseCase,
        addTaskActivityUseCase,
    )

    @Test
    fun `add task to repository if new due date is in the future`() {
        // given
        val taskId = getTaskId1()
        val oldDueDate = getFutureDate1()
        val newDueDate = getFutureDate2()

        justRun { requireDateIsNowOrLaterUseCase(newDueDate) }
        val oldTask = getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date = getCurrentDate()
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_DUE_DATE,
                date,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
        }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository if new due date is in the future`() {
        // given
        val taskId = getTaskId1()
        val oldDueDate = getFutureDate1()
        val newDueDate = getFutureDate2()

        justRun { requireDateIsNowOrLaterUseCase(newDueDate) }
        val oldTask = getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date = getCurrentDate()
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_DUE_DATE,
                date,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
        }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify {
            addTaskActivityUseCase(
                taskId,
                UPDATE_DUE_DATE,
                date,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldDueDate = getFutureDate1()
        val newDueDate = getFutureDate1()

        justRun { requireDateIsNowOrLaterUseCase(newDueDate) }
        val oldTask = getTask(id = taskId, dueDate = oldDueDate)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(dueDate = newDueDate)
        val date = getCurrentDate()
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_DUE_DATE,
                date,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
        }

        // when
        sut(taskId, newDueDate, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(
                taskId,
                UPDATE_DUE_DATE,
                date,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
        }
    }
}
