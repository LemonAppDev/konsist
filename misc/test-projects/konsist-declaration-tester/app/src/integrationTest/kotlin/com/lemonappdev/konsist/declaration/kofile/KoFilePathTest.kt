package com.lemonappdev.konsist.declaration.kofile

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.util.PathProvider.featurePaymentMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * Declaration paths should use "/" separators on all OSes.
 */
class KoFilePathTest {
    private val featurePaymentClassProjectPath =
        "/feature/payment/src/main/kotlin/com/lemonappdev/fixture/FeaturePaymentClass.kt"

    private val sut = Konsist
        .scopeFromFile("$featurePaymentMainSourceSetProjectDirectory/fixture/FeaturePaymentClass.kt")

    @Test
    fun `projectRootPath uses unix separator`() {
        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootDirectory
    }

    @Test
    fun `path uses unix separator`() {
        // then
        sut.files.first().path shouldBeEqualTo "$projectRootDirectory$featurePaymentClassProjectPath"
    }

    @Test
    fun `projectPath uses unix separator`() {
        // then
        sut.files.first().projectPath shouldBeEqualTo featurePaymentClassProjectPath
    }

    @Test
    fun `projectPath can be matched with unix separator`() {
        // then
        sut.files.first().projectPath.startsWith("/feature/payment/") shouldBeEqualTo true
    }

    @Test
    fun `location uses unix separator`() {
        // then
        sut.classes().first().location shouldBeEqualTo "$projectRootDirectory$featurePaymentClassProjectPath:3:1"
    }

    @Test
    fun `sourceSetName is resolved`() {
        // then
        sut.files.first().sourceSetName shouldBeEqualTo "main"
    }

    @Test
    fun `nameWithExtension is resolved`() {
        // then
        sut.files.first().nameWithExtension shouldBeEqualTo "FeaturePaymentClass.kt"
    }
}
