package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationProviderListExtTest {
    @Test
    fun `declarations() returns declarations from all declarations`() {
        // given
        val class1: KoClassDeclarationCore = mockk()
        val function1: KoFunctionDeclarationCore = mockk()
        val class2: KoClassDeclarationCore = mockk()
        val interface1: KoInterfaceDeclarationCore = mockk()
        val property1: KoPropertyDeclarationCore = mockk()
        val declaration1: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = true, includeLocal = false) } returns listOf(class1, function1)
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = true, includeLocal = false) } returns listOf(class2, interface1)
            }
        val declaration3: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = true, includeLocal = false) } returns listOf(property1)
            }
        val declaration4: KoDeclarationProvider =
            mockk {
                every { declarations(includeNested = true, includeLocal = false) } returns emptyList()
            }
        val declarations =
            listOf(
                declaration1,
                declaration2,
                declaration3,
                declaration4,
            )

        // when
        val sut = declarations.declarations(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(class1, function1, class2, interface1, property1)
    }

    @Test
    fun `withDeclarations() returns declaration with any declaration`() {
        // given
        val declaration1: KoDeclarationProvider =
            mockk {
                every { hasDeclarations() } returns true
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { hasDeclarations() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclarations()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDeclarations() returns declaration without any declaration`() {
        // given
        val declaration1: KoDeclarationProvider =
            mockk {
                every { hasDeclarations() } returns true
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { hasDeclarations() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclarations()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withDeclaration{} returns declaration with declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoDeclarationProvider =
            mockk {
                every { hasDeclaration(true, true, predicate) } returns true
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { hasDeclaration(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclaration(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDeclaration{} returns declaration without declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoDeclarationProvider =
            mockk {
                every { hasDeclaration(true, true, predicate) } returns true
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { hasDeclaration(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclaration(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllDeclarations{} returns declaration with all declarations satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoDeclarationProvider =
            mockk {
                every { hasAllDeclarations(true, true, predicate) } returns true
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { hasAllDeclarations(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllDeclarations(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllDeclarations{} returns declaration with all declarations which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoBaseDeclaration) -> Boolean = { (it as KoNameProvider).hasNameEndingWith(suffix) }
        val declaration1: KoDeclarationProvider =
            mockk {
                every { hasAllDeclarations(true, true, predicate) } returns true
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { hasAllDeclarations(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllDeclarations(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withDeclarations{} returns declaration with declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoBaseDeclaration>) -> Boolean =
            { it.all { koDeclaration -> (koDeclaration as KoNameProvider).hasNameEndingWith(suffix) } }
        val class1: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val class2: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoDeclarationProvider =
            mockk {
                every { declarations() } returns listOf(class1)
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { declarations() } returns listOf(class2)
            }
        val declaration3: KoDeclarationProvider =
            mockk {
                every { declarations() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withDeclarations(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutDeclarations{} returns declaration without declarations which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoBaseDeclaration>) -> Boolean =
            { it.all { koDeclaration -> (koDeclaration as KoNameProvider).hasNameEndingWith(suffix) } }
        val class1: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val class2: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoDeclarationProvider =
            mockk {
                every { declarations() } returns listOf(class1)
            }
        val declaration2: KoDeclarationProvider =
            mockk {
                every { declarations() } returns listOf(class2)
            }
        val declaration3: KoDeclarationProvider =
            mockk {
                every { declarations() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutDeclarations(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
