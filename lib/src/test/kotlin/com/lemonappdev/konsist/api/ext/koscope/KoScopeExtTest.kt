package com.lemonappdev.konsist.api.ext.koscope

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeExtTest {
    // We add these interfaces to simulate declarations that different providers implement.
    private interface TestDeclarationWithModifierProvider :
        KoBaseDeclaration,
        KoModifierProvider

    private interface TestDeclarationWithoutModifierProvider :
        KoBaseDeclaration,
        KoAnnotationProvider

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `calls declarations(includeNested, includeLocal)`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val declarations: List<KoBaseDeclaration> = emptyList()
        val scope: KoScope =
            mockk {
                every { declarations(includeNested = includeNested, includeLocal = includeLocal) } returns declarations
            }

        // when
        scope.declarationsOf<KoBaseDeclaration>(includeNested = includeNested, includeLocal = includeLocal)

        // then
        verify { scope.declarations(includeNested = includeNested, includeLocal = includeLocal) }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `returns KoClassDeclaration instances`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val classDeclaration: KoClassDeclaration = mockk()
        val interfaceDeclaration: KoInterfaceDeclaration = mockk()
        val declarations: List<KoBaseDeclaration> = listOf(classDeclaration, interfaceDeclaration)
        val scope: KoScope =
            mockk {
                every { declarations(includeNested = includeNested, includeLocal = includeLocal) } returns declarations
            }

        // when
        val sut = scope.declarationsOf<KoClassDeclaration>(includeNested = includeNested, includeLocal = includeLocal)

        // then
        sut shouldBeEqualTo listOf(classDeclaration)
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `returns declarations which implements KoModifierProvider`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val declaration1: TestDeclarationWithModifierProvider = mockk()
        val declaration2: TestDeclarationWithoutModifierProvider = mockk()
        val declarations = listOf(declaration1, declaration2)
        val scope: KoScope =
            mockk {
                every { declarations(includeNested = includeNested, includeLocal = includeLocal) } returns declarations
            }

        // when
        val sut = scope.declarationsOf<KoModifierProvider>(includeNested = includeNested, includeLocal = includeLocal)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(false, false),
                arguments(true, false),
                arguments(false, true),
                arguments(true, true),
            )
    }
}
