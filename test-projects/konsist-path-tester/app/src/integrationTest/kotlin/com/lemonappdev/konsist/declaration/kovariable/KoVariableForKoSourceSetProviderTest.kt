package com.lemonappdev.konsist.declaration.kovariable

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetProjectDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableForKoSourceSetProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForAppMain")
    fun `source set name is 'main' in app module`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForAppIntegrationTest")
    fun `source set name is 'integrationTest' in app module`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo INTEGRATION_TEST
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDataMain")
    fun `source set name is 'main' in data module`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDataTest")
    fun `source set name is 'test' in data module`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo TEST
            resideInSourceSet(TEST) shouldBeEqualTo true
            resideInSourceSet(INTEGRATION_TEST) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRootMain")
    fun `source set name is 'main' in root module`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            sourceSetName shouldBeEqualTo MAIN
            resideInSourceSet(MAIN) shouldBeEqualTo true
            resideInSourceSet(TEST) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRootMainWithDoubleSrcPackage")
    fun `source set name is 'main' in root module with double src package`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

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

        private val appMainPath = "$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator()
        private val appIntegrationTestPath = "$appIntegrationTestSourceSetProjectDirectory/sample/AppClassTest.kt".toOsSeparator()
        private val dataMainPath = "$dataMainSourceSetProjectDirectory/sample/LibClass.kt".toOsSeparator()
        private val dataTestPath = "$dataTestSourceSetProjectDirectory/sample/LibClassTest.kt".toOsSeparator()
        private val rootMainPath = "$rootMainSourceSetProjectDirectory/sample/RootClass.kt".toOsSeparator()
        private val rootMainPathWithDoubleSrcPackage = "$rootMainSourceSetProjectDirectory/sample/src/RootSrcClass.kt".toOsSeparator()

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAppMain() = listOf(
            arguments(Konsist.scopeFromFile(appMainPath).functions()),
            arguments(Konsist.scopeFromFile(appMainPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(appMainPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(appMainPath).properties().getters),
            arguments(Konsist.scopeFromFile(appMainPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAppIntegrationTest() = listOf(
            arguments(Konsist.scopeFromFile(appIntegrationTestPath).functions()),
            arguments(Konsist.scopeFromFile(appIntegrationTestPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(appIntegrationTestPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(appIntegrationTestPath).properties().getters),
            arguments(Konsist.scopeFromFile(appIntegrationTestPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDataMain() = listOf(
            arguments(Konsist.scopeFromFile(dataMainPath).functions()),
            arguments(Konsist.scopeFromFile(dataMainPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(dataMainPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(dataMainPath).properties().getters),
            arguments(Konsist.scopeFromFile(dataMainPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDataTest() = listOf(
            arguments(Konsist.scopeFromFile(dataTestPath).functions()),
            arguments(Konsist.scopeFromFile(dataTestPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(dataTestPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(dataTestPath).properties().getters),
            arguments(Konsist.scopeFromFile(dataTestPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForRootMain() = listOf(
            arguments(Konsist.scopeFromFile(rootMainPath).functions()),
            arguments(Konsist.scopeFromFile(rootMainPath).classes().initBlocks),
            arguments(Konsist.scopeFromFile(rootMainPath).classes().enumConstants),
            arguments(Konsist.scopeFromFile(rootMainPath).properties().getters),
            arguments(Konsist.scopeFromFile(rootMainPath).properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForRootMainWithDoubleSrcPackage() = listOf(
            arguments(Konsist.scopeFromFile(rootMainPathWithDoubleSrcPackage).functions()),
            arguments(Konsist.scopeFromFile(rootMainPathWithDoubleSrcPackage).classes().initBlocks),
            arguments(Konsist.scopeFromFile(rootMainPathWithDoubleSrcPackage).classes().enumConstants),
            arguments(Konsist.scopeFromFile(rootMainPathWithDoubleSrcPackage).properties().getters),
            arguments(Konsist.scopeFromFile(rootMainPathWithDoubleSrcPackage).properties().setters),
        )
    }
}
