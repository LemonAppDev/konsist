package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskTargetDateUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId, newTargetDate: LocalDateTime?, date: LocalDateTime) {
        newTargetDate?.let { requireDateIsNowOrLaterUseCase(it) }
        val task = getTaskOrThrowUseCase(taskId)

        val oldTargetDate = task.targetDate

        if (newTargetDate != oldTargetDate) {
            val newTask = task.copy(targetDate = newTargetDate)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_TARGET_DATE,
                date,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
        }
    }
}
