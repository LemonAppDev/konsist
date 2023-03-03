package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import com.mango.persistence.repository.TaskRepository
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val createTaskActivityFactory: CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val taskFactory: TaskFactory,
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(createTaskRequestModel: CreateTaskRequestModel): Task {
        createTaskRequestModel.projectId?.let {
            val projectExists = projectRepository.containsProject(it)
            require(projectExists) { "Project with id: $it doesn't exist" }
        }

        createTaskRequestModel.parentTaskId?.let {
            val parentTaskExists = taskRepository.containsTask(it)
            require(parentTaskExists) { "Parent task with id: $it doesn't exist" }
        }

        createTaskRequestModel.assigneeId?.let {
            val assigneeExists = userRepository.containsUser(it)
            require(assigneeExists) { "Assignee with id: $it doesn't exist" }
        }

        val task = taskFactory(createTaskRequestModel)

        taskRepository.addTask(task)

        val activity = createTaskActivityFactory(task.id, task.creationDate)
        activityRepository.addActivity(activity)

        return task
    }
}
