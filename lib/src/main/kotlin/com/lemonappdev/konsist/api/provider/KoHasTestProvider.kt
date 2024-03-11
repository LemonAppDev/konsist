package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a test.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("KoHasTestClassProvider"))
interface KoHasTestProvider : KoBaseProvider {
    /**
     * Whatever declaration has a Test.
     *
     * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the declaration has a test, `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasTestClass()"))
    fun hasTest(
        testFileNameSuffix: String = "Test",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Boolean
}
