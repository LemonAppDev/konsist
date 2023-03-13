package com.mango.domain.task.usecase.update

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskPriorityUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskActivityFactory: TaskActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId, newPriority: Priority, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldPriority = task.priority

        if (newPriority != oldPriority) {
            val newTask = task.copy(priority = newPriority)

            taskRepository.saveTask(newTask)

            val activity = taskActivityFactory(
                newTask.value,
                date,
                TaskActivityType.UPDATE_PRIORITY,
                newPriority.toString(),
                oldPriority.toString(),
            )
            activityRepository.addTaskActivity(activity)
        }
    }
}
