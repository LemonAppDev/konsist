package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoEnumConstDeclaration
import com.lemonappdev.konsist.api.provider.KoConstantProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstantProviderListExtTest {
    @Test
    fun `constants returns constants from all declarations`() {
        // given
        val constants1: KoEnumConstDeclaration = mockk()
        val constants2: KoEnumConstDeclaration = mockk()
        val constants3: KoEnumConstDeclaration = mockk()
        val declaration1: KoConstantProvider = mockk {
            every { constants } returns listOf(constants1, constants2)
        }
        val declaration2: KoConstantProvider = mockk {
            every { constants } returns listOf(constants3)
        }
        val declaration3: KoConstantProvider = mockk {
            every { constants } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.constants

        // then
        sut shouldBeEqualTo listOf(constants1, constants2, constants3)
    }

    @Test
    fun `withConstants() returns declaration with any constant`() {
        // given
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants() } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstants()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstants() returns declaration without any constant`() {
        // given
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants() } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstants()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllConstants(String) returns declaration with all of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants(constant1, constant2) } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants(constant1, constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllConstants(String) returns declaration without any of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants(constant1, constant2) } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants(constant1, constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeConstants(String) returns declaration with given constant`() {
        // given
        val constant = "SampleConstant"
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants(constant) } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants(constant) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeConstants(constant)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeConstants(String) returns declarations with at least one of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants(constant1) } returns true
            every { hasConstants(constant2) } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants(constant1) } returns false
            every { hasConstants(constant2) } returns true
        }
        val declaration3: KoConstantProvider = mockk {
            every { hasConstants(constant1) } returns false
            every { hasConstants(constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeConstants(String) returns declaration with given constant`() {
        // given
        val constant = "SampleConstant"
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants(constant) } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants(constant) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeConstants(constant)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeConstants(String) returns declarations with at least one of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoConstantProvider = mockk {
            every { hasConstants(constant1) } returns true
            every { hasConstants(constant2) } returns true
        }
        val declaration2: KoConstantProvider = mockk {
            every { hasConstants(constant1) } returns false
            every { hasConstants(constant2) } returns true
        }
        val declaration3: KoConstantProvider = mockk {
            every { hasConstants(constant1) } returns false
            every { hasConstants(constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
