package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.Task
import com.mango.business.model.activity.task.UpdateTaskCompleteDateActivity
import com.mango.business.model.activity.task.UpdateTaskCompleteDateActivityFactory
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime

class UpdateTaskCompleteDateUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val updateTaskCompleteDateActivityFactory: UpdateTaskCompleteDateActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = UpdateTaskCompleteDateUseCase(
        taskRepository,
        updateTaskCompleteDateActivityFactory,
        activityRepository,
        getTaskOrThrowUseCase,
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
        val oldTask: Task = BusinessTestModel.getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        every { updateTaskCompleteDateActivityFactory(taskId, date, currentCompleteDate, newCompleteDate) } returns mockk()

        every { taskRepository.saveTask(any()) } returns mockk()
        val activity: UpdateTaskCompleteDateActivity = mockk()
        every { updateTaskCompleteDateActivityFactory(taskId, date, currentCompleteDate, newCompleteDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, isComplete, date)

        // then

        if (shouldUpdateCompleteDate) {
            verify { taskRepository.saveTask(oldTask.copy(completeDate = newCompleteDate)) }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun `add activity to activity repository`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val date: LocalDateTime = mockk()
        val oldTask: Task = BusinessTestModel.getTask(completeDate = currentCompleteDate)
        val taskId = oldTask.id
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newCompleteDate = if (isComplete) date else null
        every { updateTaskCompleteDateActivityFactory(taskId, date, currentCompleteDate, newCompleteDate) } returns mockk()

        every { taskRepository.saveTask(any()) } returns mockk()
        val activity: UpdateTaskCompleteDateActivity = mockk()
        every { updateTaskCompleteDateActivityFactory(taskId, date, currentCompleteDate, newCompleteDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, isComplete, date)

        // then

        if (shouldUpdateCompleteDate) {
            verify { activityRepository.addActivity(activity) }
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
