package com.lemonappdev.konsist.declaration.koimportalias

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
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
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/fixture/AppClass.kt")
            .properties()
            .first { it.name == "appPropertyWithImportAliasType" }
            .type
            ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo app
            it?.resideInModule(app) shouldBeEqualTo true
            it?.resideInModule(data) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'data'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetProjectDirectory/fixture/LibClass.kt")
            .properties()
            .first { it.name == "libPropertyWithImportAliasType" }
            .type
            ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo data
            it?.resideInModule(data) shouldBeEqualTo true
            it?.resideInModule(app) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'root'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetProjectDirectory/fixture/RootClass.kt")
            .properties()
            .first { it.name == "rootPropertyWithImportAliasType" }
            .type
            ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo root
            it?.resideInModule(root) shouldBeEqualTo true
            it?.resideInModule(app) shouldBeEqualTo false
        }
    }
}
