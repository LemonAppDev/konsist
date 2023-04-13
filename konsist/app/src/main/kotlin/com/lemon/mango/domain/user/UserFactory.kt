package com.lemon.mango.domain.user

import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class UserFactory(
    private val uuidFactory: UuidFactory,
) {
    operator fun invoke(
        name: String,
    ) = User(
        uuidFactory.createUserId(),
        name,
    )
}
