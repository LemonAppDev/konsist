package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskNameActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskNameUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskNameActivityFactory: UpdateTaskNameActivityFactory,
) {
    operator fun invoke(taskId: TaskId, newName: String, date: LocalDateTime) {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        val oldName = task.name
        val newTask = task.copy(name = newName)

        taskRepository.updateTask(newTask)

        val activity = updateTaskNameActivityFactory(newTask.id, date, oldName, newName)
        activityRepository.addActivity(activity)
    }
}
