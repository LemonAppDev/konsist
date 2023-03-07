package com.mango.presentation.controller

import com.mango.business.usecase.user.GetCurrentUserUseCase
import com.mango.presentation.config.ApiConfig
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiConfig.API_V1_URL + "/user")
class UserController(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    @GetMapping("/current")
    fun getCurrentUser() = getCurrentUserUseCase()
}
