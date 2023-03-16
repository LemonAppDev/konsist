package com.mango.domain.task.usecase.update

import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
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
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newAssigneeId: UserId?, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        newAssigneeId?.let { checkUserIdUseCase(it) }

        val oldAssigneeId = task.assigneeId

        if (newAssigneeId != oldAssigneeId) {
            val newTask = task.copy(assigneeId = newAssigneeId)

            taskRepository.saveTask(newTask)

            addTaskActivityUseCase(
                newTask.id,
                TaskActivityType.UPDATE_ASSIGNEE,
                date,
                newAssigneeId?.value.toString(),
                oldAssigneeId?.value.toString(),
            )
        }
    }
}
