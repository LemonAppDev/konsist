package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
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
        val declaration1: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns listOf(initBlock1, initBlock2)
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns listOf(initBlock3)
            }
        val declaration3: KoInitBlockProvider =
            mockk {
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
        val declaration1: KoInitBlockProvider =
            mockk {
                every { hasInitBlocks() } returns true
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { hasInitBlocks() } returns false
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
        val declaration1: KoInitBlockProvider =
            mockk {
                every { hasInitBlocks() } returns true
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { hasInitBlocks() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInitBlocks()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInitBlock{} returns declaration with init block which satisfy predicate`() {
        // given
        val predicate: (KoInitBlockDeclaration) -> Boolean = { it.localFunctions.isEmpty() }
        val declaration1: KoInitBlockProvider =
            mockk {
                every { hasInitBlock(predicate) } returns true
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { hasInitBlock(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInitBlock(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInitBlock{} returns declaration without init block which satisfy predicate`() {
        // given
        val predicate: (KoInitBlockDeclaration) -> Boolean = { it.localFunctions.isEmpty() }
        val declaration1: KoInitBlockProvider =
            mockk {
                every { hasInitBlock(predicate) } returns true
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { hasInitBlock(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInitBlock(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllInitBlocks{} returns declaration with all init blocks satisfy predicate`() {
        // given
        val predicate: (KoInitBlockDeclaration) -> Boolean = { it.localFunctions.isEmpty() }
        val declaration1: KoInitBlockProvider =
            mockk {
                every { hasAllInitBlocks(predicate) } returns true
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { hasAllInitBlocks(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInitBlocks(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllInitBlocks{} returns declaration with all init blocks which not satisfy predicate`() {
        // given
        val predicate: (KoInitBlockDeclaration) -> Boolean = { it.localFunctions.isEmpty() }
        val declaration1: KoInitBlockProvider =
            mockk {
                every { hasAllInitBlocks(predicate) } returns true
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { hasAllInitBlocks(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInitBlocks(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInitBlocks{} returns declaration with init blocks which satisfy predicate`() {
        // given
        val predicate: (List<KoInitBlockDeclaration>) -> Boolean =
            { it.all { initBlock -> initBlock.localFunctions.isEmpty() } }
        val initBlock1: KoInitBlockDeclaration =
            mockk {
                every { localFunctions.isEmpty() } returns true
            }
        val initBlock2: KoInitBlockDeclaration =
            mockk {
                every { localFunctions.isEmpty() } returns false
            }
        val declaration1: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns listOf(initBlock1)
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns listOf(initBlock2)
            }
        val declaration3: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withInitBlocks(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutInitBlocks{} returns declaration without init blocks which satisfy predicate`() {
        // given
        val predicate: (List<KoInitBlockDeclaration>) -> Boolean =
            { it.all { initBlock -> initBlock.localFunctions.isEmpty() } }
        val initBlock1: KoInitBlockDeclaration =
            mockk {
                every { localFunctions.isEmpty() } returns true
            }
        val initBlock2: KoInitBlockDeclaration =
            mockk {
                every { localFunctions.isEmpty() } returns false
            }
        val declaration1: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns listOf(initBlock1)
            }
        val declaration2: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns listOf(initBlock2)
            }
        val declaration3: KoInitBlockProvider =
            mockk {
                every { initBlocks } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutInitBlocks(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
