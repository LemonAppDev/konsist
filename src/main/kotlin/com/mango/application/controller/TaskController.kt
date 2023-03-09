package com.mango.application.controller

import com.mango.application.config.ApiConfig
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import com.mango.domain.comment.model.request.AddCommentRequestModel
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.comment.usecase.AddCommentUseCase
import com.mango.domain.comment.usecase.DeleteCommentUseCase
import com.mango.domain.comment.usecase.GetCommentOrThrowUseCase
import com.mango.domain.comment.usecase.GetCommentsUseCase
import com.mango.domain.comment.usecase.UpdateCommentUseCase
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.task.model.request.UpdateTaskRequestModel
import com.mango.domain.task.usecase.CreateTaskUseCase
import com.mango.domain.task.usecase.DeleteTaskUseCase
import com.mango.domain.task.usecase.DuplicateTaskUseCase
import com.mango.domain.task.usecase.GetAllTasksUseCase
import com.mango.domain.task.usecase.GetChildTasksUseCase
import com.mango.domain.task.usecase.GetTaskActivitiesUseCase
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.mango.domain.task.usecase.update.UpdateTaskUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiConfig.API_V1_URL + "/task")
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
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getChildTasksUseCase: GetChildTasksUseCase,
) {
    @PostMapping("/create")
    fun createTask(@RequestBody createTaskRequestModel: CreateTaskRequestModel) = createTaskUseCase(createTaskRequestModel)

    @GetMapping("/get")
    fun getTask(@RequestParam(name = "taskId") taskId: TaskId) = getTaskOrThrowUseCase(taskId)

    @GetMapping("/all")
    fun getTasks() = getAllTasksUseCase()

    @PostMapping("/update")
    fun updateTask(@RequestBody updateTaskRequestModel: UpdateTaskRequestModel): Task {
        updateTaskUseCase(updateTaskRequestModel)
        return getTaskOrThrowUseCase(updateTaskRequestModel.taskId)
    }

    @DeleteMapping("/delete")
    fun deleteTask(@RequestParam(name = "taskId") taskId: TaskId) = deleteTaskUseCase(taskId)

    @PostMapping("/duplicate")
    fun duplicateTask(@RequestParam(name = "taskId") taskId: TaskId) = duplicateTaskUseCase.invoke(taskId)

    @GetMapping("/activities")
    fun getActivities(@RequestParam(name = "taskId") taskId: TaskId) = getTaskActivitiesUseCase(taskId)

    @GetMapping("/child-tasks")
    fun getChildTasks(@RequestParam(name = "taskId") taskId: TaskId) = getChildTasksUseCase(taskId)

    @PostMapping("/add-comment")
    fun addComment(@RequestBody addCommentRequestModel: AddCommentRequestModel) = addCommentUseCase(addCommentRequestModel)

    @GetMapping("/comment")
    fun getComment(@RequestParam(name = "commentId") commentId: CommentId) = getCommentOrThrowUseCase(commentId)

    @PostMapping("/update-comment")
    fun updateComment(@RequestBody updateCommentRequestModel: UpdateCommentRequestModel): Comment {
        updateCommentUseCase(updateCommentRequestModel)
        return getCommentOrThrowUseCase(updateCommentRequestModel.commentId)
    }

    @GetMapping("/comments-all")
    fun getComments(@RequestParam(name = "taskId") taskId: TaskId) = getCommentsUseCase(taskId)

    @DeleteMapping("/delete-comment")
    fun deleteComment(@RequestParam(name = "commentId") commentId: CommentId) = deleteCommentUseCase(commentId)
}
