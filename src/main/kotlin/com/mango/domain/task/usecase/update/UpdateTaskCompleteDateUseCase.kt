package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskCompleteDateUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId, isComplete: Boolean, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        updateCompleteDate(task, isComplete, date)
    }

    private fun updateCompleteDate(task: Task, isComplete: Boolean, date: LocalDateTime?) {
        val completeDate = when {
            isComplete && task.completeDate == null -> date
            !isComplete && task.completeDate != null -> null
            else -> return
        }

        val oldDate = task.completeDate
        val newTask = task.copy(completeDate = completeDate)

        taskRepository.saveTask(newTask)

        addTaskActivityUseCase(
            newTask.id,
            TaskActivityType.UPDATE_COMPLETE_DATE,
            date,
            newTask.completeDate.toString(),
            oldDate.toString(),
        )

        taskRepository.tasks
            .filter { childTask -> childTask.parentTaskId == task.id }
            .forEach { childTask -> updateCompleteDate(childTask, isComplete, date) }
    }
}
