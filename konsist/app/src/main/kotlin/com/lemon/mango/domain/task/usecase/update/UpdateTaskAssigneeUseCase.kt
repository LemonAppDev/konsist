package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.lemon.mango.domain.user.model.UserId
import com.lemon.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskAssigneeUseCase(
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskRepository: TaskRepository,
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
