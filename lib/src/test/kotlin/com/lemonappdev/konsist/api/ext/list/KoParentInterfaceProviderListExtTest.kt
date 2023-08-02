package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleInterface1
import com.lemonappdev.konsist.testdata.SampleInterface2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoParentInterfaceProviderListExtTest {
    @Test
    fun `withParentInterface() returns declaration with any parent interface`() {
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
    fun `withoutParentInterface() returns declaration without any parent interface`() {
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
    fun `withAllParentInterfaces() returns declaration with all of given parent interfaces`() {
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
    fun `withoutAllParentInterfaces() returns declaration without any of given parent interfaces`() {
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
    fun `withSomeParentInterfaces() returns declaration with given parent interface`() {
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
    fun `withSomeParentInterfaces() returns declarations with at least one of given parent interfaces`() {
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
    fun `withoutSomeParentInterfaces() returns declaration without given parent interface`() {
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
    fun `withoutSomeParentInterfaces() returns declarations without at least one of given parent interfaces`() {
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
    fun `withAllParentInterfacesOf(KClass) returns declaration with given parent interface`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withAllParentInterfacesOf(SampleInterface1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentInterfacesOf(KClass) returns declarations with all given parent interfaces`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withAllParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentInterfacesOf(KClass) returns declaration without given parent interface`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withoutAllParentInterfacesOf(SampleInterface1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentInterfacesOf(KClass) returns declaration without any of given parent interfaces`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withoutAllParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSomeParentInterfacesOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withSomeParentInterfacesOf(SampleInterface1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParentInterfacesOf(KClass) returns declarations with at least one of given parents`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withSomeParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParentInterfacesOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withoutSomeParentInterfacesOf(SampleInterface1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParentInterfacesOf(KClass) returns declarations with at least one of given parents`() {
        // given
        val name1 = "SampleInterface1"
        val name2 = "SampleInterface2"
        val name3 = "SampleInterface3"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentInterfaceDeclaration = mockk {
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
        val sut = declarations.withoutSomeParentInterfacesOf(SampleInterface1::class, SampleInterface2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withParentInterfaceOf() returns declaration with SampleInterface parent interface`() {
        // given
        val name1 = "SampleInterface"
        val name2 = "OtherInterface"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1)
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentInterfaceOf<SampleInterface>()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentInterfaceOf() returns declaration without SampleInterface parent interface`() {
        // given
        val name1 = "SampleInterface"
        val name2 = "OtherInterface"
        val parent1: KoParentInterfaceDeclaration = mockk {
            every { name } returns name1
        }
        val declaration1: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent1)
        }
        val parent2: KoParentInterfaceDeclaration = mockk {
            every { name } returns name2
        }
        val declaration2: KoParentInterfaceProvider = mockk {
            every { parentInterfaces } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentInterfaceOf<SampleInterface>()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
