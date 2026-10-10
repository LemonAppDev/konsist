package com.lemonappdev.konsist.core.provider

import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoModuleProviderCoreTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `moduleName uses slash separator`(
        projectPath: String,
        expected: String,
    ) {
        // given
        val sut = createModuleProvider(projectPath)

        // when
        val actual = sut.moduleName

        // then
        actual shouldBeEqualTo expected
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `resideInModule accepts module name with slash and backslash separator`(
        projectPath: String,
        expected: String,
    ) {
        // given
        val sut = createModuleProvider(projectPath)

        // then
        sut.resideInModule(expected) shouldBeEqualTo true
        sut.resideInModule(expected.replace("/", "\\")) shouldBeEqualTo true
        sut.resideInModule("other") shouldBeEqualTo false
    }

    private fun createModuleProvider(projectPath: String): KoModuleProviderCore =
        object : KoModuleProviderCore {
            override val psiElement: PsiElement = mockk()
            override val projectPath: String = projectPath
        }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("/src/main/kotlin/com/lemonappdev/RootClass.kt", "root"),
                arguments("\\src\\main\\kotlin\\com\\lemonappdev\\RootClass.kt", "root"),
                arguments("/app/src/main/kotlin/com/lemonappdev/AppClass.kt", "app"),
                arguments("\\app\\src\\main\\kotlin\\com\\lemonappdev\\AppClass.kt", "app"),
                arguments("/feature/data/src/main/kotlin/com/lemonappdev/DataClass.kt", "feature/data"),
                arguments("\\feature\\data\\src\\main\\kotlin\\com\\lemonappdev\\DataClass.kt", "feature/data"),
            )
    }
}
