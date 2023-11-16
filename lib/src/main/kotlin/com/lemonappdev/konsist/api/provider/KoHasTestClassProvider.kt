package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a test class.
 */
interface KoHasTestClassProvider : KoBaseProvider {
    /**
     * Determines whatever declaration has a Test class.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the declaration has a test class, `false` otherwise.
     */
    fun hasTestClass(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Boolean
}
