package com.lemon.mango.data.user

import com.lemon.mango.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class UserToUserJpaEntityMapper {
    operator fun invoke(user: User) =
        UserJpaEntity(
            id = user.id.value,
            name = user.name,
        )
}
