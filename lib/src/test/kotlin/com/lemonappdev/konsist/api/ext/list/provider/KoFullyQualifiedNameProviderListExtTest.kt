package com.lemonappdev.konsist.api.ext.list.provider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFullyQualifiedNameProviderListExtTest {
    @Test
    fun `withFullyQualifiedName(empty list) returns all declarations`() {
        // given
        val declaration1: KoFullyQualifiedNameProvider = mockk()
        val declaration2: KoFullyQualifiedNameProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFullyQualifiedName(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withFullyQualifiedName(empty set) returns all declarations`() {
        // given
        val declaration1: KoFullyQualifiedNameProvider = mockk()
        val declaration2: KoFullyQualifiedNameProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFullyQualifiedName(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutFullyQualifiedName(empty list) returns none declaration`() {
        // given
        val declaration1: KoFullyQualifiedNameProvider = mockk()
        val declaration2: KoFullyQualifiedNameProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFullyQualifiedName(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutFullyQualifiedName(empty set) returns none declaration`() {
        // given
        val declaration1: KoFullyQualifiedNameProvider = mockk()
        val declaration2: KoFullyQualifiedNameProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFullyQualifiedName(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

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
    fun `withFullyQualifiedName(String) returns declarations with one of given fullyQualifiedName`() {
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
    fun `withFullyQualifiedName(list of String) returns declarations with one of given fullyQualifiedName`() {
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
        val fullyQualifiedNames = listOf(fullyQualifiedName1, fullyQualifiedName2)

        // when
        val sut = declarations.withFullyQualifiedName(fullyQualifiedNames)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withFullyQualifiedName(set of String) returns declarations with one of given fullyQualifiedName`() {
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
        val fullyQualifiedNames = setOf(fullyQualifiedName1, fullyQualifiedName2)

        // when
        val sut = declarations.withFullyQualifiedName(fullyQualifiedNames)

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
    fun `withoutFullyQualifiedName(String) returns declaration without any of given fullyQualifiedNames`() {
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

    @Test
    fun `withoutFullyQualifiedName(list of String) returns declaration without any of given fullyQualifiedNames`() {
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
        val fullyQualifiedNames = listOf(fullyQualifiedName1, fullyQualifiedName2)

        // when
        val sut = declarations.withoutFullyQualifiedName(fullyQualifiedNames)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutFullyQualifiedName(set of String) returns declaration without any of given fullyQualifiedNames`() {
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
        val fullyQualifiedNames = setOf(fullyQualifiedName1, fullyQualifiedName2)

        // when
        val sut = declarations.withoutFullyQualifiedName(fullyQualifiedNames)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
