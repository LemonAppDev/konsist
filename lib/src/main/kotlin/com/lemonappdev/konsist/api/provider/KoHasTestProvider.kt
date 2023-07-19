package com.lemonappdev.konsist.api.provider

interface KoHasTestProvider : KoProvider {
    /**
     * Whatever declaration has a Test.
     *
     * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the declaration has a test, `false` otherwise.
     */
    fun hasTest(testFileNameSuffix: String = "Test", moduleName: String? = null, sourceSetName: String? = null): Boolean
}
