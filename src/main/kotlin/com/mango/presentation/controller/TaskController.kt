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
import com.mango.business.usecase.comment.GetCommentOrThrowUseCase
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
import com.mango.presentation.config.ApiConfig
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
