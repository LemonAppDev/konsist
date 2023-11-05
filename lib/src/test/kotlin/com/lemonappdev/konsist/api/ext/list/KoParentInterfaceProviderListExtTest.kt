package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentInterfaceProviderListExtTest {
    @Test
    fun `parentInterfaces returns parent interfaces from all declarations`() {
        // given
        val parent1: KoInterfaceDeclaration = mockk()
        val parent2: KoInterfaceDeclaration = mockk()
        val parent3: KoInterfaceDeclaration = mockk()
        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val declaration3: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parentInterfaces

        // then
        sut shouldBeEqualTo listOf(parent1, parent2, parent3)
    }

    @Test
    fun `parentInterfaces() returns parent interfaces from all declarations`() {
        // given
        val parent1: KoInterfaceDeclaration = mockk()
        val parent2: KoInterfaceDeclaration = mockk()
        val parent3: KoInterfaceDeclaration = mockk()
        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns listOf(parent3)
        }
        val declaration3: KoParentInterfaceProvider = mockk {
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
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces() } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaces() returns declaration without any parent interface`() {
        // given
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces() } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaces()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterfaceNamed(name) returns declaration with given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name) } returns false
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
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name1, name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaceNamed(name) returns declaration without given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name) } returns false
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
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name1, name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceWithName(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfacesNamed(name) returns declaration with given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name) } returns false
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
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name1, name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfacesNamed(name) returns declaration without given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name) } returns false
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
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name1, name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfacesWithAllNames(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentInterface{} returns declaration with parent interface which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterface(predicate = predicate) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
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
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterface(predicate = predicate) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
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
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasAllParentInterfaces(predicate = predicate) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
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
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasAllParentInterfaces(predicate = predicate) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
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
        val predicate: (List<KoInterfaceDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoInterfaceDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val parent2: KoInterfaceDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns listOf(parent1)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns listOf(parent2)
        }
        val declaration3: KoParentInterfaceProvider = mockk {
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
        val predicate: (List<KoInterfaceDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoInterfaceDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val parent2: KoInterfaceDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns listOf(parent1)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns listOf(parent2)
        }
        val declaration3: KoParentInterfaceProvider = mockk {
            every { parentInterfaces() } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentInterfaces(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfaces(name) returns declaration with given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfaces(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfaces(name) returns declaration without given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfaces(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfaces(String) returns declaration with all given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1, name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfaces(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfaces(String) returns declaration without any of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1, name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfaces(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeParentInterfaces(String) returns declaration with given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParentInterfaces(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParentInterfaces(String) returns declarations with at least one of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1) } returns true
            every { hasParentInterfaces(name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1) } returns false
            every { hasParentInterfaces(name2) } returns true
        }
        val declaration3: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1) } returns false
            every { hasParentInterfaces(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParentInterfaces(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParentInterfaces(String) returns declaration without given parent interface`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParentInterfaces(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParentInterfaces(String) returns declarations without at least one of given parent interfaces`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1) } returns true
            every { hasParentInterfaces(name2) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1) } returns false
            every { hasParentInterfaces(name2) } returns true
        }
        val declaration3: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaces(name1) } returns false
            every { hasParentInterfaces(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParentInterfaces(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withParentInterfaceOf(KClass) returns declaration with any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaceOf(KClass) returns declaration without all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasParentInterfaceOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentInterfacesOf(KClass) returns declaration with all of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasAllParentInterfacesOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasAllParentInterfacesOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentInterfacesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfacesOf(KClass) returns declaration without any of given parent interfaces`() {
        // given
        val declaration1: KoParentInterfaceProvider = mockk {
            every { hasAllParentInterfacesOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { hasAllParentInterfacesOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentInterfacesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeParentInterfacesOf(KClass) returns declaration with given parent interface`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoInterfaceDeclaration = mockk {
            every { name } returns name2
        }

        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParentInterfacesOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParentInterfacesOf(KClass) returns declarations with at least one of given parent interfaces`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoInterfaceDeclaration = mockk {
            every { name } returns name3
        }

        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParentInterfacesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParentInterfacesOf(KClass) returns declaration without given parent interface`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoInterfaceDeclaration = mockk {
            every { name } returns name2
        }

        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParentInterfacesOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParentInterfacesOf(KClass) returns declarations without at least one of given parent interfaces`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoInterfaceDeclaration = mockk {
            every { name } returns name3
        }

        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParentInterfacesOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
