package com.mango.domain.task.usecase.update

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskParentTaskUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val taskActivityFactory: TaskActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId, newParentTaskId: TaskId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)
        val parentTask = getTaskOrThrowUseCase(newParentTaskId)

        val oldParentTaskId = task.parentTaskId

        if (newParentTaskId != oldParentTaskId) {
            require(parentTask.projectId == task.projectId) { "Task and parent task are not in the same project" }
            val newTask = task.copy(parentTaskId = newParentTaskId)

            taskRepository.saveTask(newTask)

            val activity = taskActivityFactory(
                newTask.id,
                date,
                TaskActivityType.UPDATE_PARENT_TASK,
                newParentTaskId.value.toString(),
                oldParentTaskId?.value.toString(),
            )
            activityRepository.addTaskActivity(activity)
        }
    }
}
