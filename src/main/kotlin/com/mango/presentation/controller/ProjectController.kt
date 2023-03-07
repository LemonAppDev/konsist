package com.mango.presentation.controller

import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.usecase.project.CreateProjectUseCase
import com.mango.business.usecase.project.GetProjectOrThrowUseCase
import com.mango.presentation.config.ApiConfig
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiConfig.API_V1_URL + "/project")
class ProjectController(
    private val createProjectUseCase: CreateProjectUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
) {
    @PostMapping("/create")
    fun createProject(@RequestBody createProjectRequestModel: CreateProjectRequestModel) =
        createProjectUseCase(createProjectRequestModel)

    @GetMapping("/get")
    fun getProject(projectId: ProjectId) = getProjectOrThrowUseCase(projectId)
}
