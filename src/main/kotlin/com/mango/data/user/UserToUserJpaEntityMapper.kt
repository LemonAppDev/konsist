package com.mango.data.user

import com.mango.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class UserToUserJpaEntityMapper {
    operator fun invoke(user: User) =
        UserJpaEntity(
            id = user.id.value,
            name = user.name,
        )
}
