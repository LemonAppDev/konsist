package com.lemonappdev.konsist.declaration.kogetter

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withGetter
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterForKoSourceSetProviderTest {
    @Test
    fun `source set name is 'main' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .properties()
            .withGetter()
            .first()
            .getter

        // then
        assertSoftly(sut) {
            it?.sourceSetName shouldBeEqualTo MAIN
            it?.resideInSourceSet(MAIN) shouldBeEqualTo true
            it?.resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'integrationTest' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appIntegrationTestSourceSetProjectDirectory/sample/AppClassTest.kt".toOsSeparator())
            .properties()
            .withGetter()
            .first()
            .getter

        // then
        assertSoftly(sut) {
            it?.sourceSetName shouldBeEqualTo INTEGRATION_TEST
            it?.resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo true
            it?.resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'main' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator())
            .properties()
            .withGetter()
            .first()
            .getter

        // then
        assertSoftly(sut) {
            it?.sourceSetName shouldBeEqualTo MAIN
            it?.resideInSourceSet(MAIN) shouldBeEqualTo true
            it?.resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'test' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataTestSourceSetProjectDirectory/sample/LibClassTest.kt".toOsSeparator())
            .properties()
            .withGetter()
            .first()
            .getter

        // then
        assertSoftly(sut) {
            it?.sourceSetName shouldBeEqualTo TEST
            it?.resideInSourceSet(TEST) shouldBeEqualTo true
            it?.resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'main' in root module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator())
            .properties()
            .withGetter()
            .first()
            .getter

        // then
        assertSoftly(sut) {
            it?.sourceSetName shouldBeEqualTo MAIN
            it?.resideInSourceSet(MAIN) shouldBeEqualTo true
            it?.resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'main' in root module with double src package`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/src/RootSrcClass.kt".toOsSeparator())
            .properties()
            .withGetter()
            .first()
            .getter

        // then
        assertSoftly(sut) {
            it?.sourceSetName shouldBeEqualTo MAIN
            it?.resideInSourceSet(MAIN) shouldBeEqualTo true
            it?.resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    companion object {
        private const val MAIN = "main"
        private const val TEST = "test"
        private const val INTEGRATION_TEST = "integrationTest"
    }
}
