package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoInitBlockProviderListExtTest {
    @Test
    fun `withInitBlocks() returns declaration with init blocks`() {
        // given
        val declaration1: KoInitBlockProvider = mockk {
            every { hasInitBlocks } returns true
        }
        val declaration2: KoInitBlockProvider = mockk {
            every { hasInitBlocks } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInitBlocks()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInitBlocks() returns declaration without init blocks`() {
        // given
        val declaration1: KoInitBlockProvider = mockk {
            every { hasInitBlocks } returns true
        }
        val declaration2: KoInitBlockProvider = mockk {
            every { hasInitBlocks } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInitBlocks()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
