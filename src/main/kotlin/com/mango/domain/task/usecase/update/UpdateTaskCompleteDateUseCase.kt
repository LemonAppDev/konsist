package com.mango.domain.task.usecase.update

import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskCompleteDateUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, isComplete: Boolean, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        if (isComplete && task.completeDate == null) {
            updateCompleteDate(task, date, date)
        } else if (!isComplete && task.completeDate != null) {
            updateCompleteDate(task, date, null)
        }
    }

    private fun updateCompleteDate(task: Task, date: LocalDateTime, completeDate: LocalDateTime?) {
        val oldDate = task.completeDate
        val newTask = task.copy(completeDate = completeDate)

        taskRepository.saveTask(newTask)

        updateTaskActivityUseCase(
            newTask.value,
            date,
            newTask.completeDate.toString(),
            oldDate.toString(),
            TaskActivityType.UPDATE_COMPLETE_DATE,
        )
    }
}
