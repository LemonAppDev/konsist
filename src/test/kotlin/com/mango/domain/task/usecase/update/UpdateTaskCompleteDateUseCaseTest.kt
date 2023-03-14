package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType.UPDATE_COMPLETE_DATE
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
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateTaskCompleteDateUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        taskActivityFactory,
        activityRepository,
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
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_COMPLETE_DATE,
                newCompleteDate.toString(),
                currentCompleteDate.toString(),
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()
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
    fun `add activity to repository`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val date: LocalDateTime = mockk()
        val oldTask: Task = getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_COMPLETE_DATE,
                newCompleteDate.toString(),
                currentCompleteDate.toString(),
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()
        every { taskRepository.saveTask(any()) } returns mockk()

        // when
        sut(taskId, isComplete, date)

        // then

        if (shouldUpdateCompleteDate) {
            verify { activityRepository.addTaskActivity(activity) }
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
