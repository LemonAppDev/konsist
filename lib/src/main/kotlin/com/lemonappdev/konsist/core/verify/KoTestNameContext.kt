package com.lemonappdev.konsist.core.verify

import kotlinx.coroutines.ThreadContextElement
import kotlinx.coroutines.asContextElement

/**
 * Holds the name of the currently running test, set by test framework extensions
 * (`KoJUnitExtension`, `KoKotestExtension`). Used when the name can't be read from the call stack
 * (JUnit 5 dynamic tests, Kotest tests).
 */
internal object KoTestNameContext {
    private val testName = ThreadLocal<String?>()

    val current: String?
        get() = testName.get()

    fun <T> withTestName(
        name: String,
        block: () -> T,
    ): T {
        val previousName = testName.get()
        testName.set(name)

        try {
            return block()
        } finally {
            testName.set(previousName)
        }
    }

    /**
     * Coroutine version of [withTestName]. The name is restored on every thread the coroutine resumes on.
     */
    fun asContextElement(name: String): ThreadContextElement<String?> = testName.asContextElement(name)
}
