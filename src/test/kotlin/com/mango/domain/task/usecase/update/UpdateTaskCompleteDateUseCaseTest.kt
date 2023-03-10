package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.task.model.Task
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime

class UpdateTaskCompleteDateUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase = mockk()

    private val sut = UpdateTaskCompleteDateUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        updateTaskActivityUseCase,
    )

    @ParameterizedTest
    @MethodSource("getData")
    fun `add task to repository`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val date: LocalDateTime = mockk()
        val oldTask: Task = getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.value
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newCompleteDate.toString(),
                currentCompleteDate.toString(),
                TaskActivityType.UPDATE_COMPLETE_DATE,
            )
        } returns mockk()
        every { taskRepository.saveTask(any()) } returns mockk()

        // when
        sut(taskId, isComplete, date)

        // then

        if (shouldUpdateCompleteDate) {
            verify { taskRepository.saveTask(oldTask.copy(completeDate = newCompleteDate)) }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun `calls updateTaskActivityUseCase`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val date: LocalDateTime = mockk()
        val oldTask: Task = getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.value
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newCompleteDate.toString(),
                currentCompleteDate.toString(),
                TaskActivityType.UPDATE_COMPLETE_DATE,
            )
        } returns mockk()
        every { taskRepository.saveTask(any()) } returns mockk()

        // when
        sut(taskId, isComplete, date)

        // then

        if (shouldUpdateCompleteDate) {
            verify {
                updateTaskActivityUseCase(
                    taskId,
                    date,
                    newCompleteDate.toString(),
                    currentCompleteDate.toString(),
                    TaskActivityType.UPDATE_COMPLETE_DATE,
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun getData() = listOf(
            arguments(true, mockk<LocalDateTime>(), false),
            arguments(true, null, true),
            arguments(false, mockk<LocalDateTime>(), true),
            arguments(false, null, false),
        )
    }
}
