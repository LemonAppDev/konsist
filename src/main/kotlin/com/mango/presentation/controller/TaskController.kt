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
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val duplicateTaskUseCase: DuplicateTaskUseCase,
    private val getTaskActivitiesUseCase: GetTaskActivitiesUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase,
    private val getCommentUseCase: GetCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getChildTasksUseCase: GetChildTasksUseCase,
) {
    @PostMapping("/v1/task/create")
    fun createTask(@RequestBody createTaskRequestModel: CreateTaskRequestModel) =
        createTaskUseCase.invoke(createTaskRequestModel)

    @GetMapping("/v1/task/get")
    fun getTask(taskId: TaskId) = getTaskOrThrowUseCase(taskId)

    @DeleteMapping("/v1/task/delete")
    fun deleteTask(@RequestParam taskId: TaskId) = deleteTaskUseCase(taskId)

    @GetMapping("/v1/task/all")
    fun getTasks() = getAllTasksUseCase()

    @PostMapping("/v1/task/update")
    fun updateTask(@RequestBody updateTaskRequestModel: UpdateTaskRequestModel): Task {
        updateTaskUseCase(updateTaskRequestModel)
        return getTaskOrThrowUseCase(updateTaskRequestModel.taskId)
    }

    @PostMapping("/v1/task/duplicate")
    fun duplicateTask(@RequestParam(name = "taskId") taskId: TaskId) =
        duplicateTaskUseCase.invoke(taskId)

    @GetMapping("/v1/task/activities")
    fun getActivities(@RequestParam(name = "taskId") taskId: TaskId) =
        getTaskActivitiesUseCase(taskId)

    @PostMapping("/v1/task/add-comment")
    fun addComment(@RequestBody addCommentRequestModel: AddCommentRequestModel) =
        addCommentUseCase(addCommentRequestModel)

    @DeleteMapping("/v1/task/delete-comment")
    fun deleteComment(@RequestParam(name = "commentId") commentId: CommentId) =
        deleteCommentUseCase(commentId)

    @PostMapping("/v1/task/update-comment")
    fun updateComment(@RequestBody updateCommentRequestModel: UpdateCommentRequestModel): Comment {
        updateCommentUseCase(updateCommentRequestModel)
        return getCommentUseCase(updateCommentRequestModel.commentId)
    }

    @GetMapping("/v1/task/comments-all")
    fun getComments(@RequestParam(name = "taskId") taskId: TaskId) =
        getCommentsUseCase(taskId)

    @GetMapping("/v1/task/child-tasks")
    fun getChildTasks(@RequestParam(name = "taskId") taskId: TaskId) =
        getChildTasksUseCase(taskId)
}
