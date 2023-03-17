package com.mango.application.controller

import com.mango.application.model.comment.AddCommentRequestModel
import com.mango.application.model.comment.UpdateCommentRequestModel
import com.mango.application.model.task.CreateTaskRequestModel
import com.mango.application.model.task.UpdateTaskRequestModel
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.usecase.AddCommentUseCase
import com.mango.domain.comment.usecase.DeleteCommentUseCase
import com.mango.domain.comment.usecase.GetCommentOrThrowUseCase
import com.mango.domain.comment.usecase.GetCommentsUseCase
import com.mango.domain.comment.usecase.UpdateCommentUseCase
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.Task
import com.mango.domain.task.usecase.CreateTaskUseCase
import com.mango.domain.task.usecase.DeleteTaskUseCase
import com.mango.domain.task.usecase.DuplicateTaskUseCase
import com.mango.domain.task.usecase.GetAllTasksUseCase
import com.mango.domain.task.usecase.GetChildTasksUseCase
import com.mango.domain.task.usecase.GetTaskActivitiesUseCase
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.mango.domain.task.usecase.update.UpdateTaskUseCase
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
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase = mockk()
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
        getCommentOrThrowUseCase,
        getCommentsUseCase,
        getChildTasksUseCase,
    )

    @Test
    fun `createTask() calls createTaskUseCase()`() {
        // given
        val name = "name"
        val desc = "desc"
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        val priority = 4
        val projectId = getProjectId1()
        val parentTaskId = getTaskId1()
        val assigneeId = getUserId1()

        val createTaskRequestModel = CreateTaskRequestModel(name, desc, dueDate, targetDate, priority, projectId, parentTaskId, assigneeId)
        every { createTaskUseCase(name, desc, dueDate, targetDate, priority, projectId, parentTaskId, assigneeId) } returns mockk()

        // when
        sut.createTask(createTaskRequestModel)

        // then
        verify { createTaskUseCase(name, desc, dueDate, targetDate, priority, projectId, parentTaskId, assigneeId) }
    }

    @Test
    fun `getTask() calls getTaskUseCase() method`() {
        // given
        val taskId = getTaskId1()
        every { getTaskOrThrowUseCase(taskId) } returns mockk()

        // when
        sut.getTask(taskId)

        // then
        verify { getTaskOrThrowUseCase(taskId) }
    }

    @Test
    fun `deleteTask() calls deleteTaskUseCase()`() {
        // given
        val taskId = getTaskId1()
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
        val taskId = getTaskId1()
        val name = "name"
        val desc = "desc"
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        val priority = Priority.PRIORITY_4
        val projectId = getProjectId1()
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId1()
        val task: Task = mockk()
        val updateTaskRequestModel = UpdateTaskRequestModel(
            taskId,
            name,
            desc,
            dueDate,
            targetDate,
            priority,
            projectId,
            parentTaskId,
            assigneeId,
            true,
        )

        justRun {
            updateTaskUseCase(
                taskId,
                name,
                desc,
                dueDate,
                targetDate,
                priority,
                projectId,
                parentTaskId,
                assigneeId,
                true,
            )
        }
        every { getTaskOrThrowUseCase(taskId) } returns task

        // when
        sut.updateTask(updateTaskRequestModel)

        // then
        verify {
            updateTaskUseCase(
                taskId,
                name,
                desc,
                dueDate,
                targetDate,
                priority,
                projectId,
                parentTaskId,
                assigneeId,
                true,
            )
        }
    }

    @Test
    fun `updateTask() returns updated task`() {
        // given
        val taskId = getTaskId1()
        val name = "name"
        val desc = "desc"
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        val priority = Priority.PRIORITY_4
        val projectId = getProjectId1()
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId1()
        val task: Task = mockk()
        val updateTaskRequestModel = UpdateTaskRequestModel(
            taskId,
            name,
            desc,
            dueDate,
            targetDate,
            priority,
            projectId,
            parentTaskId,
            assigneeId,
            true,
        )
        justRun {
            updateTaskUseCase(
                taskId,
                name,
                desc,
                dueDate,
                targetDate,
                priority,
                projectId,
                parentTaskId,
                assigneeId,
                true,
            )
        }
        every { getTaskOrThrowUseCase(taskId) } returns task

        // when
        val actual = sut.updateTask(updateTaskRequestModel)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `duplicateTask() calls duplicateTaskUseCase()`() {
        // given
        val taskId = getTaskId1()
        every { duplicateTaskUseCase(taskId) } returns mockk()

        // when
        sut.duplicateTask(taskId)

        // then
        verify { duplicateTaskUseCase(taskId) }
    }

    @Test
    fun `getActivities() calls getTaskActivityUseCase()`() {
        // given
        val taskId = getTaskId1()
        every { getTaskActivitiesUseCase(taskId) } returns mockk()

        // when
        sut.getTaskActivities(taskId)

        // then
        verify { getTaskActivitiesUseCase(taskId) }
    }

    @Test
    fun `addComment() calls addCommentUseCase()`() {
        // given
        val taskId = getTaskId1()
        val text = "text"
        val addCommentRequestModel = AddCommentRequestModel(taskId, text)
        every { addCommentUseCase(taskId, text) } returns mockk()

        // when
        sut.addComment(addCommentRequestModel)

        // then
        verify { addCommentUseCase(taskId, text) }
    }

    @Test
    fun `deleteComment() calls deleteCommentUseCase()`() {
        // given
        val commentId = getCommentId1()
        justRun { deleteCommentUseCase(commentId) }

        // when
        sut.deleteComment(commentId)

        // then
        verify { deleteCommentUseCase(commentId) }
    }

    @Test
    fun `updateComment() calls updateCommentUseCase()`() {
        // given
        val commentId = getCommentId1()
        val text = "text"
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, text)
        val comment: Comment = mockk()
        every { updateCommentUseCase(commentId, text) } returns mockk()
        every { getCommentOrThrowUseCase(commentId) } returns comment

        // when
        sut.updateComment(updateCommentRequestModel)

        // then
        verify { updateCommentUseCase(commentId, text) }
    }

    @Test
    fun `updateComment() returns updated comment`() {
        // given
        val commentId = getCommentId1()
        val text = "text"
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, text)
        val comment: Comment = mockk()
        every { updateCommentUseCase(commentId, text) } returns mockk()
        every { getCommentOrThrowUseCase(commentId) } returns comment

        // when
        val actual = sut.updateComment(updateCommentRequestModel)

        // then
        actual shouldBeEqualTo comment
    }

    @Test
    fun `getAllComments() calls getAllCommentsUseCase()`() {
        // given
        val taskId = getTaskId1()
        every { getCommentsUseCase(taskId) } returns mockk()

        // when
        sut.getComments(taskId)

        // then
        verify { getCommentsUseCase(taskId) }
    }

    @Test
    fun `getChildTasks() calls getChildTasksUseCase()`() {
        val taskId = getTaskId1()
        every { getChildTasksUseCase(taskId) } returns mockk()

        // when
        sut.getChildTasks(taskId)

        // then
        verify { getChildTasksUseCase(taskId) }
    }
}
