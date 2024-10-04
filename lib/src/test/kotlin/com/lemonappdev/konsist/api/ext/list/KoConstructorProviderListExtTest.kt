package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorProviderListExtTest {
    @Test
    fun `constructors returns constructors from all declarations`() {
        // given
        val constructor1: KoConstructorDeclaration = mockk()
        val constructor2: KoConstructorDeclaration = mockk()
        val constructor3: KoConstructorDeclaration = mockk()
        val declaration1: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor1, constructor2)
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor3)
            }
        val declaration3: KoConstructorProvider =
            mockk {
                every { constructors } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.constructors

        // then
        sut shouldBeEqualTo listOf(constructor1, constructor2, constructor3)
    }

    @Test
    fun `withConstructors() returns declaration with constructor`() {
        // given
        val declaration1: KoConstructorProvider =
            mockk {
                every { hasConstructors() } returns true
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { hasConstructors() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstructors()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstructors() returns declaration without constructor`() {
        // given
        val declaration1: KoConstructorProvider =
            mockk {
                every { hasConstructors() } returns true
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { hasConstructors() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstructors()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withConstructor{} returns declaration with constructor which satisfy predicate`() {
        // given
        val predicate: (KoConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoConstructorProvider =
            mockk {
                every { hasConstructor(predicate) } returns true
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { hasConstructor(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstructor(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstructor{} returns declaration without constructor which satisfy predicate`() {
        // given
        val predicate: (KoConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoConstructorProvider =
            mockk {
                every { hasConstructor(predicate) } returns true
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { hasConstructor(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstructor(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllConstructors{} returns declaration with all constructors satisfy predicate`() {
        // given
        val predicate: (KoConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoConstructorProvider =
            mockk {
                every { hasAllConstructors(predicate) } returns true
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { hasAllConstructors(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllConstructors{} returns declaration with all constructors which not satisfy predicate`() {
        // given
        val predicate: (KoConstructorDeclaration) -> Boolean = { it.hasPrivateModifier }
        val declaration1: KoConstructorProvider =
            mockk {
                every { hasAllConstructors(predicate) } returns true
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { hasAllConstructors(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withConstructors{} returns declaration with constructors which satisfy predicate`() {
        // given
        val predicate: (List<KoConstructorDeclaration>) -> Boolean =
            { it.all { constructor -> constructor.hasPrivateModifier } }
        val constructor1: KoConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns true
            }
        val constructor2: KoConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns false
            }
        val declaration1: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor1)
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor2)
            }
        val declaration3: KoConstructorProvider =
            mockk {
                every { constructors } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutConstructors{} returns declaration without constructors which satisfy predicate`() {
        // given
        val predicate: (List<KoConstructorDeclaration>) -> Boolean =
            { it.all { constructor -> constructor.hasPrivateModifier } }
        val constructor1: KoConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns true
            }
        val constructor2: KoConstructorDeclaration =
            mockk {
                every { hasPrivateModifier } returns false
            }
        val declaration1: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor1)
            }
        val declaration2: KoConstructorProvider =
            mockk {
                every { constructors } returns listOf(constructor2)
            }
        val declaration3: KoConstructorProvider =
            mockk {
                every { constructors } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutConstructors(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
