package com.mango.business.usecase.comment

import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import org.springframework.stereotype.Service

@Suppress("LongParameterList")
@Service
class AddCommentUseCase(
    private val commentFactory: CommentFactory,
    private val commentRepository: CommentRepository,
    private val activityRepository: ActivityRepository,
    private val addCommentActivityFactory: AddCommentActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(addCommentRequestModel: AddCommentRequestModel): Comment {
        val task = getTaskOrThrowUseCase(addCommentRequestModel.taskId)
        require(addCommentRequestModel.text.isNotBlank()) { "Comment text is blank" }

        val comment = commentFactory(addCommentRequestModel)

        commentRepository.addComment(comment)

        val activity = addCommentActivityFactory(task.id, comment.creationDate, comment.text)
        activityRepository.addActivity(activity)

        return comment
    }
}
