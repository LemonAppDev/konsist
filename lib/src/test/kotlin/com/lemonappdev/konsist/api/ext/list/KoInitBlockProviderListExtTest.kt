package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoInitBlockProviderListExtTest {
    @Test
    fun `initBlocks returns init blocks from all declarations`() {
        // given
        val initBlock1: KoInitBlockDeclaration = mockk()
        val initBlock2: KoInitBlockDeclaration = mockk()
        val initBlock3: KoInitBlockDeclaration = mockk()
        val declaration1: KoInitBlockProvider = mockk {
            every { initBlocks } returns listOf(initBlock1, initBlock2)
        }
        val declaration2: KoInitBlockProvider = mockk {
            every { initBlocks } returns listOf(initBlock3)
        }
        val declaration3: KoInitBlockProvider = mockk {
            every { initBlocks } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.initBlocks

        // then
        sut shouldBeEqualTo listOf(initBlock1, initBlock2, initBlock3)
    }

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
        sut shouldBeEqualTo listOf(declaration1)
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
        sut shouldBeEqualTo listOf(declaration2)
    }
}
