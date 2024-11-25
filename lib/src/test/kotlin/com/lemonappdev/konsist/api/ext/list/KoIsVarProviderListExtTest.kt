package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsVarProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsVarProviderListExtTest {
    @Test
    fun `withVar() returns declaration with var keyword`() {
        // given
        val declaration1: KoIsVarProvider =
            mockk {
                every { isVar } returns true
            }
        val declaration2: KoIsVarProvider =
            mockk {
                every { isVar } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVar()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVar() returns declaration without var keyword`() {
        // given
        val declaration1: KoIsVarProvider =
            mockk {
                every { isVar } returns true
            }
        val declaration2: KoIsVarProvider =
            mockk {
                every { isVar } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVar()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
