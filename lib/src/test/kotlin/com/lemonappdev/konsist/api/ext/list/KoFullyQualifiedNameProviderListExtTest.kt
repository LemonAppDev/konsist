package com.lemonappdev.konsist.api.ext.list
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFullyQualifiedNameProviderListExtTest {
    @Test
    fun `withFullyQualifiedName() returns declaration with given fullyQualifiedName`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val declaration1: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName1
            }
        val declaration2: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFullyQualifiedName(fullyQualifiedName1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withFullyQualifiedName() returns declarations with one of given fullyQualifiedName`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val declaration1: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName1
            }
        val declaration2: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName2
            }
        val declaration3: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withFullyQualifiedName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutFullyQualifiedName() returns declaration without given fullyQualifiedName`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val declaration1: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName1
            }
        val declaration2: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFullyQualifiedName(fullyQualifiedName1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutFullyQualifiedName() returns declaration without any of given fullyQualifiedNames`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val declaration1: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName1
            }
        val declaration2: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName2
            }
        val declaration3: KoFullyQualifiedNameProvider =
            mockk {
                every { fullyQualifiedName } returns fullyQualifiedName3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutFullyQualifiedName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
