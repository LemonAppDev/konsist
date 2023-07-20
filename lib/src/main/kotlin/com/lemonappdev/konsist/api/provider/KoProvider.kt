package com.lemonappdev.konsist.api.provider

interface KoProvider {
    val errorText: String
        get() = this.toString()
}
