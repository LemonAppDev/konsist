package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorsProviderListExtTest {
    @Test
    fun `secondaryConstructors returns secondary constructors from all declarations`() {
        // given
        val secondaryConstructor1: KoSecondaryConstructorDeclaration = mockk()
        val secondaryConstructor2: KoSecondaryConstructorDeclaration = mockk()
        val secondaryConstructor3: KoSecondaryConstructorDeclaration = mockk()
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns listOf(secondaryConstructor1, secondaryConstructor2)
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns listOf(secondaryConstructor3)
            }
        val declaration3: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.secondaryConstructors

        // then
        sut shouldBeEqualTo listOf(secondaryConstructor1, secondaryConstructor2, secondaryConstructor3)
    }

    @Test
    fun `withSecondaryConstructors() returns declaration with secondary constructor`() {
        // given
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructors() } returns true
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructors() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSecondaryConstructors()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSecondaryConstructors() returns declaration without secondary constructor`() {
        // given
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructors() } returns true
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructors() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSecondaryConstructors()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSecondaryConstructor{} returns declaration with secondary constructor which satisfy predicate`() {
        // given
        val predicate: (KoSecondaryConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructor(predicate) } returns true
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructor(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSecondaryConstructor(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSecondaryConstructor{} returns declaration without secondary constructor which satisfy predicate`() {
        // given
        val predicate: (KoSecondaryConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructor(predicate) } returns true
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { hasSecondaryConstructor(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSecondaryConstructor(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllSecondaryConstructors{} returns declaration with all secondary constructors satisfy predicate`() {
        // given
        val predicate: (KoSecondaryConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { hasAllSecondaryConstructors(predicate) } returns true
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { hasAllSecondaryConstructors(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllSecondaryConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllSecondaryConstructors{} returns declaration with all secondary constructors which not satisfy predicate`() {
        // given
        val predicate: (KoSecondaryConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { hasAllSecondaryConstructors(predicate) } returns true
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { hasAllSecondaryConstructors(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllSecondaryConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSecondaryConstructors{} returns declaration with secondary constructors which satisfy predicate`() {
        // given
        val predicate: (List<KoSecondaryConstructorDeclaration>) -> Boolean =
            { it.all { secondaryConstructor -> secondaryConstructor.hasPrivateModifier } }
        val secondaryConstructor1: KoSecondaryConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns true
            }
        val secondaryConstructor2: KoSecondaryConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns false
            }
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns listOf(secondaryConstructor1)
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns listOf(secondaryConstructor2)
            }
        val declaration3: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSecondaryConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutSecondaryConstructors{} returns declaration without secondary constructors which satisfy predicate`() {
        // given
        val predicate: (List<KoSecondaryConstructorDeclaration>) -> Boolean =
            { it.all { secondaryConstructor -> secondaryConstructor.hasPrivateModifier } }
        val secondaryConstructor1: KoSecondaryConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns true
            }
        val secondaryConstructor2: KoSecondaryConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns false
            }
        val declaration1: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns listOf(secondaryConstructor1)
            }
        val declaration2: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns listOf(secondaryConstructor2)
            }
        val declaration3: KoSecondaryConstructorsProvider =
            mockk {
                every { secondaryConstructors } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSecondaryConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
