package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

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
    fun `parents() returns parents from all declarations`() {
        // given
        val parent1: KoParentDeclaration = mockk()
        val parent2: KoParentDeclaration = mockk()
        val parent3: KoParentDeclaration = mockk()
        val declaration1: KoParentProvider = mockk {
            every { parents() } returns listOf(parent1, parent2)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents() } returns listOf(parent3)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents() } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parents()

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
    fun `withParentNamed(name) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParentWithName(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentWithName(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentNamed(String) returns declaration with any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParentWithName(name1, name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentWithName(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentNamed(name) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParentWithName(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentWithName(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentNamed(String) returns declaration without any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParentWithName(name1, name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentWithName(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentsNamed(name) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsNamed(String) returns declaration with all given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name1, name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentsNamed(name) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsNamed(String) returns declaration without all of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name1, name2) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentsWithAllNames(name1, name2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParent{} returns declaration with parent which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentProvider = mockk {
            every { hasParent(predicate = predicate) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParent(predicate = predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParent(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParent{} returns declaration without parent which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentProvider = mockk {
            every { hasParent(predicate = predicate) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParent(predicate = predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParent(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParents{} returns declaration with all parents satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentProvider = mockk {
            every { hasAllParents(predicate = predicate) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasAllParents(predicate = predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParents{} returns declaration with all parents which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentProvider = mockk {
            every { hasAllParents(predicate = predicate) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasAllParents(predicate = predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParents{} returns declaration with parents which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParentDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoParentDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val parent2: KoParentDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val declaration1: KoParentProvider = mockk {
            every { parents() } returns listOf(parent1)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents() } returns listOf(parent2)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents() } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutParents{} returns declaration without parents which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParentDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoParentDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val parent2: KoParentDeclaration = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val declaration1: KoParentProvider = mockk {
            every { parents() } returns listOf(parent1)
        }
        val declaration2: KoParentProvider = mockk {
            every { parents() } returns listOf(parent2)
        }
        val declaration3: KoParentProvider = mockk {
            every { parents() } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParents(predicate = predicate)

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
    fun `withParentOf(KClass) returns declaration with any of given parents`() {
        // given
        val declaration1: KoParentProvider = mockk {
            every { hasParentOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentOf(KClass) returns declaration without all of given parents`() {
        // given
        val declaration1: KoParentProvider = mockk {
            every { hasParentOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasParentOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentsOf(KClass) returns declaration with all of given parents`() {
        // given
        val declaration1: KoParentProvider = mockk {
            every { hasAllParentsOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasAllParentsOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentsOf(KClass) returns declaration without any of given parents`() {
        // given
        val declaration1: KoParentProvider = mockk {
            every { hasAllParentsOf(SampleClass::class, SampleInterface::class) } returns true
        }
        val declaration2: KoParentProvider = mockk {
            every { hasAllParentsOf(SampleClass::class, SampleInterface::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
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
