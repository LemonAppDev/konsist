package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.Task
import com.mango.business.model.activity.task.UpdateTaskCompleteDateActivity
import com.mango.business.model.activity.task.UpdateTaskCompleteDateActivityFactory
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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDateTime

class UpdateTaskCompleteDateUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val updateTaskCompleteDateActivityFactory: UpdateTaskCompleteDateActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateTaskCompleteDateUseCase(
        taskRepository,
        updateTaskCompleteDateActivityFactory,
        activityRepository,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("taskId")
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId, true, mockk()) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun `add task to repository`(
        isComplete: Boolean,
        currentCompleteDate: LocalDateTime?,
        shouldUpdateCompleteDate: Boolean,
    ) {
        // given
        val taskId = TaskId("taskId")
        val date: LocalDateTime = mockk()
        val oldTask: Task = BusinessTestModel.getTask(completeDate = currentCompleteDate)
        every { updateTaskCompleteDateActivityFactory(taskId, date) } returns mockk()

        every { taskRepository.getTask(taskId) } returns oldTask
        justRun { taskRepository.updateTask(any()) }
        val activity: UpdateTaskCompleteDateActivity = mockk()
        every { updateTaskCompleteDateActivityFactory(taskId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, isComplete, date)

        // then

        if (shouldUpdateCompleteDate) {
            val newCompleteDate = if (isComplete) date else null
            verify { taskRepository.updateTask(oldTask.copy(completeDate = newCompleteDate)) }
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
        val taskId = TaskId("taskId")
        val date: LocalDateTime = mockk()
        val oldTask: Task = BusinessTestModel.getTask(completeDate = currentCompleteDate)
        val activity: UpdateTaskCompleteDateActivity = mockk()
        every { updateTaskCompleteDateActivityFactory(taskId, date) } returns activity

        every { taskRepository.getTask(taskId) } returns oldTask
        justRun { taskRepository.updateTask(any()) }
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
