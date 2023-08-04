package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasProviderListExtTest {
    @Test
    fun `withTypeAlias() returns declaration with typealias`() {
        // given
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeAliases()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeAlias() returns declaration without typealias`() {
        // given
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeAliases()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeAliases(String) returns declarations with all of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeAliases(String) returns declaration without any of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeTypeAliases(String) returns declaration with given typeAlias`() {
        // given
        val typeAlias = "SampleTypeAlias"
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeTypeAliases(typeAlias)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeTypeAliases(String) returns declarations with at least one of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns true
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val declaration3: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeTypeAliases(String) returns declaration without given typeAlias`() {
        // given
        val typeAlias = "SampleTypeAlias"
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeTypeAliases(typeAlias)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeTypeAliases(String) returns declarations without at least one of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val declaration1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns true
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val declaration2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val declaration3: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
