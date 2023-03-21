package com.lemon.mango.application.controller

import com.lemon.mango.application.config.ApiConfig
import com.lemon.mango.domain.user.model.User
import com.lemon.mango.domain.user.usecase.CreateUserUseCase
import com.lemon.mango.domain.user.usecase.GetCurrentUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiConfig.API_V1_URL + "/user")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    @PostMapping("/create")
    fun createUser(@RequestParam(name = "userName") userName: String): User = createUserUseCase(userName)

    @GetMapping("/current")
    fun getCurrentUser(): User = getCurrentUserUseCase()
}
