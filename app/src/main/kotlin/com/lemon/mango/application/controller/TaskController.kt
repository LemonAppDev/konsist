package com.lemon.mango.application.controller

import com.lemon.mango.application.config.ApiConfig
import com.lemon.mango.application.model.comment.AddCommentRequestModel
import com.lemon.mango.application.model.comment.UpdateCommentRequestModel
import com.lemon.mango.application.model.task.CreateTaskRequestModel
import com.lemon.mango.application.model.task.UpdateTaskRequestModel
import com.lemon.mango.domain.activity.model.TaskActivity
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.comment.model.CommentId
import com.lemon.mango.domain.comment.usecase.AddCommentUseCase
import com.lemon.mango.domain.comment.usecase.DeleteCommentUseCase
import com.lemon.mango.domain.comment.usecase.GetCommentOrThrowUseCase
import com.lemon.mango.domain.comment.usecase.GetCommentsUseCase
import com.lemon.mango.domain.comment.usecase.UpdateCommentUseCase
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.CreateTaskUseCase
import com.lemon.mango.domain.task.usecase.DeleteTaskUseCase
import com.lemon.mango.domain.task.usecase.DuplicateTaskUseCase
import com.lemon.mango.domain.task.usecase.GetAllTasksUseCase
import com.lemon.mango.domain.task.usecase.GetChildTasksUseCase
import com.lemon.mango.domain.task.usecase.GetTaskActivitiesUseCase
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.lemon.mango.domain.task.usecase.update.UpdateTaskUseCase
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
    private val addCommentUseCase: AddCommentUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val duplicateTaskUseCase: DuplicateTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getChildTasksUseCase: GetChildTasksUseCase,
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getTaskActivitiesUseCase: GetTaskActivitiesUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) {
    @PostMapping("/create")
    fun createTask(@RequestBody requestModel: CreateTaskRequestModel): Task =
        createTaskUseCase(
            requestModel.name,
            requestModel.description,
            requestModel.dueDate,
            requestModel.targetDate,
            requestModel.priority,
            requestModel.projectId,
            requestModel.parentTaskId,
            requestModel.assigneeId,
        )

    @GetMapping("/get")
    fun getTask(@RequestParam(name = "taskId") taskId: TaskId): Task = getTaskOrThrowUseCase(taskId)

    @GetMapping("/all")
    fun getTasks(): List<Task> = getAllTasksUseCase()

    @PostMapping("/update")
    fun updateTask(@RequestBody requestModel: UpdateTaskRequestModel): Task {
        updateTaskUseCase(
            requestModel.taskId,
            requestModel.name,
            requestModel.description,
            requestModel.dueDate,
            requestModel.targetDate,
            requestModel.priority,
            requestModel.projectId,
            requestModel.parentTaskId,
            requestModel.assigneeId,
            requestModel.isCompleted,
        )
        return getTaskOrThrowUseCase(requestModel.taskId)
    }

    @DeleteMapping("/delete")
    fun deleteTask(@RequestParam(name = "taskId") taskId: TaskId): Unit = deleteTaskUseCase(taskId)

    @PostMapping("/duplicate")
    fun duplicateTask(@RequestParam(name = "taskId") taskId: TaskId): Task = duplicateTaskUseCase(taskId)

    @GetMapping("/activities")
    fun getTaskActivities(@RequestParam(name = "taskId") taskId: TaskId): List<TaskActivity> = getTaskActivitiesUseCase(taskId)

    @GetMapping("/child-tasks")
    fun getChildTasks(@RequestParam(name = "taskId") taskId: TaskId): List<Task> = getChildTasksUseCase(taskId)

    @PostMapping("/add-comment")
    fun addComment(@RequestBody addCommentRequestModel: AddCommentRequestModel): Comment =
        addCommentUseCase(addCommentRequestModel.taskId, addCommentRequestModel.text)

    @GetMapping("/comment")
    fun getComment(@RequestParam(name = "commentId") commentId: CommentId): Comment = getCommentOrThrowUseCase(commentId)

    @PostMapping("/update-comment")
    fun updateComment(@RequestBody updateCommentRequestModel: UpdateCommentRequestModel): Comment {
        updateCommentUseCase(updateCommentRequestModel.commentId, updateCommentRequestModel.text)
        return getCommentOrThrowUseCase(updateCommentRequestModel.commentId)
    }

    @GetMapping("/comments-all")
    fun getComments(@RequestParam(name = "taskId") taskId: TaskId): List<Comment> = getCommentsUseCase(taskId)

    @DeleteMapping("/delete-comment")
    fun deleteComment(@RequestParam(name = "commentId") commentId: CommentId): Unit = deleteCommentUseCase(commentId)
}
