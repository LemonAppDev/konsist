package com.mango.business.factory

import com.mango.business.model.User
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
