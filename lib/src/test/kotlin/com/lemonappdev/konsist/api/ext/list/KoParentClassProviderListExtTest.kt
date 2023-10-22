package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentClassProviderListExtTest {
    @Test
    fun `parentClasses returns parent classes from all declarations`() {
        // given
        val parentClass1: KoClassDeclaration = mockk()
        val parentClass2: KoClassDeclaration = mockk()
        val declaration1: KoParentClassProvider = mockk {
            every { parentClass } returns parentClass1
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClass } returns parentClass2
        }
        val declaration3: KoParentClassProvider = mockk {
            every { parentClass } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parentClasses

        // then
        sut shouldBeEqualTo listOf(parentClass1, parentClass2)
    }

    @Test
    fun `withParentClass() returns declaration with any parent class`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass() } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClass()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClass() returns declaration without any parent class`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass() } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClass()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentClass{} returns declaration with parent class which satisfy predicate`() {
        // given
        val name1 = "SampleName"
        val name2 = "OtherName"
        val parentClass1: KoClassDeclaration = mockk {
            every { name } returns name1
        }
        val parentClass2: KoClassDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoParentClassProvider = mockk {
            every { parentClass } returns parentClass1
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClass } returns parentClass2
        }
        val declaration3: KoParentClassProvider = mockk {
            every { parentClass } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParentClass { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClass{} returns declaration without parent class which satisfy predicate`() {
        // given
        val name1 = "SampleName"
        val name2 = "OtherName"
        val parentClass1: KoClassDeclaration = mockk {
            every { name } returns name1
        }
        val parentClass2: KoClassDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoParentClassProvider = mockk {
            every { parentClass } returns parentClass1
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClass } returns parentClass2
        }
        val declaration3: KoParentClassProvider = mockk {
            every { parentClass } returns null
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentClass { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withParentClassNamed(name) returns declaration with given parent class`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClassNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentClassNamed(String) returns declaration with any of given parent classs`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name1, name2) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClassNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClassNamed(name) returns declaration without given parent class`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClassNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentClassNamed(String) returns declaration without any of given parent classs`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name1, name2) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassWithName(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClassNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentClass(name) returns declaration with given parent class name`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClass(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentClass(name) returns declarations with one of given parent class names`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val declaration3: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParentClass(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutParentClass(name) returns declaration without given parent class name`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClass(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentClass(name) returns declaration without any of given parent class names`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val declaration3: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentClass(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withParentClassOf(KClass) returns declaration with given parent class`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass1::class) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClassOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentClassOf(KClass) returns declarations with one of given parent classes`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass1::class, SampleClass2::class) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass1::class, SampleClass2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClassOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClassOf(KClass) returns declaration without given parent class`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass::class) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClassOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentClassOf(KClass) returns declaration without any of given parent classes`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass1::class, SampleClass2::class) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassOf(SampleClass1::class, SampleClass2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClassOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
