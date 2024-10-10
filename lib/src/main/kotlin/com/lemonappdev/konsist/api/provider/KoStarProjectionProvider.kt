package com.lemonappdev.konsist.api.provider

interface KoStarProjectionProvider : KoBaseProvider {
    /**
     * Determines whether the source declaration is a star projection.
     * A star projection is a placeholder that represents any type in a generic context, allowing for flexible type constraints.
     */
    val isStarProjection: Boolean
}
