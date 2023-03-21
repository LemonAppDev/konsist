package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.TaskActivityType.UPDATE_COMPLETE_DATE
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.model.Task
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime

class UpdateTaskCompleteDateUseCaseTest {
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskCompleteDateUseCase(
        addTaskActivityUseCase,
        getTaskOrThrowUseCase,
        taskRepository,
    )

    @ParameterizedTest
    @MethodSource("getData")
    fun `add task to repository`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val date = getCurrentDate()
        val oldTask: Task = getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_COMPLETE_DATE,
                date,
                newCompleteDate.toString(),
                currentCompleteDate.toString(),
            )
        }
        every { taskRepository.saveTask(any()) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(taskId, isComplete, date)

        // then
        if (shouldUpdateCompleteDate) {
            verify { taskRepository.saveTask(oldTask.copy(completeDate = newCompleteDate)) }
        }
    }

    @ParameterizedTest
    @MethodSource("getChildDate")
    fun `add child task to repository`(
        isComplete: Boolean,
        currentCompleteDateOfParentTask: LocalDateTime?,
        currentCompleteDateOfChildTask: LocalDateTime?,
        shouldUpdateCompleteDateOfChildTask: Boolean,
    ) {
        // given
        val date = getCurrentDate()
        val oldTask: Task = getTask(completeDate = currentCompleteDateOfParentTask)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_COMPLETE_DATE,
                date,
                newCompleteDate.toString(),
                currentCompleteDateOfParentTask.toString(),
            )
        }
        every { taskRepository.saveTask(any()) } returns mockk()

        val childTaskId = getTaskId1()
        val childTask = getTask(id = childTaskId, completeDate = currentCompleteDateOfChildTask, parentTaskId = oldTask.id)
        val newChildCompleteDate = if (isComplete) date else null
        justRun {
            addTaskActivityUseCase(
                childTaskId,
                UPDATE_COMPLETE_DATE,
                date,
                newChildCompleteDate.toString(),
                currentCompleteDateOfChildTask.toString(),
            )
        }

        every { taskRepository.tasks } returns listOf(childTask)

        // when
        sut(taskId, isComplete, date)

        // then
        if (shouldUpdateCompleteDateOfChildTask) {
            verify { taskRepository.saveTask(childTask.copy(completeDate = newChildCompleteDate)) }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun `add activity to repository`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val date = getCurrentDate()
        val oldTask: Task = getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_COMPLETE_DATE,
                date,
                newCompleteDate.toString(),
                currentCompleteDate.toString(),
            )
        }
        every { taskRepository.saveTask(any()) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(taskId, isComplete, date)

        // then
        if (shouldUpdateCompleteDate) {
            verify {
                addTaskActivityUseCase(
                    taskId,
                    UPDATE_COMPLETE_DATE,
                    date,
                    newCompleteDate.toString(),
                    currentCompleteDate.toString(),
                )
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getChildDate")
    fun `add child activities to repository`(
        isComplete: Boolean,
        currentCompleteDateOfParentTask: LocalDateTime?,
        currentCompleteDateOfChildTask: LocalDateTime?,
        shouldUpdateCompleteDateOfChildTask: Boolean,
    ) {
        // given
        val date = getCurrentDate()
        val oldTask: Task = getTask(completeDate = currentCompleteDateOfParentTask)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_COMPLETE_DATE,
                date,
                newCompleteDate.toString(),
                currentCompleteDateOfParentTask.toString(),
            )
        }
        every { taskRepository.saveTask(any()) } returns mockk()

        val childTaskId = getTaskId1()
        val childTask = getTask(id = childTaskId, completeDate = currentCompleteDateOfChildTask, parentTaskId = oldTask.id)
        val newChildCompleteDate = if (isComplete) date else null
        justRun {
            addTaskActivityUseCase(
                childTaskId,
                UPDATE_COMPLETE_DATE,
                date,
                newChildCompleteDate.toString(),
                currentCompleteDateOfChildTask.toString(),
            )
        }

        every { taskRepository.tasks } returns listOf(childTask)

        // when
        sut(taskId, isComplete, date)

        // then
        if (shouldUpdateCompleteDateOfChildTask) {
            verify {
                addTaskActivityUseCase(
                    childTaskId,
                    UPDATE_COMPLETE_DATE,
                    date,
                    newCompleteDate.toString(),
                    currentCompleteDateOfChildTask.toString(),
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

        @JvmStatic
        fun getChildDate() = listOf(
            arguments(true, mockk<LocalDateTime>(), mockk<LocalDateTime>(), false),
            arguments(true, null, mockk<LocalDateTime>(), false),
            arguments(true, null, null, true),
            arguments(false, mockk<LocalDateTime>(), mockk<LocalDateTime>(), true),
            arguments(false, null, mockk<LocalDateTime>(), false),
            arguments(false, null, null, false),
        )
    }
}
