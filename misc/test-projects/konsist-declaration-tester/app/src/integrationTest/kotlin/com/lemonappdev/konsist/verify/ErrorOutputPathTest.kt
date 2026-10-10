package com.lemonappdev.konsist.verify

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.helper.util.PathProvider.featurePaymentMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * Error output hyperlinks should be clickable in IntelliJ console on all OSes ("file:///" prefix and "/" separators).
 */
class ErrorOutputPathTest {
    private val projectRootFileUrl = "file:///${projectRootDirectory.removePrefix("/")}"

    private val featurePaymentClassUrl =
        "$projectRootFileUrl/feature/payment/src/main/kotlin/com/lemonappdev/fixture/FeaturePaymentClass.kt"

    private val paymentPresentationClassUrl =
        "$projectRootFileUrl/feature/payment/src/main/kotlin/com/lemonappdev/fixture/payment/presentation/" +
            "PaymentPresentationClass.kt"

    @Test
    fun `file assert error output uses file url with unix separator`() {
        // given
        val sut = Konsist
            .scopeFromFile("$featurePaymentMainSourceSetProjectDirectory/fixture/FeaturePaymentClass.kt")
            .files

        // when
        val func = { sut.assertTrue { false } }

        // then
        val message = (func shouldThrow KoAssertionFailedException::class).exceptionMessage.orEmpty()
        message shouldContain "FeaturePaymentClass.kt $featurePaymentClassUrl"
        message shouldNotContain "\\"
    }

    @Test
    fun `declaration assert error output uses file url with unix separator`() {
        // given
        val sut = Konsist
            .scopeFromFile("$featurePaymentMainSourceSetProjectDirectory/fixture/FeaturePaymentClass.kt")
            .classes()

        // when
        val func = { sut.assertTrue { false } }

        // then
        val message = (func shouldThrow KoAssertionFailedException::class).exceptionMessage.orEmpty()
        message shouldContain "Class FeaturePaymentClass $featurePaymentClassUrl:3:1"
        message shouldNotContain "\\"
    }

    @Test
    fun `assert empty error output uses file url with unix separator`() {
        // given
        val sut = Konsist
            .scopeFromFile("$featurePaymentMainSourceSetProjectDirectory/fixture/FeaturePaymentClass.kt")
            .classes()

        // when
        val func = { sut.assertEmpty() }

        // then
        val message = (func shouldThrow KoAssertionFailedException::class).exceptionMessage.orEmpty()
        message shouldContain "Class FeaturePaymentClass $featurePaymentClassUrl:3:1"
        message shouldNotContain "\\"
    }

    @Test
    fun `architecture assert error output uses file url with unix separator`() {
        // given
        val scope = Konsist.scopeFromDirectory(featurePaymentMainSourceSetProjectDirectory)
        val domain = Layer("Domain", "com.lemonappdev.fixture.payment.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.fixture.payment.presentation..")

        // when
        val func = {
            scope.assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }
        }

        // then
        val message = (func shouldThrow KoAssertionFailedException::class).exceptionMessage.orEmpty()
        message shouldContain "File $paymentPresentationClassUrl\n"
        message shouldContain "Import com.lemonappdev.fixture.payment.domain.PaymentDomainClass " +
            "($paymentPresentationClassUrl:3:1)"
        message shouldNotContain "\\"
    }
}
