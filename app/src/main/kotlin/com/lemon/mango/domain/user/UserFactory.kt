package com.lemon.mango.domain.user

import com.lemon.mango.domain.common.UUIDFactory
import com.lemon.mango.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class UserFactory(
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        name: String,
    ) = User(
        uuidFactory.createUserId(),
        name,
    )
}
