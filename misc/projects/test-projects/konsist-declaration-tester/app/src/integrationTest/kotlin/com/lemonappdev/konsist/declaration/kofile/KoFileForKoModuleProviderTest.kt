package com.lemonappdev.konsist.declaration.kofile

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.featureMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.featurePaymentMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.featureProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForKoModuleProviderTest {
    private val app = "app"
    private val data = "data"
    private val root = "root"
    private val feature = "feature"
    private val featurePayment = "feature/payment"

    @Test
    fun `module name is 'app'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/fixture/AppClass.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo app
            residesInModule(app) shouldBeEqualTo true
            residesInModule(data) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'data'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$dataMainSourceSetProjectDirectory/fixture/LibClass.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo data
            residesInModule(data) shouldBeEqualTo true
            residesInModule(app) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'root'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$rootMainSourceSetProjectDirectory/fixture/RootClass.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo root
            residesInModule(root) shouldBeEqualTo true
            residesInModule(app) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name of nested module uses unix separator on all OSes`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$featurePaymentMainSourceSetProjectDirectory/fixture/FeaturePaymentClass.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo featurePayment
            residesInModule(featurePayment) shouldBeEqualTo true
            residesInModule("""feature\payment""") shouldBeEqualTo true
            residesInModule(":feature:payment") shouldBeEqualTo true
            residesInModule(feature) shouldBeEqualTo false
            residesInModule(":feature") shouldBeEqualTo false
            residesInModule(data) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name of parent module does not contain nested module`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$featureMainSourceSetProjectDirectory/fixture/FeatureClass.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo feature
            residesInModule(feature) shouldBeEqualTo true
            residesInModule(":feature") shouldBeEqualTo true
            residesInModule(featurePayment) shouldBeEqualTo false
            residesInModule(":feature:payment") shouldBeEqualTo false
        }
    }

    @Test
    fun `module name of file located outside src directory in module directory is module directory`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$featureProjectDirectory/FeatureScript.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo feature
            residesInModule(feature) shouldBeEqualTo true
            residesInModule(":feature") shouldBeEqualTo true
            residesInModule(featurePayment) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name of file located outside src directory in non-module directory is closest module directory`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$featureProjectDirectory/config/FeatureConfig.kt".toOsSeparator())
                .files
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo feature
            residesInModule(feature) shouldBeEqualTo true
            residesInModule(":feature") shouldBeEqualTo true
            residesInModule("feature/config") shouldBeEqualTo false
            residesInModule(featurePayment) shouldBeEqualTo false
        }
    }
}
