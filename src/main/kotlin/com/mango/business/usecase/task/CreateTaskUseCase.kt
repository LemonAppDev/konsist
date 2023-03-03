package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
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

        val task = taskFactory(createTaskRequestModel)

        taskRepository.addTask(task)

        val activity = createTaskActivityFactory(task.id, task.creationDate)
        activityRepository.addActivity(activity)

        return task
    }
}
