package com.mango.application.controller

import com.mango.application.config.ApiConfig
import com.mango.application.model.comment.AddCommentRequestModel
import com.mango.application.model.comment.UpdateCommentRequestModel
import com.mango.application.model.task.CreateTaskRequestModel
import com.mango.application.model.task.UpdateTaskRequestModel
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import com.mango.domain.comment.usecase.AddCommentUseCase
import com.mango.domain.comment.usecase.DeleteCommentUseCase
import com.mango.domain.comment.usecase.GetCommentOrThrowUseCase
import com.mango.domain.comment.usecase.GetCommentsUseCase
import com.mango.domain.comment.usecase.UpdateCommentUseCase
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
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
