package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a test class.
 */
interface KoHasTestClassProvider : KoBaseProvider {
    /**
     * Determines whatever declaration has a Test class.
     *
     * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the declaration has a test class, `false` otherwise.
     */
    fun hasTestClass(
        testFileNameSuffix: String = "Test",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Boolean
}
