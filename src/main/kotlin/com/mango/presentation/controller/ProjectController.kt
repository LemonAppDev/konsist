package com.mango.presentation.controller

import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.usecase.project.CreateProjectUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProjectController(
    private val createProjectUseCase: CreateProjectUseCase,
) {
    @PostMapping("/v1/project/create")
    fun createProject(@RequestBody createProjectRequestModel: CreateProjectRequestModel) =
        createProjectUseCase(createProjectRequestModel)
}
