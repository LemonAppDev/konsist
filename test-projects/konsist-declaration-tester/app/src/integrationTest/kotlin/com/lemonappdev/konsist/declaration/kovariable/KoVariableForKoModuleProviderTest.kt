package com.lemonappdev.konsist.declaration.kovariable

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableForKoModuleProviderTest {
    private val app = "app"
    private val data = "data"
    private val root = "root"

    @ParameterizedTest
    @MethodSource("provideValuesForApp")
    fun `module name is 'app'`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo app
            resideInModule(app) shouldBeEqualTo true
            resideInModule(data) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForData")
    fun `module name is 'data'`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo data
            resideInModule(data) shouldBeEqualTo true
            resideInModule(app) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRoot")
    fun `module name is 'root'`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            moduleName shouldBeEqualTo root
            resideInModule(root) shouldBeEqualTo true
            resideInModule(app) shouldBeEqualTo false
        }
    }

    companion object {
        private val appPath = "$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator()
        private val dataPath = "$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator()
        private val rootPath = "$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator()

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForApp() = listOf(
            arguments(Konsist.scopeFromFile(appPath).functions()),
            arguments(Konsist.scopeFromFile(appPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(appPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(appPath).properties().getters),
            arguments(Konsist.scopeFromFile(appPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForData() = listOf(
            arguments(Konsist.scopeFromFile(dataPath).functions()),
            arguments(Konsist.scopeFromFile(dataPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(dataPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(dataPath).properties().getters),
            arguments(Konsist.scopeFromFile(dataPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForRoot() = listOf(
            arguments(Konsist.scopeFromFile(rootPath).functions()),
            arguments(Konsist.scopeFromFile(rootPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(rootPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(rootPath).properties().getters),
            arguments(Konsist.scopeFromFile(rootPath).properties().setters),
        )
    }
}
