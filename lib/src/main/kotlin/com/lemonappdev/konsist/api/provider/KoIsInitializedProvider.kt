package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has been initialized.
 */
interface KoIsInitializedProvider : KoBaseProvider {
    /**
     * Determines whether this declaration has been initialized. A declaration that has been initialized has a body.
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
     * var speed: Int
     *    get() = 100 // true
     *    set(value) { field = value } // true
     *
     * var speed: Int = 0
     *   private set // false
     *
     * val speed: Int = 0
     *   get // false
     * ```
     */
    val isInitialized: Boolean
}
