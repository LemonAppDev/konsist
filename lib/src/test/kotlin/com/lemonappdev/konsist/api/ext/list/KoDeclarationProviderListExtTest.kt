package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationProviderListExtTest {
    // We haven't tested all possibilities of includeNested = [true/false], includeLocal = [true/false], because
    // has been tested at KoDeclarationProvider.
    @Test
    fun `declarations() returns declarations from all declarations`() {
        // given
        val class1: KoClassDeclarationCore = mockk()
        val function1: KoFunctionDeclarationCore = mockk()
        val class2: KoClassDeclarationCore = mockk()
        val interface1: KoInterfaceDeclarationCore = mockk()
        val property1: KoPropertyDeclarationCore = mockk()
        val declaration1: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns listOf(class1, function1)
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns listOf(class2, interface1)
        }
        val declaration3: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns listOf(property1)
        }
        val declaration4: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = false) } returns emptyList()
        }
        val declarations = listOf(
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
        val sampleDeclaration: KoClassDeclaration = mockk()
        val declaration1: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = true) } returns listOf(sampleDeclaration)
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = true) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclarations(includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDeclarations() returns declaration without any declaration`() {
        // given
        val sampleDeclaration: KoClassDeclaration = mockk()
        val declaration1: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = true) } returns listOf(sampleDeclaration)
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { declarations(includeNested = true, includeLocal = true) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclarations(includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllDeclarations(name) returns declaration with given declaration`() {
        // given
        val name = "SampleName"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllDeclarations(name, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllDeclarations(name) returns declaration without given declaration`() {
        // given
        val name = "SampleName"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllDeclarations(name, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllDeclarations(String) returns declaration with all given declarations`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns true
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns true
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns false
        }
        val declaration3: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns false
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAllDeclarations(name1, name2, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllDeclarations(String) returns declarations without any of given declarations`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns true
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns true
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns false
        }
        val declaration3: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns false
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAllDeclarations(name1, name2, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withSomeDeclarations(String) returns declaration with given declaration`() {
        // given
        val name = "SampleName"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeDeclarations(name, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeDeclarations(String) returns declarations with at least one of given declarations`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns true
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns false
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns true
        }
        val declaration3: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns false
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeDeclarations(name1, name2, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeDeclarations(String) returns declaration without given declaration`() {
        // given
        val name = "SampleName"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeDeclarations(name, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeDeclarations(String) returns declarations without at least one of given declarations`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns true
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns true
        }
        val declaration2: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns false
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns true
        }
        val declaration3: KoDeclarationProvider = mockk {
            every { containsDeclaration(name1, includeNested = true, includeLocal = true) } returns false
            every { containsDeclaration(name2, includeNested = true, includeLocal = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeDeclarations(name1, name2, includeNested = true, includeLocal = true)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
