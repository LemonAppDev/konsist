package com.lemonappdev.konsist.kofile

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toCanonicalPaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForModuleName {
    private val app = "app"
    private val data = "data"
    private val root = "root"

    @Test
    fun `module name is 'app'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetDirectory/sample/AppClass.kt".toCanonicalPaths(), absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo app
            resideInModule(app) shouldBeEqualTo true
            resideInModule(data) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'data'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetDirectory/sample/LibClass.kt".toCanonicalPaths(), absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo data
            resideInModule(data) shouldBeEqualTo true
            resideInModule(app) shouldBeEqualTo false
        }
    }

    @Test
    fun `module name is 'root'`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetDirectory/sample/RootClass.kt".toCanonicalPaths(), absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo root
            resideInModule(root) shouldBeEqualTo true
            resideInModule(app) shouldBeEqualTo false
        }
    }
}
