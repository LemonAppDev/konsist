package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceAndObjectProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassAndInterfaceAndObjectProviderListExtTest {
    @Test
    fun `classesAndInterfacesAndObjects() returns classesAndInterfacesAndObjects from all declarations`() {
        // given
        val classDeclaration: KoClassDeclaration = mockk()
        val interfaceDeclaration: KoInterfaceDeclaration = mockk()
        val objectDeclaration: KoObjectDeclaration = mockk()
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every {
                    classesAndInterfacesAndObjects(
                        includeNested = true,
                        includeLocal = false,
                    )
                } returns listOf(classDeclaration, interfaceDeclaration)
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects(includeNested = true, includeLocal = false) } returns
                    listOf(
                        objectDeclaration,
                    )
            }
        val declaration3: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects(includeNested = true, includeLocal = false) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classesAndInterfacesAndObjects(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(classDeclaration, interfaceDeclaration, objectDeclaration)
    }

    @Test
    fun `withClassesAndInterfacesAndObjects() returns declaration with any class or interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassesAndInterfacesAndObjects()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceOrObjectNamed(empty list) returns declaration with any class or interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceOrObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceOrObjectNamed(empty set) returns declaration with any class or interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceOrObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjectsNamed(empty list) returns declaration with any class or interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjectsNamed(empty set) returns declaration with any class or interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassesAndInterfacesAndObjects() returns declaration without any class, interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassesAndInterfacesAndObjects()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceOrObjectNamed(empty list) returns declaration without any class, interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceOrObjectNamed(empty set) returns declaration without any class, interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjectsNamed(empty list) returns declaration without any class, interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjectsNamed(empty set) returns declaration without any class, interface or object`() {
        // given
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesOrInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassOrInterfaceOrObjectNamed(name) returns declaration with given class, interface or object`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceOrObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceOrObjectNamed(String) returns declaration with any of given class, interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceOrObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceOrObjectNamed(list of String) returns declaration with any of given class, interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withClassOrInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceOrObjectNamed(set of String) returns declaration with any of given class, interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withClassOrInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassOrInterfaceOrObjectNamed(name) returns declaration without given class, interface or object`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceOrObjectNamed(String) returns declaration without any of given class, interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceOrObjectNamed(list of String) returns declaration without any of given class, interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceOrObjectNamed(set of String) returns declaration without any of given class, interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjectsNamed(name) returns declaration with given classes, interfaces and objects`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjectsNamed(String) returns declaration with all given classes, interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjectsNamed(list of String) returns declaration with all given classes, interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjectsNamed(set of String) returns declaration with all given classes, interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjectsNamed(name) returns declaration without given classes, interfaces and objects`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjectsNamed(String) returns declaration without all of given classes, interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjectsNamed(list) returns declaration without all of given classes, interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjectsNamed(set) returns declaration without all of given classes, interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassesAndInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassOrInterfaceOrObject{} returns declaration with classes, interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObject(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObject(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceOrObject(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassOrInterfaceOrObject{} returns declaration without classes, interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObject(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasClassOrInterfaceOrObject(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceOrObject(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesAndInterfacesAndObjects{} returns declaration with all classes, interfaces and objects satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasAllClassesAndInterfacesAndObjects(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasAllClassesAndInterfacesAndObjects(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesAndInterfacesAndObjects{} returns decls with all class, interface and object which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasAllClassesAndInterfacesAndObjects(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { hasAllClassesAndInterfacesAndObjects(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassesAndInterfacesAndObjects{} returns declaration with classes, interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassAndInterfaceAndObjectDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val classDeclaration: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val interfaceDeclaration: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val objectDeclaration: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects() } returns listOf(classDeclaration, interfaceDeclaration)
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects() } returns listOf(interfaceDeclaration, objectDeclaration)
            }
        val declaration3: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withClassesAndInterfacesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutClassesAndInterfacesAndObjects{} returns declaration without classes, interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassAndInterfaceAndObjectDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val classDeclaration: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val interfaceDeclaration: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val objectDeclaration: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects() } returns listOf(classDeclaration, interfaceDeclaration)
            }
        val declaration2: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects() } returns listOf(interfaceDeclaration, objectDeclaration)
            }
        val declaration3: KoClassAndInterfaceAndObjectProvider =
            mockk {
                every { classesAndInterfacesAndObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutClassesAndInterfacesAndObjects(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
