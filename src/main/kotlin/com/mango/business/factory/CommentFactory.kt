package com.mango.business.factory

import com.mango.business.model.Comment
import com.mango.business.model.request.AddCommentRequestModel
import com.mango.business.model.value.UserId
import org.springframework.stereotype.Service

@Service
class CommentFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(
        addCommentRequestModel: AddCommentRequestModel,
        creatorId: UserId,
    ) = Comment(
        uuidFactory.createCommentId(),
        addCommentRequestModel.text,
        addCommentRequestModel.taskId,
        creatorId,
        localDateTimeFactory(),
    )
}
