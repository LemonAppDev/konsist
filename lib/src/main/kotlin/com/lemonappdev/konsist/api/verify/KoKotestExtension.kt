package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.core.verify.KoTestNameContext
import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.test.TestCase
import io.kotest.engine.test.TestResult
import kotlinx.coroutines.withContext

/**
 * Kotest extension that passes the name of the running test to Konsist assertions, so the `testName` argument
 * is not needed. The name is displayed in error messages and used for suppression. Requires Kotest 6.
 *
 * Register the extension in the project config (`override val extensions = listOf(KoKotestExtension)`), in a spec
 * (`extension(KoKotestExtension)`) or with `@ApplyExtension(KoKotestExtension::class)`.
 */
object KoKotestExtension : TestCaseExtension {
    override suspend fun intercept(
        testCase: TestCase,
        execute: suspend (TestCase) -> TestResult,
    ): TestResult = withContext(KoTestNameContext.asContextElement(testCase.name.name)) { execute(testCase) }
}
