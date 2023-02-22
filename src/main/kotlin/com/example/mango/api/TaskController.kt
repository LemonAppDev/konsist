package com.example.mango.api

import com.example.mango.data.model.Task
import com.example.mango.data.repository.TaskRepository
import com.example.mango.data.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class TaskController(
    val taskRepository: TaskRepository,
    val userRepository: UserRepository,
) {
    @Suppress("detekt.LongParameterList")
    @GetMapping("/create-task")
    fun createTask(
        @RequestParam(value = "name", required = true) name: String,
        @RequestParam(value = "description") description: String? = null,
        @RequestParam(value = "duedata") dueDate: LocalDateTime? = null,
        @RequestParam(value = "targetdate") targetDate: LocalDateTime? = null,
        @RequestParam(value = "priority") priorityId: Int? = null,
        @RequestParam(value = "projectid") projectId: Int?,
        @RequestParam(value = "parenttaskid") parentTaskId: Int? = null,
        @RequestParam(value = "assigneeid") assigneeId: Int? = null,
        @RequestParam(value = "authToken", required = true) authToken: String,
    ) {
        val user = userRepository.getUser(authToken)

        taskRepository.createTask(
            user.id, name, description, dueDate, targetDate, priorityId, projectId, parentTaskId, assigneeId,
        )
    }

    @GetMapping("/delete-task")
    fun deleteTask(
        @RequestParam(value = "id", required = true) id: Int,
        @RequestParam(value = "authtoken", required = true) authToken: String,
    ) {
        val user = userRepository.getUser(authToken)

        taskRepository.deleteTask(user.id, id)
    }

    @GetMapping("/all-tasks")
    fun getAllTasks(
        @RequestParam(value = "authtoken", required = true) authToken: String,
    ): List<Task> {
        val user = userRepository.getUser(authToken)

        return taskRepository.getTasks(user.id)
    }

    @Suppress("detekt.LongParameterList")
    @GetMapping("/update-task")
    fun updateTask(
        @RequestParam(value = "id", required = true) taskId: Int,
        @RequestParam(value = "name") name: String? = null,
        @RequestParam(value = "description") description: String? = null,
        @RequestParam(value = "duedata") dueDate: LocalDateTime? = null,
        @RequestParam(value = "targetdate") targetDate: LocalDateTime? = null,
        @RequestParam(value = "priority") priorityId: Int? = null,
        @RequestParam(value = "projectid") projectId: Int? = null,
        @RequestParam(value = "parenttaskid") parentTaskId: Int? = null,
        @RequestParam(value = "assigneeid") assigneeId: Int? = null,
        @RequestParam(value = "completedate") completeDate: Boolean = false,
        @RequestParam(value = "authtoken", required = true) authToken: String,
    ) {
        val user = userRepository.getUser(authToken)

        taskRepository.updateTask(
            user.id,
            taskId,
            name,
            description,
            dueDate,
            targetDate,
            priorityId,
            projectId,
            parentTaskId,
            assigneeId,
            completeDate,
        )
    }

    @GetMapping("/complete-task")
    fun markAsComplete(
        @RequestParam(value = "id", required = true) id: Int,
        @RequestParam(value = "authtoken", required = true) authToken: String,
    ) {
        val user = userRepository.getUser(authToken)

        taskRepository.completeTask(user.id, id)
    }

    @GetMapping("/duplicate-task")
    fun copyTask(
        @RequestParam(value = "id", required = true) id: Int,
        @RequestParam(value = "authtoken", required = true) authToken: String,
    ): Task {
        val user = userRepository.getUser(authToken)

        return taskRepository.copyTask(user.id, id)
    }
}
