package com.mango.business.usecase.task.comment

import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.model.Task
import com.mango.business.model.activity.task.AddCommentActivity
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.AddCommentRequestModel
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import com.mango.persistence.repository.TaskRepository
import com.mango.persistence.repository.UserRepository
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
    private val taskRepository: TaskRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val commentRepository: CommentRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val addCommentActivityFactory: AddCommentActivityFactory = mockk()

    private val sut = AddCommentUseCase(
        commentFactory,
        taskRepository,
        userRepository,
        commentRepository,
        activityRepository,
        addCommentActivityFactory,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        val addCommentRequestModel: AddCommentRequestModel = mockk()
        every { addCommentRequestModel.taskId } returns taskId
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(addCommentRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task doesn't exist id: $taskId"
    }

    @Test
    fun `add comment to repository`() {
        // given
        val taskId = TaskId("id")
        val addCommentRequestModel = AddCommentRequestModel(taskId, "comment")
        val task: Task = mockk()
        every { task.id } returns taskId
        every { taskRepository.getTask(taskId) } returns task
        val date: LocalDateTime = mockk()
        val userId = UserId("id")
        every { userRepository.getCurrentUser().id } returns userId
        val comment: Comment = mockk()
        val text = "comment"
        every { comment.text } returns text
        every { comment.creationDate } returns date
        every { commentFactory(addCommentRequestModel, userId) } returns comment

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
        every { taskRepository.getTask(TaskId("id")) } returns task
        val date: LocalDateTime = mockk()
        val userId = UserId("id")
        every { userRepository.getCurrentUser().id } returns userId
        val comment: Comment = mockk()
        val text = "comment"
        every { comment.text } returns text
        every { comment.creationDate } returns date
        every { commentFactory(addCommentRequestModel, userId) } returns comment

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
