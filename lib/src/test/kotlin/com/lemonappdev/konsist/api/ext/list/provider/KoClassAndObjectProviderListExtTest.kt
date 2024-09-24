package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndObjectProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassAndObjectProviderListExtTest {
    @Test
    fun `classesAndObjects() returns classesAndObjects from all declarations`() {
        // given
        val classDeclaration: KoClassDeclaration = mockk()
        val objectDeclaration1: KoObjectDeclaration = mockk()
        val objectDeclaration2: KoObjectDeclaration = mockk()
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects(includeNested = true, includeLocal = false) } returns listOf(classDeclaration, objectDeclaration1)
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects(includeNested = true, includeLocal = false) } returns listOf(objectDeclaration2)
            }
        val declaration3: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects(includeNested = true, includeLocal = false) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classesAndObjects(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(classDeclaration, objectDeclaration1, objectDeclaration2)
    }

    @Test
    fun `withClassesAndObjects() returns declaration with any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassesAndObjects()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrObjectNamed(empty list) returns declaration with any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrObjectNamed(empty set) returns declaration with any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndObjectsNamed(empty list) returns declaration with any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndObjectsNamed(empty set) returns declaration with any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassesAndObjects() returns declaration without any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassesAndObjects()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrObjectNamed(empty list) returns declaration without any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrObjectNamed(empty set) returns declaration without any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndObjectsNamed(empty list) returns declaration without any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndObjectsNamed(empty set) returns declaration without any class or object`() {
        // given
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassOrObjectNamed(name) returns declaration with given class or object`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrObjectNamed(String) returns declaration with any of given class or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrObjectNamed(list of String) returns declaration with any of given class or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withClassOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrObjectNamed(set of String) returns declaration with any of given class or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withClassOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassOrObjectNamed(name) returns declaration without given class or object`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrObjectNamed(String) returns declaration without any of given class or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrObjectNamed(list of String) returns declaration without any of given class or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutClassOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrObjectNamed(set of String) returns declaration without any of given class or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutClassOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesAndObjectsNamed(name) returns declaration with given classes and objects`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndObjectsNamed(String) returns declaration with all given classes and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndObjectsNamed(list of String) returns declaration with all given classes and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllClassesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndObjectsNamed(set of String) returns declaration with all given classes and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllClassesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesAndObjectsNamed(name) returns declaration without given classes and objects`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndObjectsNamed(String) returns declaration without all of given classes and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndObjectsNamed(list of String) returns declaration without all of given classes and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndObjectsNamed(set of String) returns declaration without all of given classes and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassesAndObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassOrObject{} returns declaration with classes and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObject(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObject(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrObject(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassOrObject{} returns declaration without classes and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObject(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasClassOrObject(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrObject(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesAndObjects{} returns declaration with all classes and objects satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasAllClassesAndObjects(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasAllClassesAndObjects(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesAndObjects{} returns declaration with all classes and objects which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { hasAllClassesAndObjects(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { hasAllClassesAndObjects(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassesAndObjects{} returns declaration with classes and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassAndObjectDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val classDeclaration: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val objectDeclaration: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects() } returns listOf(classDeclaration)
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects() } returns listOf(objectDeclaration)
            }
        val declaration3: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withClassesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutClassesAndObjects{} returns declaration without classes and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassAndObjectDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val classDeclaration: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val objectDeclaration: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects() } returns listOf(classDeclaration)
            }
        val declaration2: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects() } returns listOf(objectDeclaration)
            }
        val declaration3: KoClassAndObjectProvider =
            mockk {
                every { classesAndObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutClassesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
