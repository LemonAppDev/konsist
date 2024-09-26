package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a declaration that provides information about whether it is a mutable type.
 */
interface KoIsMutableTypeProvider : KoBaseProvider {
    /**
     * Determines whether the declaration is a mutable type.
     *
     * A mutable type is considered any type whose name starts with "Mutable",
     * such as `MutableList`, `MutableStateFlow`, `MutableLiveData`, etc.
     */
    val isMutableType: Boolean
}
