package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldStartWith
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.junit.jupiter.api.Test

class CompilerConfigurationExtensionStorageTest {
    @Test
    fun `kotlin compiler used at runtime is prior to 2_4`() {
        // then
        KotlinCompilerVersion.VERSION shouldStartWith "2.3."
    }

    @Test
    fun `konsist parses kotlin file`() {
        // given
        val sut = Konsist
            .scopeFromProduction()
            .classes()

        // then
        sut.map { it.name } shouldBeEqualTo listOf("FixtureClass")
    }
}
