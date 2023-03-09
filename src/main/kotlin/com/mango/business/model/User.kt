package com.mango.business.model

import com.mango.business.model.value.UserId

data class User(
    val id: UserId,
    val name: String,
)
