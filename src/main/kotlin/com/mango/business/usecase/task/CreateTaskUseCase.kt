package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.usecase.project.CheckProjectIdUseCase
import com.mango.business.usecase.user.CheckUserIdUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val createTaskActivityFactory: CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val taskFactory: TaskFactory,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
) {
    fun invoke(createTaskRequestModel: CreateTaskRequestModel): Task {
        createTaskRequestModel.projectId?.let { checkProjectIdUseCase(it) }
        createTaskRequestModel.parentTaskId?.let { checkTaskIdUseCase(it) }
        createTaskRequestModel.assigneeId?.let { checkUserIdUseCase(it) }

        val task = taskFactory(createTaskRequestModel)

        taskRepository.addTask(task)

        val activity = createTaskActivityFactory(task.id, task.creationDate)
        activityRepository.addActivity(activity)

        return task
    }
}
