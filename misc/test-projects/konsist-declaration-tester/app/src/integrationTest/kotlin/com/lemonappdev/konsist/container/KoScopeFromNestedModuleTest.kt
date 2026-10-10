package com.lemonappdev.konsist.container

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.featurePaymentMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * Nested module name should select module files with Gradle project path (":feature:payment"),
 * "/" and "\" separators ("feature/payment", "feature\payment") on all OSes.
 */
class KoScopeFromNestedModuleTest {
    private val featurePaymentClassPath = "$featurePaymentMainSourceSetDirectory/fixture/FeaturePaymentClass.kt".toOsSeparator()

    private val paymentDomainClassPath =
        "$featurePaymentMainSourceSetDirectory/fixture/payment/domain/PaymentDomainClass.kt".toOsSeparator()

    private val paymentPresentationClassPath =
        "$featurePaymentMainSourceSetDirectory/fixture/payment/presentation/PaymentPresentationClass.kt".toOsSeparator()

    private val featurePaymentFilePaths =
        listOf(
            featurePaymentClassPath,
            paymentDomainClassPath,
            paymentPresentationClassPath,
        )

    @Test
    fun `scopeFromModule for nested module with unix separator`() {
        // given
        val sut = Konsist.scopeFromModule("feature/payment")

        // then
        sut.mapToFilePaths() shouldBeEqualTo featurePaymentFilePaths
    }

    @Test
    fun `scopeFromModule for nested module with windows separator`() {
        // given
        val sut = Konsist.scopeFromModule("""feature\payment""")

        // then
        sut.mapToFilePaths() shouldBeEqualTo featurePaymentFilePaths
    }

    @Test
    fun `scopeFromModule for nested module with gradle project path`() {
        // given
        val sut = Konsist.scopeFromModule(":feature:payment")

        // then
        sut.mapToFilePaths() shouldBeEqualTo featurePaymentFilePaths
    }

    @Test
    fun `scopeFromModule for parent directory of nested module is empty`() {
        // given
        val sut = Konsist.scopeFromModule("feature")

        // then
        sut.mapToFilePaths() shouldBeEqualTo emptyList()
    }

    @Test
    fun `scopeFromProject for nested module with unix separator`() {
        // given
        val sut = Konsist.scopeFromProject(moduleName = "feature/payment")

        // then
        sut.mapToFilePaths() shouldBeEqualTo featurePaymentFilePaths
    }

    @Test
    fun `scopeFromProject for nested module with windows separator and main source set`() {
        // given
        val sut = Konsist.scopeFromProject(moduleName = """feature\payment""", sourceSetName = "main")

        // then
        sut.mapToFilePaths() shouldBeEqualTo featurePaymentFilePaths
    }

    @Test
    fun `scopeFromProject for nested module with gradle project path`() {
        // given
        val sut = Konsist.scopeFromProject(moduleName = ":feature:payment")

        // then
        sut.mapToFilePaths() shouldBeEqualTo featurePaymentFilePaths
    }

    @Test
    fun `scopeFromPackage for nested module with unix separator`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.fixture.payment..", moduleName = "feature/payment")

        // then
        sut.mapToFilePaths() shouldBeEqualTo listOf(paymentDomainClassPath, paymentPresentationClassPath)
    }

    @Test
    fun `scopeFromPackage for nested module with windows separator`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.fixture", moduleName = """feature\payment""")

        // then
        sut.mapToFilePaths() shouldBeEqualTo listOf(featurePaymentClassPath)
    }

    private fun KoScope.mapToFilePaths() = files.map { it.path }
}
