package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoBodyProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBodyProviderListExtTest {
    @Test
    fun `withExpressionBody() returns declaration with expression body`() {
        // given
        val declaration1: KoBodyProvider =
            mockk {
                every { hasExpressionBody } returns true
            }
        val declaration2: KoBodyProvider =
            mockk {
                every { hasExpressionBody } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExpressionBody()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExpressionBody() returns declaration without expression body`() {
        // given
        val declaration1: KoBodyProvider =
            mockk {
                every { hasExpressionBody } returns true
            }
        val declaration2: KoBodyProvider =
            mockk {
                every { hasExpressionBody } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExpressionBody()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withBlockBody() returns declaration with block body`() {
        // given
        val declaration1: KoBodyProvider =
            mockk {
                every { hasBlockBody } returns true
            }
        val declaration2: KoBodyProvider =
            mockk {
                every { hasBlockBody } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withBlockBody()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutBlockBody() returns declaration without block body`() {
        // given
        val declaration1: KoBodyProvider =
            mockk {
                every { hasBlockBody } returns true
            }
        val declaration2: KoBodyProvider =
            mockk {
                every { hasBlockBody } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutBlockBody()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
