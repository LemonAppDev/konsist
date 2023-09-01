package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoParentProviderListExtTest {
    @Test
    fun `parents returns parents from all declarations`() {
        // given
        val parent1: KoParentDeclaration = mockk()
        val parent2: KoParentDeclaration = mockk()
        val parent3: KoParentDeclaration = mockk()
        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent3)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parents

        // then
        sut shouldBeEqualTo listOf(parent1, parent2, parent3)
    }

    @Test
    fun `withParents() returns declaration with any parent`() {
        // given
        val declaration1: KoParentProvider = mockk {
            every { hasParents() } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParents()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParents() returns declaration without any parent`() {
        // given
        val declaration1: KoParentProvider = mockk {
            every { hasParents() } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParents()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParents(name) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParents(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParents(name) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParents(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParents(String) returns declaration with all given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name1, name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParents(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParents(String) returns declaration without any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name1, name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParents(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeParents(String) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParents(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParents(String) returns declarations with at least one of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name1) } returns true
            every { hasParents(name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name1) } returns false
            every { hasParents(name2) } returns true
        }
        val declaration3: KoParentProvider = mockk {
            every { hasParents(name1) } returns false
            every { hasParents(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParents(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParents(String) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParents(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParents(String) returns declarations without at least one of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParents(name1) } returns true
            every { hasParents(name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParents(name1) } returns false
            every { hasParents(name2) } returns true
        }
        val declaration3: KoParentProvider = mockk {
            every { hasParents(name1) } returns false
            every { hasParents(name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParents(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAllParentsOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsOf(KClass) returns declaration with all of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclaration = mockk {
            every { name } returns name3
        }

        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentsOf(KClass) returns declaration without given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsOf(KClass) returns declaration without any of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclaration = mockk {
            every { name } returns name3
        }

        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSomeParentsOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }

        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParentsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParentsOf(KClass) returns declarations with at least one of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclaration = mockk {
            every { name } returns name3
        }

        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParentsOf(KClass) returns declaration without given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }

        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent2)
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParentsOf(SampleClass::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParentsOf(KClass) returns declarations without at least one of given parents`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val name3 = "OtherParent"
        val parent1: KoParentDeclaration = mockk {
            every { name } returns name1
        }
        val parent2: KoParentDeclaration = mockk {
            every { name } returns name2
        }
        val parent3: KoParentDeclaration = mockk {
            every { name } returns name3
        }

        val declaration1: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents } returns listOf(parent1, parent3)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents } returns listOf(parent3)
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
