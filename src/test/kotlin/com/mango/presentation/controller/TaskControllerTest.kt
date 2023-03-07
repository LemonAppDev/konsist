package com.mango.presentation.controller

import com.mango.business.model.Comment
import com.mango.business.model.Task
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.business.model.request.comment.UpdateCommentRequestModel
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.model.request.task.UpdateTaskRequestModel
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.comment.AddCommentUseCase
import com.mango.business.usecase.comment.DeleteCommentUseCase
import com.mango.business.usecase.comment.GetCommentUseCase
import com.mango.business.usecase.comment.GetCommentsUseCase
import com.mango.business.usecase.comment.UpdateCommentUseCase
import com.mango.business.usecase.task.CreateTaskUseCase
import com.mango.business.usecase.task.DeleteTaskUseCase
import com.mango.business.usecase.task.DuplicateTaskUseCase
import com.mango.business.usecase.task.GetAllTasksUseCase
import com.mango.business.usecase.task.GetChildTasksUseCase
import com.mango.business.usecase.task.GetTaskActivitiesUseCase
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.business.usecase.task.update.UpdateTaskUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TaskControllerTest {
    private val createTaskUseCase: CreateTaskUseCase = mockk()
    private val deleteTaskUseCase: DeleteTaskUseCase = mockk()
    private val getAllTasksUseCase: GetAllTasksUseCase = mockk()
    private val updateTaskUseCase: UpdateTaskUseCase = mockk()
    private val duplicateTaskUseCase: DuplicateTaskUseCase = mockk()
    private val getTaskActivitiesUseCase: GetTaskActivitiesUseCase = mockk()
    private val addCommentUseCase: AddCommentUseCase = mockk()
    private val deleteCommentUseCase: DeleteCommentUseCase = mockk()
    private val updateCommentUseCase: UpdateCommentUseCase = mockk()
    private val getCommentUseCase: GetCommentUseCase = mockk()
    private val getCommentsUseCase: GetCommentsUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val getChildTasksUseCase: GetChildTasksUseCase = mockk()

    private val sut = TaskController(
        createTaskUseCase,
        getTaskOrThrowUseCase,
        deleteTaskUseCase,
        getAllTasksUseCase,
        updateTaskUseCase,
        duplicateTaskUseCase,
        getTaskActivitiesUseCase,
        addCommentUseCase,
        deleteCommentUseCase,
        updateCommentUseCase,
        getCommentUseCase,
        getCommentsUseCase,
        getChildTasksUseCase,
    )

    @Test
    fun `createTask() calls createTaskUseCase()`() {
        // given
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskUseCase.invoke(createTaskRequestModel) } returns mockk()

        // when
        sut.createTask(createTaskRequestModel)

        // then
        verify { createTaskUseCase.invoke(createTaskRequestModel) }
    }

    @Test
    fun `getTask() calls getTaskUseCase() method`() {
        // given
        val taskId = TaskId("id")
        every { getTaskOrThrowUseCase(taskId) } returns mockk()

        // when
        sut.getTask(taskId)

        // then
        verify { getTaskOrThrowUseCase(taskId) }
    }

    @Test
    fun `deleteTask() calls deleteTaskUseCase()`() {
        // given
        val taskId = TaskId("id")
        justRun { deleteTaskUseCase(taskId) }

        // when
        sut.deleteTask(taskId)

        // then
        verify { deleteTaskUseCase(taskId) }
    }

    @Test
    fun `getAllTasks() calls getAllTasksUseCase()`() {
        // given
        every { getAllTasksUseCase() } returns mockk()

        // when
        sut.getTasks()

        // then
        verify { getAllTasksUseCase() }
    }

    @Test
    fun `updateTask() calls updateTaskUseCase()`() {
        // given
        val taskId = TaskId("id")
        val task: Task = mockk()
        val updateTaskRequestModel: UpdateTaskRequestModel = mockk()
        every { updateTaskRequestModel.taskId } returns taskId

        justRun { updateTaskUseCase(updateTaskRequestModel) }
        every { getTaskOrThrowUseCase(taskId) } returns task

        // when
        sut.updateTask(updateTaskRequestModel)

        // then
        verify { updateTaskUseCase(updateTaskRequestModel) }
    }

    @Test
    fun `updateTask() calls returns updated task`() {
        // given
        val taskId = TaskId("id")
        val task: Task = mockk()
        val updateTaskRequestModel: UpdateTaskRequestModel = mockk()
        every { updateTaskRequestModel.taskId } returns taskId
        justRun { updateTaskUseCase(updateTaskRequestModel) }
        every { getTaskOrThrowUseCase(taskId) } returns task

        // when
        val actual = sut.updateTask(updateTaskRequestModel)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `duplicateTask() calls duplicateTaskUseCase()`() {
        // given
        val taskId = TaskId("id")
        every { duplicateTaskUseCase(taskId) } returns mockk()

        // when
        sut.duplicateTask(taskId)

        // then
        verify { duplicateTaskUseCase(taskId) }
    }

    @Test
    fun `getActivities() calls getTaskActivityUseCase()`() {
        // given
        val taskId = TaskId("id")
        every { getTaskActivitiesUseCase(taskId) } returns mockk()

        // when
        sut.getActivities(taskId)

        // then
        verify { getTaskActivitiesUseCase(taskId) }
    }

    @Test
    fun `addComment() calls addCommentUseCase()`() {
        // given
        val addCommentRequestModel: AddCommentRequestModel = mockk()
        every { addCommentUseCase(addCommentRequestModel) } returns mockk()

        // when
        sut.addComment(addCommentRequestModel)

        // then
        verify { addCommentUseCase(addCommentRequestModel) }
    }

    @Test
    fun `deleteComment() calls deleteCommentUseCase()`() {
        // given
        val commentId = CommentId("id")
        justRun { deleteCommentUseCase(commentId) }

        // when
        sut.deleteComment(commentId)

        // then
        verify { deleteCommentUseCase(commentId) }
    }

    @Test
    fun `updateComment() calls updateCommentUseCase()`() {
        // given
        val updateCommentRequestModel: UpdateCommentRequestModel = mockk()
        justRun { updateCommentUseCase(updateCommentRequestModel) }
        val commentId = CommentId("id")
        val comment: Comment = mockk()
        every { updateCommentRequestModel.commentId } returns commentId
        every { getCommentUseCase(commentId) } returns comment

        // when
        sut.updateComment(updateCommentRequestModel)

        // then
        verify { updateCommentUseCase(updateCommentRequestModel) }
    }

    @Test
    fun `updateComment() calls returns updated comment`() {
        // given
        val commentId = CommentId("id")
        val comment: Comment = mockk()
        val updateCommentRequestModel: UpdateCommentRequestModel = mockk()
        every { updateCommentRequestModel.commentId } returns commentId
        justRun { updateCommentUseCase(updateCommentRequestModel) }
        every { getCommentUseCase(commentId) } returns comment

        // when
        val actual = sut.updateComment(updateCommentRequestModel)

        // then
        actual shouldBeEqualTo comment
    }

    @Test
    fun `getAllComments() calls getAllCommentsUseCase()`() {
        // given
        val taskId = TaskId("id")
        every { getCommentsUseCase(taskId) } returns mockk()

        // when
        sut.getComments(taskId)

        // then
        verify { getCommentsUseCase(taskId) }
    }

    @Test
    fun `getChildTasks() calls getChildTasksUseCase()`() {
        val taskId = TaskId("id")
        every { getChildTasksUseCase(taskId) } returns mockk()

        // when
        sut.getChildTasks(taskId)

        // then
        verify { getChildTasksUseCase(taskId) }
    }
}
