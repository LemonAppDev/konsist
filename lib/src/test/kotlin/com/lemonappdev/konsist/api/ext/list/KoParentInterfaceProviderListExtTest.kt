package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoParentInterfaceProviderListExtTest {
    @Test
    fun `parentInterfaces() returns parent interfaces from all declarations`() {
        // given
        val parent1: KoParentDeclaration = mockk()
        val parent2: KoParentDeclaration = mockk()
        val parent3: KoParentDeclaration = mockk()
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns listOf(parent3)
            }
        val declaration3: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parentInterfaces()

        // then
        sut shouldBeEqualTo listOf(parent1, parent2, parent3)
    }

    @Test
    fun `withParentInterfaces() returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceNamed(empty list) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceNamed(empty set) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesNamed(empty list) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesNamed(empty set) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaces() returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceNamed(empty list) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceNamed(empty set) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(empty list) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(empty set) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterfaceNamed(name) returns declaration with given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceNamed(String) returns declaration with any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceNamed(list of String) returns declaration with any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withParentInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceNamed(set of String) returns declaration with any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withParentInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaceNamed(name) returns declaration without given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceNamed(String) returns declaration without any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceNamed(list of String) returns declaration without any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceNamed(set of String) returns declaration without any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfacesNamed(name) returns declaration with given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesNamed(String) returns declaration with all given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesNamed(list of String) returns declaration with all given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesNamed(set of String) returns declaration with all given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(name) returns declaration without given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(String) returns declaration without all of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(list of String) returns declaration without all of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(set of String) returns declaration without all of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfacesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterface{} returns declaration with parent interface which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterface(predicate = predicate) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterface(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterface(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterface{} returns declaration without parent interface which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterface(predicate = predicate) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterface(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterface(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfaces{} returns declaration with all parent interfaces satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfaces(predicate = predicate) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfaces(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfaces(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfaces{} returns declaration with all parent interfaces which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfaces(predicate = predicate) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfaces(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfaces(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterfaces{} returns declaration with parent interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParentDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns listOf(parent1)
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns listOf(parent2)
            }
        val declaration3: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParentInterfaces(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutParentInterfaces{} returns declaration without parent interfaces which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParentDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns listOf(parent1)
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns listOf(parent2)
            }
        val declaration3: KoParentInterfaceProvider =
            mockk {
                every { parentInterfaces() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentInterfaces(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterfaceOf(empty list) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceOf(empty set) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesOf(empty list) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesOf(empty set) returns declaration with any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaceOf(empty list) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceOf(empty set) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesOf(empty list) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesOf(empty set) returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaces() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterfaceOf(KClass) returns declaration with any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceOf(list of KClass) returns declaration with any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withParentInterfaceOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentInterfaceOf(set of KClass) returns declaration with any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withParentInterfaceOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaceOf(KClass) returns declaration without all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceOf(list of KClass) returns declaration without all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutParentInterfaceOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentInterfaceOf(set of KClass) returns declaration without all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasParentInterfaceOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutParentInterfaceOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfacesOf(KClass) returns declaration with all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesOf(list of KClass) returns declaration with all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllParentInterfacesOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesOf(set of KClass) returns declaration with all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllParentInterfacesOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfacesOf(KClass) returns declaration without any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesOf(list of KClass) returns declaration without any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllParentInterfacesOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesOf(set of KClass) returns declaration without any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentInterfaceProvider =
            mockk {
                every { hasAllParentInterfacesOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllParentInterfacesOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
