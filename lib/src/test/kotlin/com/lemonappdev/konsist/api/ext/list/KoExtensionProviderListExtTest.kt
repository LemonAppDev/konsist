package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExtensionProviderListExtTest {
    @Test
    fun `withExtension() returns declaration which is extension`() {
        // given
        val declaration1: KoExtensionProvider = mockk {
            every { isExtension() } returns true
        }
        val declaration2: KoExtensionProvider = mockk {
            every { isExtension() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExtension() returns declaration which is not extension`() {
        // given
        val declaration1: KoExtensionProvider = mockk {
            every { isExtension() } returns true
        }
        val declaration2: KoExtensionProvider = mockk {
            every { isExtension() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
