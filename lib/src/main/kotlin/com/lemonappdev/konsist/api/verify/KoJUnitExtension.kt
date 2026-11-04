package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.core.verify.KoTestNameContext
import org.junit.jupiter.api.extension.DynamicTestInvocationContext
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor

/**
 * JUnit extension that passes the name of the running dynamic test to Konsist assertions, so the `testName` argument
 * does not have to repeat the name passed to `dynamicTest`. The name is displayed in error messages and used for
 * suppression (the name of the `@TestFactory` method is also accepted).
 *
 * Register the extension with `@ExtendWith(KoJUnitExtension::class)` or enable JUnit extension auto-detection
 * (`junit.jupiter.extensions.autodetection.enabled=true`).
 */
class KoJUnitExtension : InvocationInterceptor {
    override fun interceptDynamicTest(
        invocation: InvocationInterceptor.Invocation<Void>,
        invocationContext: DynamicTestInvocationContext,
        extensionContext: ExtensionContext,
    ): Unit {
        KoTestNameContext.withTestName(extensionContext.displayName) { invocation.proceed() }
    }
}
