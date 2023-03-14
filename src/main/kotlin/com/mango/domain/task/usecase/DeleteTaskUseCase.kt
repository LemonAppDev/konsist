package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class DeleteTaskUseCase(
    private val taskRepository: TaskRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskActivityFactory: TaskActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId) {
        val task = taskRepository.getTask(taskId)

        task?.let {
            taskRepository.deleteTask(it)

            val date = localDateTimeFactory()
            val activity = taskActivityFactory(it.id, date, TaskActivityType.DELETE)
            activityRepository.addTaskActivity(activity)

            taskRepository.tasks
                .filter { task -> task.parentTaskId == taskId }
                .forEach { task ->
                    taskRepository.deleteTask(task)
                    val childActivity = taskActivityFactory(task.id, date, TaskActivityType.DELETE)
                    activityRepository.addTaskActivity(childActivity)
                }
        }
    }
}
