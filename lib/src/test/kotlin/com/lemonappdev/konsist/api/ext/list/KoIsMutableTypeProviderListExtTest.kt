package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsMutableTypeProviderListExtTest {
    @Test
    fun `withMutableType() returns type with mutable type`() {
        // given
        val type1: KoIsMutableTypeProvider =
            mockk {
                every { isMutableType } returns true
            }
        val type2: KoIsMutableTypeProvider =
            mockk {
                every { isMutableType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withMutableType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutMutableType() returns type without mutable type`() {
        // given
        val type1: KoIsMutableTypeProvider =
            mockk {
                every { isMutableType } returns true
            }
        val type2: KoIsMutableTypeProvider =
            mockk {
                every { isMutableType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutMutableType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withMutableTypeArgument() returns type with mutable type argument`() {
        // given
        val type1: KoIsMutableTypeProvider =
            mockk {
                every { hasMutableTypeArgument } returns true
            }
        val type2: KoIsMutableTypeProvider =
            mockk {
                every { hasMutableTypeArgument } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withMutableTypeArgument()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutMutableTypeArgument() returns type without mutable type argument`() {
        // given
        val type1: KoIsMutableTypeProvider =
            mockk {
                every { hasMutableTypeArgument } returns true
            }
        val type2: KoIsMutableTypeProvider =
            mockk {
                every { hasMutableTypeArgument } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutMutableTypeArgument()

        // then
        sut shouldBeEqualTo listOf(type2)
    }
}
