package com.lemonappdev.konsist.declaration.koimportalias

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasForKoSourceSetProviderTest {
    @Test
    fun `source set name is 'main' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "appPropertyWithImportAliasType" }
            .type
            ?.declaration as KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'integrationTest' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appIntegrationTestSourceSetProjectDirectory/sample/AppClassTest.kt".toOsSeparator())
            .properties()
            .first { it.name == "appPropertyWithImportAliasTypeTest" }
            .type
            ?.declaration as KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo INTEGRATION_TEST
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'main' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "libPropertyWithImportAliasType" }
            .type
            ?.declaration as KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'test' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataTestSourceSetProjectDirectory/sample/LibClassTest.kt".toOsSeparator())
            .properties()
            .first { it.name == "libPropertyWithImportAliasTypeTest" }
            .type
            ?.declaration as KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo TEST
            resideInSourceSet(TEST) shouldBeEqualTo true
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'main' in root module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "rootPropertyWithImportAliasType" }
            .type
            ?.declaration as KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @Test
    fun `source set name is 'main' in root module with double src package`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/src/RootSrcClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "rootSrcPropertyWithImportAliasType" }
            .type
            ?.declaration as KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    companion object {
        private const val MAIN = "main"
        private const val TEST = "test"
        private const val INTEGRATION_TEST = "integrationTest"
    }
}
