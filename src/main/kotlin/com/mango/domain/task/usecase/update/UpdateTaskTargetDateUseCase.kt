package com.mango.domain.task.usecase.update

import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskTargetDateUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newTargetDate: LocalDateTime, date: LocalDateTime) {
        requireDateIsNowOrLaterUseCase(newTargetDate)
        val task = getTaskOrThrowUseCase(taskId)

        val oldTargetDate = task.targetDate

        if (newTargetDate != oldTargetDate) {
            val newTask = task.copy(targetDate = newTargetDate)

            taskRepository.saveTask(newTask)

            updateTaskActivityUseCase(
                newTask.value,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
                TaskActivityType.UPDATE_TARGET_DATE,
            )
        }
    }
}
