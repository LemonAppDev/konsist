package com.mango.domain.comment.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.request.AddCommentRequestModel
import com.mango.domain.task.activity.AddCommentActivityFactory
import org.springframework.stereotype.Service

@Suppress("LongParameterList")
@Service
class AddCommentUseCase(
    private val createCommentUseCase: CreateCommentUseCase,
    private val activityRepository: ActivityRepository,
    private val addCommentActivityFactory: AddCommentActivityFactory,
) {
    operator fun invoke(addCommentRequestModel: AddCommentRequestModel): Comment {
        val comment = createCommentUseCase(addCommentRequestModel.taskId, addCommentRequestModel.text)

        return comment.also {
            val activity = addCommentActivityFactory(comment.taskId, comment.creationDate, comment.text)
            activityRepository.addActivity(activity)
        }
    }
}
