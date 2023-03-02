package com.mango.presentation.controller

import com.mango.business.model.Task
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
import com.mango.business.usecase.task.GetTasksUseCase
import com.mango.business.usecase.task.comment.AddCommentUseCase
import com.mango.business.usecase.task.comment.DeleteCommentUseCase
import com.mango.business.usecase.task.comment.GetAllCommentsUseCase
import com.mango.business.usecase.task.comment.UpdateCommentUseCase
import com.mango.business.usecase.task.update.UpdateTaskUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val duplicateTaskUseCase: DuplicateTaskUseCase,
    private val getTaskActivitiesUseCase: GetTaskActivitiesUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase,
    private val getAllCommentsUseCase: GetAllCommentsUseCase,
) {
    @PostMapping("/v1/task/create")
    fun createTask(@RequestBody createTaskRequestModel: CreateTaskRequestModel) =
        createTaskUseCase(createTaskRequestModel)

    @GetMapping("/v1/task/get")
    fun getTask(taskId: TaskId) = getTasksUseCase(taskId)

    @DeleteMapping("/v1/task/delete")
    fun deleteTask(@RequestParam taskId: TaskId) = deleteTaskUseCase(taskId)

    @GetMapping("/v1/task/all")
    fun getAllTasks() = getAllTasksUseCase()

    @PostMapping("/v1/task/update")
    fun updateTask(@RequestBody updateTaskRequestModel: UpdateTaskRequestModel): Task {
        updateTaskUseCase(updateTaskRequestModel)
        return getTasksUseCase(updateTaskRequestModel.taskId)
    }

    @PostMapping("/v1/task/duplicate")
    fun duplicateTask(@RequestParam(name = "taskId") taskId: TaskId) =
        duplicateTaskUseCase.invoke(taskId)

    @GetMapping("/v1/task/activity")
    fun getTaskActivity(@RequestParam(name = "taskId") taskId: TaskId) =
        getTaskActivitiesUseCase(taskId)

    @PostMapping("/v1/task/addComment")
    fun addComment(@RequestBody addCommentRequestModel: AddCommentRequestModel) =
        addCommentUseCase(addCommentRequestModel)

    @DeleteMapping("/v1/task/deleteComment")
    fun deleteComment(@RequestParam(name = "commentId") commentId: CommentId) =
        deleteCommentUseCase(commentId)

    @PatchMapping("/v1/task/updateComment")
    fun updateComment(@RequestBody updateCommentRequestModel: UpdateCommentRequestModel) {
        updateCommentUseCase(updateCommentRequestModel)
    }

    @GetMapping("/v1/task/allComments")
    fun getAllComments(@RequestParam(name = "taskId") taskId: TaskId) =
        getAllCommentsUseCase(taskId)
}
