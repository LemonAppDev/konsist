package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskAssigneeActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.business.usecase.user.CheckUserIdUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskAssigneeUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskAssigneeActivityFactory: UpdateTaskAssigneeActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
) {
    operator fun invoke(taskId: TaskId, newAssigneeId: UserId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        checkUserIdUseCase(newAssigneeId)

        val oldAssigneeId = task.assigneeId

        if (newAssigneeId != oldAssigneeId) {
            val newTask = task.copy(assigneeId = newAssigneeId)

            taskRepository.saveTask(newTask)

            val activity = updateTaskAssigneeActivityFactory(newTask.id, date, oldAssigneeId, newAssigneeId)
            activityRepository.addActivity(activity)
        }
    }
}
