package com.lemonappdev.konsist.declaration.kointerface

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceForKoModuleProviderTest {
    private val app = "app"
    private val data = "data"
    private val root = "root"

    @Test
    fun `module name is 'app'`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/fixture/AppClass.kt".toOsSeparator())
                .interfaces()
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
                .interfaces()
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
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo root
            residesInModule(root) shouldBeEqualTo true
            residesInModule(app) shouldBeEqualTo false
        }
    }
}
