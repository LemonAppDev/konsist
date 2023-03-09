package com.mango.persistence.repository

import com.mango.business.model.Task
import com.mango.business.model.value.TaskId
import com.mango.persistence.datasource.TaskJpaRepository
import com.mango.persistence.entity.mapper.TaskJpaEntityToTaskMapper
import com.mango.persistence.entity.mapper.TaskToTaskJpaEntityMapper
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TaskRepository(
    private val taskJpaRepository: TaskJpaRepository,
    private val taskToTaskJpaEntityMapper: TaskToTaskJpaEntityMapper,
    private val taskJpaEntityToTaskMapper: TaskJpaEntityToTaskMapper,
) {
    val tasks
        get() = taskJpaRepository
            .findAll()
            .map { taskJpaEntityToTaskMapper(it) }

    fun saveTask(task: Task) = taskJpaRepository
        .save(taskToTaskJpaEntityMapper(task))
        .let { taskJpaEntityToTaskMapper(it) }

    fun getTask(taskId: TaskId) = taskJpaRepository
        .findById(taskId.value)
        .getOrNull()
        ?.let { taskJpaEntityToTaskMapper(it) }

    fun deleteTask(task: Task) {
        taskJpaRepository.delete(taskToTaskJpaEntityMapper(task))
    }

    fun containsTask(taskId: TaskId) = taskJpaRepository
        .existsById(taskId.value)
}
