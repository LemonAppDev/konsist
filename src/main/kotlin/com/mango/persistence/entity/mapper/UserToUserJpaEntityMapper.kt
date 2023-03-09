package com.mango.persistence.entity.mapper

import com.mango.business.model.User
import com.mango.persistence.entity.UserJpaEntity
import org.springframework.stereotype.Service

@Service
class UserToUserJpaEntityMapper {
    operator fun invoke(user: User) =
        UserJpaEntity(
            id = user.id.value,
            name = user.name,
        )
}
