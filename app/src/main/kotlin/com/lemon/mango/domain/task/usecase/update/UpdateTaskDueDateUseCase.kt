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
class UpdateTaskDueDateUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId, newDueDate: LocalDateTime?, date: LocalDateTime) {
        newDueDate?.let { requireDateIsNowOrLaterUseCase(it) }

        val task = getTaskOrThrowUseCase(taskId)

        val oldDueDate = task.dueDate

        if (newDueDate != oldDueDate) {
            val newTask = task.copy(dueDate = newDueDate)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_DUE_DATE,
                date,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
        }
    }
}
