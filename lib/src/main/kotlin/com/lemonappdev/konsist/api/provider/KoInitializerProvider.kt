package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has been initialized.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsInitializedProvider"))
interface KoInitializerProvider : KoBaseProvider {
    /**
     * Determines whatever this declaration has been initialized. Declaration that has been initialized has a body.
     * e.g.
     * ```kotlin
     * val name: String = "John Doe" // true
     * val name: String by lazy { "John Doe" } // true
     * lateinit var name: String // false
     *
     * fun greet() = "Hello, World!" // true
     * fun greet() { println("Hello, World!") } // true
     * fun greet(): String // false
     *
     * val speed: Int
     *    get() = 100 // true
     *    set(value) { field = value } // true
     *
     * val speed: Int = 0
     *   private set // false
     *
     * val speed: Int = 0
     *   get // false
     * ```
     */
    val isInitialized: Boolean
}
