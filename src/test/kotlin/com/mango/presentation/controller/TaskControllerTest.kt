package com.mango.presentation.controller

import com.mango.business.model.request.AddCommentRequestModel
import com.mango.business.model.request.CreateTaskRequestModel
import com.mango.business.model.request.UpdateCommentRequestModel
import com.mango.business.model.request.UpdateTaskRequestModel
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.CreateTaskUseCase
import com.mango.business.usecase.task.DeleteTaskUseCase
import com.mango.business.usecase.task.DuplicateTaskUseCase
import com.mango.business.usecase.task.GetAllTasksUseCase
import com.mango.business.usecase.task.GetTaskActivitiesUseCase
import com.mango.business.usecase.task.comment.AddCommentUseCase
import com.mango.business.usecase.task.comment.DeleteCommentUseCase
import com.mango.business.usecase.task.comment.GetAllCommentsUseCase
import com.mango.business.usecase.task.comment.UpdateCommentUseCase
import com.mango.business.usecase.task.update.UpdateTaskUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
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
    private val getAllCommentsUseCase: GetAllCommentsUseCase = mockk()

    private val sut = TaskController(
        createTaskUseCase,
        deleteTaskUseCase,
        getAllTasksUseCase,
        updateTaskUseCase,
        duplicateTaskUseCase,
        getTaskActivitiesUseCase,
        addCommentUseCase,
        deleteCommentUseCase,
        updateCommentUseCase,
        getAllCommentsUseCase,
    )

    @Test
    fun `createTask() calls createTaskUseCase()`() {
        // given
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskUseCase(createTaskRequestModel) } returns mockk()

        // when
        sut.createTask(createTaskRequestModel)

        // then
        verify { createTaskUseCase(createTaskRequestModel) }
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
        sut.getAllTasks()

        // then
        verify { getAllTasksUseCase() }
    }

    @Test
    fun `updateTask() calls updateTaskUseCase()`() {
        // given
        val updateTaskRequestModel: UpdateTaskRequestModel = mockk()
        justRun { updateTaskUseCase(updateTaskRequestModel) }

        // when
        sut.updateTask(updateTaskRequestModel)

        // then
        verify { updateTaskUseCase(updateTaskRequestModel) }
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
    fun `getTaskActivities() calls getTaskActivityUseCase()`() {
        // given
        val taskId = TaskId("id")
        every { getTaskActivitiesUseCase(taskId) } returns mockk()

        // when
        sut.getTaskActivity(taskId)

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

        // when
        sut.updateComment(updateCommentRequestModel)

        // then
        verify { updateCommentUseCase(updateCommentRequestModel) }
    }

    @Test
    fun `getAllComments() calls getAllCommentsUseCase()`() {
        // given
        val taskId = TaskId("id")
        every { getAllCommentsUseCase(taskId) } returns mockk()

        // when
        sut.getAllComments(taskId)

        // then
        verify { getAllCommentsUseCase(taskId) }
    }
}
