package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceAndObjectProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceAndObjectProviderListExtTest {
    @Test
    fun `interfacesAndObjects() returns interfacesAndObjects from all declarations`() {
        // given
        val interfaceDeclaration: KoInterfaceDeclaration = mockk()
        val objectDeclaration1: KoObjectDeclaration = mockk()
        val objectDeclaration2: KoObjectDeclaration = mockk()
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects(includeNested = true) } returns listOf(interfaceDeclaration, objectDeclaration1)
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects(includeNested = true) } returns listOf(objectDeclaration2)
            }
        val declaration3: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects(includeNested = true) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfacesAndObjects(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(interfaceDeclaration, objectDeclaration1, objectDeclaration2)
    }

    @Test
    fun `withInterfacesAndObjects() returns declaration with any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfacesAndObjects()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceOrObjectNamed(empty list) returns declaration with any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceOrObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceOrObjectNamed(empty set) returns declaration with any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceOrObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesAndObjectsNamed(empty list) returns declaration with any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesAndObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesAndObjectsNamed(empty set) returns declaration with any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesAndObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterfacesAndObjects() returns declaration without any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfacesAndObjects()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceOrObjectNamed(empty list) returns declaration without any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceOrObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceOrObjectNamed(empty set) returns declaration without any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceOrObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesAndObjectsNamed(empty list) returns declaration without any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesAndObjectsNamed(empty set) returns declaration without any interface or object`() {
        // given
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesOrObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInterfaceOrObjectNamed(name) returns declaration with given interface or object`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceOrObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceOrObjectNamed(String) returns declaration with any of given interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceOrObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceOrObjectNamed(list of String) returns declaration with any of given interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceOrObjectNamed(set of String) returns declaration with any of given interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterfaceOrObjectNamed(name) returns declaration without given interface or object`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceOrObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceOrObjectNamed(String) returns declaration without any of given interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceOrObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceOrObjectNamed(list of String) returns declaration without any of given interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceOrObjectNamed(set of String) returns declaration without any of given interface or object`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutInterfaceOrObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllInterfacesAndObjectsNamed(name) returns declaration with given interfaces and objects`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesAndObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesAndObjectsNamed(String) returns declaration with all given interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesAndObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesAndObjectsNamed(list of String) returns declaration with all given interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesAndObjectsNamed(set of String) returns declaration with all given interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllInterfacesAndObjectsNamed(name) returns declaration without given interfaces and objects`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesAndObjectsNamed(String) returns declaration without all of given interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesAndObjectsNamed(list of String) returns declaration without all of given interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesAndObjectsNamed(set of String) returns declaration without all of given interfaces and objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfacesAndObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInterfaceOrObject{} returns declaration with interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObject(true, predicate) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObject(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceOrObject(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterfaceOrObject{} returns declaration without interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObject(true, predicate) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasInterfaceOrObject(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceOrObject(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllInterfacesAndObjects{} returns declaration with all interfaces and objects satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasAllInterfacesAndObjects(true, predicate) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasAllInterfacesAndObjects(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesAndObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllInterfacesAndObjects{} returns declaration with all interfaces and objects which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceAndObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { hasAllInterfacesAndObjects(true, predicate) } returns true
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { hasAllInterfacesAndObjects(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesAndObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInterfacesAndObjects{} returns declaration with interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoInterfaceAndObjectDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val interfaceDeclaration: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val objectDeclaration: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects() } returns listOf(interfaceDeclaration)
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects() } returns listOf(objectDeclaration)
            }
        val declaration3: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withInterfacesAndObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutInterfacesAndObjects{} returns declaration without interfaces and objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoInterfaceAndObjectDeclaration>) -> Boolean =
            { it.all { koDeclaration -> koDeclaration.hasNameEndingWith(suffix) } }
        val interfaceDeclaration: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val objectDeclaration: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects() } returns listOf(interfaceDeclaration)
            }
        val declaration2: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects() } returns listOf(objectDeclaration)
            }
        val declaration3: KoInterfaceAndObjectProvider =
            mockk {
                every { interfacesAndObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutInterfacesAndObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
