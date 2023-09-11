package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantProviderListExtTest {
    @Test
    fun `enumConstants returns constants from all declarations`() {
        // given
        val constants1: KoEnumConstantDeclaration = mockk()
        val constants2: KoEnumConstantDeclaration = mockk()
        val constants3: KoEnumConstantDeclaration = mockk()
        val declaration1: KoEnumConstantProvider = mockk {
            every { enumConstants } returns listOf(constants1, constants2)
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { enumConstants } returns listOf(constants3)
        }
        val declaration3: KoEnumConstantProvider = mockk {
            every { enumConstants } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.enumConstants

        // then
        sut shouldBeEqualTo listOf(constants1, constants2, constants3)
    }

    @Test
    fun `withEnumConstants() returns declaration with any constant`() {
        // given
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants() } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstants()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutEnumConstants() returns declaration without any constant`() {
        // given
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants() } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstants()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllEnumConstants{} returns declaration with all enum constants satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasAllEnumConstants(predicate) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasAllEnumConstants(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstants(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withEnumConstant{} returns declaration with enum constant which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstant(predicate) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstant(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumConstant(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllEnumConstants{} returns declaration with all enum constants which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasAllEnumConstants(predicate) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasAllEnumConstants(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstants(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutEnumConstant{} returns declaration without enum constant which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoEnumConstantDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstant(predicate) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstant(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumConstant(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllEnumConstants(String) returns declaration with all of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1, constant2) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1, constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllEnumConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllEnumConstants(String) returns declaration without any of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1, constant2) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1, constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllEnumConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeEnumConstants(String) returns declaration with given constant`() {
        // given
        val constant = "SampleConstant"
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeEnumConstants(constant)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeEnumConstants(String) returns declarations with at least one of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1) } returns true
            every { hasEnumConstants(constant2) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1) } returns false
            every { hasEnumConstants(constant2) } returns true
        }
        val declaration3: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1) } returns false
            every { hasEnumConstants(constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeEnumConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeEnumConstants(String) returns declaration with given constant`() {
        // given
        val constant = "SampleConstant"
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeEnumConstants(constant)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeEnumConstants(String) returns declarations with at least one of given constants`() {
        // given
        val constant1 = "SampleConstant1"
        val constant2 = "SampleConstant2"
        val declaration1: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1) } returns true
            every { hasEnumConstants(constant2) } returns true
        }
        val declaration2: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1) } returns false
            every { hasEnumConstants(constant2) } returns true
        }
        val declaration3: KoEnumConstantProvider = mockk {
            every { hasEnumConstants(constant1) } returns false
            every { hasEnumConstants(constant2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeEnumConstants(constant1, constant2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
