package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import com.lemonappdev.konsist.testdata.SampleInterface
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
    fun `parentClasses() returns parent classes from all declarations`() {
        // given
        val parentClass1: KoClassDeclaration = mockk()
        val parentClass2: KoClassDeclaration = mockk()
        val parentClass3: KoClassDeclaration = mockk()
        val declaration1: KoParentClassProvider = mockk {
            every { parentClasses(indirectParents = true) } returns listOf(parentClass1, parentClass2)
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClasses(indirectParents = true) } returns listOf(parentClass3)
        }
        val declaration3: KoParentClassProvider = mockk {
            every { parentClasses(indirectParents = true) } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parentClasses(indirectParents = true)

        // then
        sut shouldBeEqualTo listOf(parentClass1, parentClass2, parentClass3)
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
    fun `withParentClasses() returns declaration with any parent class`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClasses(indirectParents = true) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClasses(indirectParents = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClasses(indirectParents = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClasses() returns declaration without any parent class`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClasses(indirectParents = true) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClasses(indirectParents = true) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClasses(indirectParents = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentClass{} returns declaration with parent class which satisfy predicate`() {
        // given
        val prefix = "sample"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameStartingWith(prefix) }
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(indirectParents = true, predicate) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(indirectParents = true, predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClass(indirectParents = true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClass{} returns declaration without parent class which satisfy predicate`() {
        // given
        val prefix = "sample"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameStartingWith(prefix) }
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(indirectParents = true, predicate) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(indirectParents = true, predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClass(indirectParents = true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentClasses{} returns declaration with all parent classes satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentClassProvider = mockk {
            every { hasAllParentClasses(predicate = predicate) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasAllParentClasses(predicate = predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentClasses(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentClasses{} returns declaration with all parent classes which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentClassProvider = mockk {
            every { hasAllParentClasses(predicate = predicate) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasAllParentClasses(predicate = predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentClasses(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentClasses{} returns declaration with parent classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoClassDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val parent2: KoClassDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val declaration1: KoParentClassProvider = mockk {
            every { parentClasses() } returns listOf(parent1)
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClasses() } returns listOf(parent2)
        }
        val declaration3: KoParentClassProvider = mockk {
            every { parentClasses() } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParentClasses(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutParentClasses{} returns declaration without parent classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoClassDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val parent2: KoClassDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val declaration1: KoParentClassProvider = mockk {
            every { parentClasses() } returns listOf(parent1)
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClasses() } returns listOf(parent2)
        }
        val declaration3: KoParentClassProvider = mockk {
            every { parentClasses() } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentClasses(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
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
    fun `withAllParentClassesNamed(name) returns declaration with given parent class`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentClassesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentClassesNamed(String) returns declaration with all given parent classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name1, name2) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentClassesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentClassesNamed(name) returns declaration without given parent class`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentClassesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentClassesNamed(String) returns declaration without all of given parent classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name1, name2) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClassesWithAllNames(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentClassesNamed(name1, name2)

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

    @Test
    fun `withAllParentClassesOf(KClass) returns declaration with all of given parent classes`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasAllParentClassesOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasAllParentClassesOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentClassesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentClassesOf(KClass) returns declaration without any of given parent classes`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasAllParentClassesOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasAllParentClassesOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentClassesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
