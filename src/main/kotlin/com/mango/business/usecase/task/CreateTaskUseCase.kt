package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.request.CreateTaskRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
@Suppress("detekt.LongParameterList")
class CreateTaskUseCase(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
    private val createTaskActivityFactory: CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val taskFactory: TaskFactory,
) {
    operator fun invoke(createTaskRequestModel: CreateTaskRequestModel): Task {
        createTaskRequestModel.parentTaskId?.let {
            val parentTaskExists = taskRepository.containsTask(it)
            require(parentTaskExists) { "Parent task with id: $it doesn't exist" }
        }

        val task = taskFactory(
            createTaskRequestModel.name,
            userRepository.getCurrentUser().id,
            projectId = createTaskRequestModel.projectId,
            description = createTaskRequestModel.description,
            dueDate = createTaskRequestModel.dueDate,
            targetDate = createTaskRequestModel.targetDate,
            priority = Priority.getByValue(createTaskRequestModel.priority),
            parentTaskId = createTaskRequestModel.parentTaskId,
            assigneeId = createTaskRequestModel.assigneeId,
        )

        taskRepository.addTask(task)

        val activity = createTaskActivityFactory(task.id, task.creationDate)
        activityRepository.addActivity(activity)

        return task
    }
}
