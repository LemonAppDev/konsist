package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsValProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsValProviderListExtTest {
    @Test
    fun `withVal() returns declaration with val keyword`() {
        // given
        val declaration1: KoIsValProvider =
            mockk {
                every { isVal } returns true
            }
        val declaration2: KoIsValProvider =
            mockk {
                every { isVal } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVal()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVal() returns declaration without val keyword`() {
        // given
        val declaration1: KoIsValProvider =
            mockk {
                every { isVal } returns true
            }
        val declaration2: KoIsValProvider =
            mockk {
                every { isVal } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVal()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
