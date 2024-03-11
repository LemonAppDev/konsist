package com.lemonappdev.konsist.declaration.type.kofunctiontype

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.types
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTypeForKoModuleProviderTest {
    private val app = "app"
    private val data = "data"
    private val root = "root"

    @Test
    fun `module name is 'app'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "appPropertyWithFunctionType" }
            .type
            ?.asFunctionTypeDeclaration()

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
            .scopeFromFile("$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "libPropertyWithFunctionType" }
            .type
            ?.asFunctionTypeDeclaration()

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
            .scopeFromFile("$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator())
            .properties()
            .first { it.name == "rootPropertyWithFunctionType" }
            .type
            ?.asFunctionTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.moduleName shouldBeEqualTo root
            it?.resideInModule(root) shouldBeEqualTo true
            it?.resideInModule(app) shouldBeEqualTo false
        }
    }
}
