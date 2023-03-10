package com.mango.domain.comment.usecase

import com.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.mango.domain.comment.CommentFactory
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.request.AddCommentRequestModel
import com.mango.domain.task.usecase.CheckTaskIdUseCase
import org.springframework.stereotype.Service

@Service
class AddCommentUseCase(
    private val addCommentActivityUseCase: AddCommentActivityUseCase,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val commentFactory: CommentFactory,
    private val commentRepository: CommentRepository,
    private val checkCommentTextUseCase: CheckCommentTextUseCase,
) {
    operator fun invoke(addCommentRequestModel: AddCommentRequestModel): Comment {
        checkTaskIdUseCase(addCommentRequestModel.taskId)
        checkCommentTextUseCase(addCommentRequestModel.text)

        val comment = commentFactory(addCommentRequestModel.taskId, addCommentRequestModel.text)

        return commentRepository.saveComment(comment).also {
            addCommentActivityUseCase(comment, comment.creationDate, comment.text)
        }
    }
}
