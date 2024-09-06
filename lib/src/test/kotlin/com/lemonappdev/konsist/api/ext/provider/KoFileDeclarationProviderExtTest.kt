package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFileDeclarationProviderExtTest {
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
        val provider: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = includeNested, includeLocal = includeLocal) } returns declarations
            }

        // when
        provider.declarationsOf<KoBaseDeclaration>(includeNested = includeNested, includeLocal = includeLocal)

        // then
        verify { provider.declarations(includeNested = includeNested, includeLocal = includeLocal) }
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
        val provider: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = includeNested, includeLocal = includeLocal) } returns declarations
            }

        // when
        val sut = provider.declarationsOf<KoClassDeclaration>(includeNested = includeNested, includeLocal = includeLocal)

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
        val provider: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = includeNested, includeLocal = includeLocal) } returns declarations
            }

        // when
        val sut = provider.declarationsOf<KoModifierProvider>(includeNested = includeNested, includeLocal = includeLocal)

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
