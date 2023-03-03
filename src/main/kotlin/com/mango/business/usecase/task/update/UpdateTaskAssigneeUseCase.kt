package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskAssigneeActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskAssigneeUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskAssigneeActivityFactory: UpdateTaskAssigneeActivityFactory,
    private val getTaskUseCase: GetTaskUseCase,
    private val userRepository: UserRepository,
) {
    operator fun invoke(taskId: TaskId, newAssigneeId: UserId, date: LocalDateTime) {
        val task = getTaskUseCase(taskId)

        val newAssigneeExists = userRepository.containsUser(newAssigneeId)
        require(newAssigneeExists) { "Assignee with id: $newAssigneeId doesn't exist" }

        val oldAssigneeId = task.assigneeId
        val newTask = task.copy(assigneeId = newAssigneeId)

        taskRepository.updateTask(newTask)

        val activity = updateTaskAssigneeActivityFactory(newTask.id, date, oldAssigneeId, newAssigneeId)
        activityRepository.addActivity(activity)
    }
}
