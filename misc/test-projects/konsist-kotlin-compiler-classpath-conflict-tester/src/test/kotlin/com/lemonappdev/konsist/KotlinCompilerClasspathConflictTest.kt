package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class KotlinCompilerClasspathConflictTest {
    @Test
    fun `konsist throws exception describing kotlin compiler classpath conflict`() {
        // given
        val func = {
            Konsist
                .scopeFromProduction()
                .classes()
        }

        // then
        val message = (func shouldThrow KoInternalException::class).exception.message.orEmpty()

        message shouldContain "Failed to parse Kotlin file:"
        message shouldContain "Multiple Kotlin compilers found on the classpath:"
        message shouldContain "kotlin-compiler-2.4.21.jar"
        message shouldContain "kotlin-compiler-embeddable-2.4.21.jar"
        message shouldContain "https://docs.konsist.lemonappdev.com/help/known-issues"
    }
}
