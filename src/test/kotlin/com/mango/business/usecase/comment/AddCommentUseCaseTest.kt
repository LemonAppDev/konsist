package com.mango.business.usecase.comment

import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.AddCommentActivity
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.persistence.repository.ActivityRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddCommentUseCaseTest {
    private val createCommentUseCase: CreateCommentUseCase = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val addCommentActivityFactory: AddCommentActivityFactory = mockk()

    private val sut = AddCommentUseCase(
        createCommentUseCase,
        activityRepository,
        addCommentActivityFactory,
    )

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val text = "comment"
        val addCommentRequestModel = AddCommentRequestModel(taskId, text)
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.taskId } returns taskId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        every { createCommentUseCase(taskId, text) } returns comment

        val activity: AddCommentActivity = mockk()
        every { addCommentActivityFactory(taskId, date, text) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(addCommentRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `returns comment`() {
        // given
        val taskId = getTaskId1()
        val text = "comment"
        val addCommentRequestModel = AddCommentRequestModel(taskId, text)
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.taskId } returns taskId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        every { createCommentUseCase(taskId, text) } returns comment

        val activity: AddCommentActivity = mockk()
        every { addCommentActivityFactory(taskId, date, text) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        val actual = sut(addCommentRequestModel)

        // then
        actual shouldBeEqualTo comment
    }
}
