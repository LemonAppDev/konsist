package com.mango.business.usecase.task.update

import com.mango.business.model.Priority
import com.mango.business.model.activity.task.UpdateTaskPriorityActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
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
