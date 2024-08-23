package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassAndInterfaceProviderListExtTest {
    @Test
    fun `classesAndInterfaces() returns classesAndInterfaces from all declarations`() {
        // given
        val classDeclaration: KoClassDeclaration = mockk()
        val interfaceDeclaration1: KoInterfaceDeclaration = mockk()
        val interfaceDeclaration2: KoInterfaceDeclaration = mockk()
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces(includeNested = true, includeLocal = false) } returns listOf(classDeclaration, interfaceDeclaration1)
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces(includeNested = true, includeLocal = false) } returns listOf(interfaceDeclaration2)
            }
        val declaration3: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces(includeNested = true, includeLocal = false) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classesAndInterfaces(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(classDeclaration, interfaceDeclaration1, interfaceDeclaration2)
    }

    @Test
    fun `withClassesAndInterfaces() returns declaration with any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassesAndInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceNamed(empty list) returns declaration with any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceNamed(empty set) returns declaration with any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesNamed(empty list) returns declaration with any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesNamed(empty set) returns declaration with any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassesAndInterfaces() returns declaration without any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassesAndInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceNamed(empty list) returns declaration without any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceNamed(empty set) returns declaration without any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesNamed(empty list) returns declaration without any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesNamed(empty set) returns declaration without any class or interface`() {
        // given
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesOrInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassOrInterfaceNamed(name) returns declaration with given class or interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceNamed(String) returns declaration with any of given class or interface`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceNamed(list of String) returns declaration with any of given class or interface`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withClassOrInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassOrInterfaceNamed(set of String) returns declaration with any of given class or interface`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withClassOrInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassOrInterfaceNamed(name) returns declaration without given class or interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceNamed(String) returns declaration without any of given class or interface`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceNamed(list of String) returns declaration without any of given class or interface`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutClassOrInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassOrInterfaceNamed(set of String) returns declaration without any of given class or interface`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterfaceWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutClassOrInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesAndInterfacesNamed(name) returns declaration with given classes and interfaces`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesNamed(String) returns declaration with all given classes and interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesNamed(list of String) returns declaration with all given classes and interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllClassesAndInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesAndInterfacesNamed(set of String) returns declaration with all given classes and interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllClassesAndInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesAndInterfacesNamed(name) returns declaration without given classes and interfaces`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesNamed(String) returns declaration without all of given classes and interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesNamed(list of String) returns declaration without all of given classes and interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesAndInterfacesNamed(set of String) returns declaration without all of given classes and interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassesAndInterfacesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesAndInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassOrInterface{} returns declaration with classes and interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterface(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterface(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassOrInterface(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassOrInterface{} returns declaration without classes and interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterface(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasClassOrInterface(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassOrInterface(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesAndInterfaces{} returns declaration with all classes and interfaces satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasAllClassesAndInterfaces(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasAllClassesAndInterfaces(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesAndInterfaces(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesAndInterfaces{} returns declaration with all classes and interfaces which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassAndInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { hasAllClassesAndInterfaces(true, true, predicate) } returns true
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { hasAllClassesAndInterfaces(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesAndInterfaces(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassesAndInterfaces{} returns declaration with classes and interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassAndInterfaceDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val classDeclaration: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val interfaceDeclaration: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces() } returns listOf(classDeclaration)
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces() } returns listOf(interfaceDeclaration)
            }
        val declaration3: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withClassesAndInterfaces(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutClassesAndInterfaces{} returns declaration without classes and interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassAndInterfaceDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val classDeclaration: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val interfaceDeclaration: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces() } returns listOf(classDeclaration)
            }
        val declaration2: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces() } returns listOf(interfaceDeclaration)
            }
        val declaration3: KoClassAndInterfaceProvider =
            mockk {
                every { classesAndInterfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutClassesAndInterfaces(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
