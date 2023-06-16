package com.lemonappdev.konsist.koclass

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForHasTestTest {
    @Test
    fun `class-with-test-with-declared-test-file-name-suffix`() {
        // given
        val sut = Konsist
            .scopeFromProduction("data")
            .classes()
            .first()

        println("Class ${sut.name}")

        // then
        sut.hasTest("Spec") shouldBeEqualTo true
    }
}
