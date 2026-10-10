package com.lemonappdev.konsist.container

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.featurePaymentMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Scope output paths should use OS separators.
 */
class KoScopePathTest {
    private val featurePaymentClassPath =
        "$projectRootDirectory/feature/payment/src/main/kotlin/com/lemonappdev/fixture/FeaturePaymentClass.kt".toOsSeparator()

    private val sut = Konsist
        .scopeFromFile("$featurePaymentMainSourceSetProjectDirectory/fixture/FeaturePaymentClass.kt".toOsSeparator())

    @Test
    fun `toString uses os separator`() {
        // then
        sut.toString() shouldBeEqualTo featurePaymentClassPath
    }

    @Test
    fun `print uses os separator`() {
        // given
        val outputStream = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(outputStream))

        // when
        try {
            sut.print()
        } finally {
            System.setOut(originalOut)
        }

        // then
        outputStream.toString().trimEnd() shouldBeEqualTo featurePaymentClassPath
    }
}
