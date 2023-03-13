package com.mango.domain.task.usecase.update

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.mango.domain.user.model.UserId
import com.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskAssigneeUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val taskActivityFactory: TaskActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId, newAssigneeId: UserId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        checkUserIdUseCase(newAssigneeId)

        val oldAssigneeId = task.assigneeId

        if (newAssigneeId != oldAssigneeId) {
            val newTask = task.copy(assigneeId = newAssigneeId)

            taskRepository.saveTask(newTask)

            val activity = taskActivityFactory(
                newTask.value,
                date,
                TaskActivityType.UPDATE_ASSIGNEE,
                newAssigneeId.value.toString(),
                oldAssigneeId?.value.toString(),
            )
            activityRepository.addTaskActivity(activity)
        }
    }
}
