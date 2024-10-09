package com.lemonappdev.konsist.api.provider

interface KoTypeProjectionProvider : KoBaseProvider {
    val isStarProjection: Boolean

//    val isContravariantProjection: Boolean

    val hasInProjection: Boolean

//    val isCovariantProjection: Boolean
    val hasOutProjection: Boolean
}
