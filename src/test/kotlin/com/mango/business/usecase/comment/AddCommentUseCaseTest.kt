package com.mango.business.usecase.comment

import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.model.Task
import com.mango.business.model.activity.task.AddCommentActivity
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddCommentUseCaseTest {
    private val commentFactory: CommentFactory = mockk()
    private val commentRepository: CommentRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val addCommentActivityFactory: AddCommentActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = AddCommentUseCase(
        commentFactory,
        commentRepository,
        activityRepository,
        addCommentActivityFactory,
        getTaskUseCase,
    )

    @Test
    fun `add comment to repository`() {
        // given
        val taskId = TaskId("id")
        val addCommentRequestModel = AddCommentRequestModel(taskId, "comment")
        val task: Task = mockk()
        every { task.id } returns taskId
        every { getTaskUseCase(taskId) } returns task
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
        every { getTaskUseCase(TaskId("id")) } returns task
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
