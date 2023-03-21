package com.mango.data.task

import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TaskRepositoryImpl(
    private val taskJpaEntityToTaskMapper: TaskJpaEntityToTaskMapper,
    private val taskJpaRepository: TaskJpaRepository,
    private val taskToTaskJpaEntityMapper: TaskToTaskJpaEntityMapper,
) : TaskRepository {
    override val tasks
        get() = taskJpaRepository
            .findAll()
            .map { taskJpaEntityToTaskMapper(it) }

    override fun saveTask(task: Task) = taskJpaRepository
        .save(taskToTaskJpaEntityMapper(task))
        .let { taskJpaEntityToTaskMapper(it) }

    override fun getTask(taskId: TaskId) = taskJpaRepository
        .findById(taskId.value)
        .getOrNull()
        ?.let { taskJpaEntityToTaskMapper(it) }

    override fun deleteTask(task: Task) {
        taskJpaRepository.delete(taskToTaskJpaEntityMapper(task))
    }

    override fun containsTask(taskId: TaskId) = taskJpaRepository
        .existsById(taskId.value)
}
