package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.task.activity.UpdateTaskPriorityActivityFactory
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskPriorityUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskPriorityActivityFactory: UpdateTaskPriorityActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(taskId: TaskId, newPriority: Priority, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldPriority = task.priority

        if (newPriority != oldPriority) {
            val newTask = task.copy(priority = newPriority)

            taskRepository.saveTask(newTask)

            val activity = updateTaskPriorityActivityFactory(newTask.id, date, oldPriority, newPriority)
            activityRepository.addActivity(activity)
        }
    }
}
