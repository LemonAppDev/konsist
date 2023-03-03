package com.mango.presentation.controller

import com.mango.business.usecase.user.GetCurrentUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    @GetMapping("/v1/user/get")
    fun getCurrentUser() = getCurrentUserUseCase()
}
