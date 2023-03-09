package com.mango.persistence.entity.mapper

import com.mango.business.model.User
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.UserJpaEntity
import org.springframework.stereotype.Service

@Service
class UserJpaEntityToUserMapper {
    operator fun invoke(userJpaEntity: UserJpaEntity) =
        User(
            id = UserId(userJpaEntity.id),
            name = userJpaEntity.name,
        )
}
