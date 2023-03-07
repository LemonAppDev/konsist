package com.mango.business.usecase.comment

import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.model.Task
import com.mango.business.model.activity.task.AddCommentActivity
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddCommentUseCaseTest {
    private val commentFactory: CommentFactory = mockk()
    private val commentRepository: CommentRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val addCommentActivityFactory: AddCommentActivityFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = AddCommentUseCase(
        commentFactory,
        commentRepository,
        activityRepository,
        addCommentActivityFactory,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `throws exception when comment has no text`() {
        // given
        val taskId = TaskId("id")
        val addCommentRequestModel = AddCommentRequestModel(taskId, "")
        val task: Task = mockk()
        every { task.id } returns taskId
        every { getTaskOrThrowUseCase(taskId) } returns task

        // when
        val actual = { sut(addCommentRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Comment text is blank"
    }

    @Test
    fun `add comment to repository`() {
        // given
        val taskId = TaskId("id")
        val addCommentRequestModel = AddCommentRequestModel(taskId, "comment")
        val task: Task = mockk()
        every { task.id } returns taskId
        every { getTaskOrThrowUseCase(taskId) } returns task
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        val text = "comment"
        every { comment.text } returns text
        every { comment.creationDate } returns date
        every { commentFactory(addCommentRequestModel) } returns comment

        justRun { commentRepository.addComment(comment) }
        val activity: AddCommentActivity = mockk()
        every { addCommentActivityFactory(taskId, date, text) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(addCommentRequestModel)

        // then
        verify { commentRepository.addComment(comment) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("id")
        val addCommentRequestModel = AddCommentRequestModel(taskId, "comment")
        val task: Task = mockk()
        every { task.id } returns taskId
        every { getTaskOrThrowUseCase(TaskId("id")) } returns task
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        val text = "comment"
        every { comment.text } returns text
        every { comment.creationDate } returns date
        every { commentFactory(addCommentRequestModel) } returns comment

        justRun { commentRepository.addComment(comment) }
        val activity: AddCommentActivity = mockk()
        every { addCommentActivityFactory(taskId, date, text) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(addCommentRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
