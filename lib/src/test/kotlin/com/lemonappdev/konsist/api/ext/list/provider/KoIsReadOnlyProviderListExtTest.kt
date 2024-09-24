package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoIsReadOnlyProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsReadOnlyProviderListExtTest {
    @Test
    fun `withReadOnly() returns declaration defined within constructor`() {
        // given
        val declaration1: KoIsReadOnlyProvider =
            mockk {
                every { isReadOnly } returns true
            }
        val declaration2: KoIsReadOnlyProvider =
            mockk {
                every { isReadOnly } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReadOnly()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReadOnly() returns declaration defined outside constructor`() {
        // given
        val declaration1: KoIsReadOnlyProvider =
            mockk {
                every { isReadOnly } returns true
            }
        val declaration2: KoIsReadOnlyProvider =
            mockk {
                every { isReadOnly } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReadOnly()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
