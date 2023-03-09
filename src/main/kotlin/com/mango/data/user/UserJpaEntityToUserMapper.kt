package com.mango.data.user

import com.mango.domain.user.model.User
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class UserJpaEntityToUserMapper {
    operator fun invoke(userJpaEntity: UserJpaEntity) =
        User(
            id = UserId(userJpaEntity.id),
            name = userJpaEntity.name,
        )
}
