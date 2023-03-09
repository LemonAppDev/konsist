package com.mango.domain.user

import com.mango.domain.common.UUIDFactory
import com.mango.domain.user.model.User
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
