package com.mango.domain.comment.usecase

import com.mango.domain.comment.CommentFactory
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.CheckTaskIdUseCase
import org.springframework.stereotype.Service

@Suppress("LongParameterList")
@Service
class CreateCommentUseCase(
    private val commentFactory: CommentFactory,
    private val commentRepository: CommentRepository,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
) {
    operator fun invoke(taskId: TaskId, text: String): Comment {
        checkTaskIdUseCase(taskId)
        require(text.isNotBlank()) { "Comment text is blank" }

        val comment = commentFactory(taskId, text)
        return commentRepository.saveComment(comment)
    }
}
