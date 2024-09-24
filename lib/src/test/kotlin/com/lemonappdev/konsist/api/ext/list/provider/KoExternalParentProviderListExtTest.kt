package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoExternalParentProviderListExtTest {
    @Test
    fun `externalParents() returns external parents from all declarations`() {
        // given
        val parent1: KoExternalDeclaration = mockk()
        val parent2: KoExternalDeclaration = mockk()
        val parent3: KoExternalDeclaration = mockk()
        val declaration1: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns listOf(parent1, parent2)
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns listOf(parent3)
            }
        val declaration3: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.externalParents()

        // then
        sut shouldBeEqualTo listOf(parent1, parent2, parent3)
    }

    @Test
    fun `withExternalParents() returns declaration with any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParents()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentNamed(empty list) returns declaration with any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentNamed(empty set) returns declaration with any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsNamed(empty list) returns declaration with any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsNamed(empty set) returns declaration with any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalParents() returns declaration without any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParents()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentNamed(empty list) returns declaration without any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentNamed(empty set) returns declaration without any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsNamed(empty list) returns declaration without any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsNamed(empty set) returns declaration without any external parent`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExternalParentNamed(name) returns declaration with given external parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentNamed(String) returns declaration with any of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentNamed(list of String) returns declaration with any of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withExternalParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentNamed(set of String) returns declaration with any of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withExternalParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalParentNamed(name) returns declaration without given external parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentNamed(String) returns declaration without any of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentNamed(list of String) returns declaration without any of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutExternalParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentNamed(set of String) returns declaration without any of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutExternalParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllExternalParentsNamed(name) returns declaration with given external parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsNamed(String) returns declaration with all given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsNamed(list of String) returns declaration with all given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllExternalParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsNamed(set of String) returns declaration with all given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllExternalParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllExternalParentsNamed(name) returns declaration without given external parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsNamed(String) returns declaration without all of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsNamed(list of String) returns declaration without all of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllExternalParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsNamed(set of String) returns declaration without all of given external parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllExternalParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExternalParent{} returns declaration with external parent which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoExternalDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParent(predicate = predicate) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParent(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParent(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalParent{} returns declaration without external parent which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoExternalDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParent(predicate = predicate) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParent(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParent(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllExternalParents{} returns declaration with all external parents satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoExternalDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParents(predicate = predicate) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParents(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllExternalParents{} returns declaration with all external parents which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoExternalDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParents(predicate = predicate) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParents(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExternalParents{} returns declaration with external parents which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoExternalDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoExternalDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoExternalDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns listOf(parent1)
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns listOf(parent2)
            }
        val declaration3: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExternalParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutExternalParents{} returns declaration without external parents which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoExternalDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoExternalDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoExternalDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns listOf(parent1)
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns listOf(parent2)
            }
        val declaration3: KoExternalParentProvider =
            mockk {
                every { externalParents() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExternalParents(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExternalParentOf(empty list) returns declaration with any external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentOf(empty set) returns declaration with any external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsOf(empty list) returns declaration with any external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsOf(empty set) returns declaration with any external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalParentOf(empty list) returns declaration with none external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentOf(empty set) returns declaration with none external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsOf(empty list) returns declaration with none external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsOf(empty set) returns declaration with none external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExternalParentOf(KClass) returns declaration with any of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalParentOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentOf(list of KClass) returns declaration with any of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withExternalParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalParentOf(set of KClass) returns declaration with any of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withExternalParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalParentOf(KClass) returns declaration without all of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalParentOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentOf(list of KClass) returns declaration without all of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutExternalParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalParentOf(set of KClass) returns declaration without all of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasExternalParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutExternalParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllExternalParentsOf(KClass) returns declaration with all of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllExternalParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsOf(list of KClass) returns declaration with all of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllExternalParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllExternalParentsOf(set of KClass) returns declaration with all of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllExternalParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllExternalParentsOf(KClass) returns declaration without any of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllExternalParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsOf(list of KClass) returns declaration without any of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllExternalParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllExternalParentsOf(set of KClass) returns declaration without any of given external parents`() {
        // given
        val declaration1: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoExternalParentProvider =
            mockk {
                every { hasAllExternalParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllExternalParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
