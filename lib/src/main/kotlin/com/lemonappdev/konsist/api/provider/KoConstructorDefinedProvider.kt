package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is
 * defined within a constructor or not.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsConstructorDefinedProvider"))
interface KoConstructorDefinedProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is defined in constructor (true) or not (false).
     *
     * e.g.
     * ```
     * val topLevelProperty = "" // isConstructorDefined == false
     *
     * class SampleClass(val constructorProperty: Int) { // isConstructorDefined == true
     *      val bodyProperty = "" // isConstructorDefined == false
     * }
     * ```
     */
    val isConstructorDefined: Boolean
}
