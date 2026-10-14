package com.lemonappdev.konsist.declaration.koimportalias

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasForKoModuleProviderTest {
    private val app = "app"
    private val data = "data"
    private val root = "root"

    @Test
    fun `module name is 'app'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/fixture/AppClass.kt".toOsSeparator())
                .properties()
                .first { it.name == "appPropertyWithImportAliasType" }
                .type
                ?.sourceDeclaration
                ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo app
            it?.residesInModule(app) shouldBeEqualTo true
            it?.residesInModule(data) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'data'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$dataMainSourceSetProjectDirectory/fixture/LibClass.kt".toOsSeparator())
                .properties()
                .first { it.name == "libPropertyWithImportAliasType" }
                .type
                ?.sourceDeclaration
                ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo data
            it?.residesInModule(data) shouldBeEqualTo true
            it?.residesInModule(app) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'root'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$rootMainSourceSetProjectDirectory/fixture/RootClass.kt".toOsSeparator())
                .properties()
                .first { it.name == "rootPropertyWithImportAliasType" }
                .type
                ?.sourceDeclaration
                ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo root
            it?.residesInModule(root) shouldBeEqualTo true
            it?.residesInModule(app) shouldBeEqualTo false
        }
    }
}
