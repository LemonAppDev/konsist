package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceProviderListExtTest {
    @Test
    fun `interfaces() returns interfaces from all declarations`() {
        // given
        val interface1: KoInterfaceDeclarationCore = mockk()
        val interface2: KoInterfaceDeclarationCore = mockk()
        val interface3: KoInterfaceDeclarationCore = mockk()
        val declaration1: KoInterfaceProvider =
            mockk {
                every { interfaces(includeNested = true) } returns listOf(interface1, interface2)
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { interfaces(includeNested = true) } returns listOf(interface3)
            }
        val declaration3: KoInterfaceProvider =
            mockk {
                every { interfaces(includeNested = true) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfaces(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(interface1, interface2, interface3)
    }

    @Test
    fun `withInterfaces() returns declaration with any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceNamed(empty list) returns declaration with any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceNamed(empty set) returns declaration with any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesNamed(empty list) returns declaration with any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesNamed(empty set) returns declaration with any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterfaces() returns declaration without any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceNamed(empty list) returns declaration without any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceNamed(empty set) returns declaration without any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesNamed(empty list) returns declaration without any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesNamed(empty set) returns declaration without any interface`() {
        // given
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInterfaceNamed(name) returns declaration with given interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceNamed(String) returns declaration with any of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceNamed(list of String) returns declaration with any of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceNamed(set of String) returns declaration with any of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterfaceNamed(name) returns declaration without given interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceNamed(String) returns declaration without any of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceNamed(list of String) returns declaration without any of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceNamed(set of String) returns declaration without any of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfaceWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllInterfacesNamed(name) returns declaration with given interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesNamed(String) returns declaration with all given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesNamed(list of String) returns declaration with all given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllInterfacesNamed(set of String) returns declaration with all given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllInterfacesNamed(name) returns declaration without given interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesNamed(String) returns declaration without all of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesNamed(list of String) returns declaration without all of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllInterfacesNamed(set of String) returns declaration without all of given interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterfacesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInterface{} returns declaration with interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterface(true, predicate) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterface(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterface(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterface{} returns declaration without interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasInterface(true, predicate) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasInterface(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterface(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllInterfaces{} returns declaration with all interfaces satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasAllInterfaces(true, predicate) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasAllInterfaces(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllInterfaces(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllInterfaces{} returns declaration with all interfaces which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoInterfaceProvider =
            mockk {
                every { hasAllInterfaces(true, predicate) } returns true
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { hasAllInterfaces(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllInterfaces(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInterfaces{} returns declaration with interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoInterfaceDeclaration>) -> Boolean =
            { it.all { koInterface -> koInterface.hasNameEndingWith(suffix) } }
        val interface1: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val interface2: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoInterfaceProvider =
            mockk {
                every { interfaces() } returns listOf(interface1)
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { interfaces() } returns listOf(interface2)
            }
        val declaration3: KoInterfaceProvider =
            mockk {
                every { interfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withInterfaces(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutInterfaces{} returns declaration without interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoInterfaceDeclaration>) -> Boolean =
            { it.all { koInterface -> koInterface.hasNameEndingWith(suffix) } }
        val interface1: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val interface2: KoInterfaceDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoInterfaceProvider =
            mockk {
                every { interfaces() } returns listOf(interface1)
            }
        val declaration2: KoInterfaceProvider =
            mockk {
                every { interfaces() } returns listOf(interface2)
            }
        val declaration3: KoInterfaceProvider =
            mockk {
                every { interfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutInterfaces(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
