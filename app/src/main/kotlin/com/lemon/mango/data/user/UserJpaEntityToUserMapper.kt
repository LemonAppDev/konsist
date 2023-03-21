package com.lemon.mango.data.user

import com.lemon.mango.domain.user.model.User
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class UserJpaEntityToUserMapper {
    operator fun invoke(userJpaEntity: UserJpaEntity) =
        User(
            id = UserId(userJpaEntity.id),
            name = userJpaEntity.name,
        )
}
