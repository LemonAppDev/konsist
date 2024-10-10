package com.lemonappdev.konsist.api.provider

interface KoTypeProjectionProvider : KoBaseProvider {
    val isStarProjection: Boolean

    val hasInModifier: Boolean

    val hasOutModifier: Boolean
}
