package com.example.mango.data.model

data class Project(
    val id: Int,
    var name: String,
    var color: Color = Color.GREY,
    var collaborators: List<User>? = null,
    var isFavourite: Boolean = false,
)
