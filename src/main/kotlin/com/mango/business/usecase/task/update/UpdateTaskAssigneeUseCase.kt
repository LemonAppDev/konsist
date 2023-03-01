package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskAssigneeActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskAssigneeUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskAssigneeActivityFactory: UpdateTaskAssigneeActivityFactory,
) {
    operator fun invoke(taskId: TaskId, newAssigneeId: UserId, date: LocalDateTime) {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        val oldAssigneeId = task.assigneeId
        val newTask = task.copy(assigneeId = newAssigneeId)

        taskRepository.updateTask(newTask)

        val activity = updateTaskAssigneeActivityFactory(newTask.id, date, oldAssigneeId, newAssigneeId)
        activityRepository.addActivity(activity)
    }
}
