package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.domain.comment.CommentRepository
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class GetCommentsUseCase(
    private val commentRepository: CommentRepository,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(taskId: TaskId): List<Comment> {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        return commentRepository.comments
            .filter { it.taskId == taskId }
    }
}
