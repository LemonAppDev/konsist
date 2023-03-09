package com.mango.business.usecase.comment

import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.CheckTaskIdUseCase
import com.mango.persistence.repository.CommentRepository
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
