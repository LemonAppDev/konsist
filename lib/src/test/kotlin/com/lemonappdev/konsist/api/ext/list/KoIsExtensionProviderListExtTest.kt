package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsExtensionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoIsExtensionProviderListExtTest {
    @Test
    fun `withExtension() returns declaration defined within constructor`() {
        // given
        val declaration1: KoIsExtensionProvider =
            mockk {
                every { isExtension } returns true
            }

        val declaration2: KoIsExtensionProvider =
            mockk {
                every { isExtension } returns false
            }

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExtension()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExtension() returns declaration defined outside constructor`() {
        // given
        val declaration1: KoIsExtensionProvider =
            mockk {
                every { isExtension } returns true
            }

        val declaration2: KoIsExtensionProvider =
            mockk {
                every { isExtension } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExtension()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
