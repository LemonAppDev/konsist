package com.mango.application.controller

import com.mango.application.config.ApiConfig
import com.mango.application.model.project.CreateProjectRequestModel
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.CreateProjectUseCase
import com.mango.domain.project.usecase.DeleteProjectUseCase
import com.mango.domain.project.usecase.GetAllProjectsUseCase
import com.mango.domain.project.usecase.GetProjectActivitiesUseCase
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiConfig.API_V1_URL + "/project")
class ProjectController(
    private val createProjectUseCase: CreateProjectUseCase,
    private val deleteProjectUseCase: DeleteProjectUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val getAllProjectsUseCase: GetAllProjectsUseCase,
    private val getProjectActivitiesUseCase: GetProjectActivitiesUseCase,
) {
    @PostMapping("/create")
    fun createProject(@RequestBody requestModel: CreateProjectRequestModel): Project =
        createProjectUseCase(
            requestModel.name,
            requestModel.color,
            requestModel.isFavourite,
        )

    @GetMapping("/get")
    fun getProject(@RequestParam projectId: ProjectId): Project = getProjectOrThrowUseCase(projectId)

    @GetMapping("/all")
    fun getProjects(): List<Project> = getAllProjectsUseCase()

    @DeleteMapping("/delete")
    fun deleteProject(@RequestParam projectId: ProjectId): Unit = deleteProjectUseCase(projectId)

    @GetMapping("/activities")
    fun getProjectActivities(@RequestParam(name = "projectId") projectId: ProjectId): List<ProjectActivity> =
        getProjectActivitiesUseCase(projectId)
}
