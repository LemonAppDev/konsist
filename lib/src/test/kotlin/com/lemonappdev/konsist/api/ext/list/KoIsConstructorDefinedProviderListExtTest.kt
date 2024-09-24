package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsConstructorDefinedProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsConstructorDefinedProviderListExtTest {
    @Test
    fun `withConstructorDefined() returns declaration defined within constructor`() {
        // given
        val declaration1: KoIsConstructorDefinedProvider =
            mockk {
                every { isConstructorDefined } returns true
            }
        val declaration2: KoIsConstructorDefinedProvider =
            mockk {
                every { isConstructorDefined } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstructorDefined()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstructorDefined() returns declaration defined outside constructor`() {
        // given
        val declaration1: KoIsConstructorDefinedProvider =
            mockk {
                every { isConstructorDefined } returns true
            }
        val declaration2: KoIsConstructorDefinedProvider =
            mockk {
                every { isConstructorDefined } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstructorDefined()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
