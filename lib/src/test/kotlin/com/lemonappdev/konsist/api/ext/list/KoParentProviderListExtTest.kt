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
        val declaration1: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent3)
            }
        val declaration3: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { parents() } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentProvider =
            mockk {
                every { parents() } returns listOf(parent3)
            }
        val declaration3: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParents()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentNamed(empty list) returns declaration with any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentNamed(empty set) returns declaration with any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsNamed(empty list) returns declaration with any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsNamed(empty set) returns declaration with any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParents() returns declaration without any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParents()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentNamed(empty list) returns declaration without any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentNamed(empty set) returns declaration without any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsNamed(empty list) returns declaration without any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsNamed(empty set) returns declaration without any parent`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentNamed(name) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name)) } returns false
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentNamed(list of String) returns declaration with any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentNamed(set of String) returns declaration with any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentNamed(name) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name)) } returns false
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentNamed(list of String) returns declaration without any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentNamed(set of String) returns declaration without any of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutParentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentsNamed(name) returns declaration with given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name)) } returns false
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsNamed(list of String) returns declaration with all given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsNamed(set of String) returns declaration with all given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentsNamed(name) returns declaration without given parent`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name)) } returns false
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsNamed(list of String) returns declaration without all of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsNamed(set of String) returns declaration without all of given parents`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllParentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParent{} returns declaration with parent which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParentProvider =
            mockk {
                every { hasParent(predicate = predicate) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParent(predicate = predicate) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParents(predicate = predicate) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParents(predicate = predicate) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val parent1: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoParentProvider =
            mockk {
                every { parents() } returns listOf(parent1)
            }
        val declaration2: KoParentProvider =
            mockk {
                every { parents() } returns listOf(parent2)
            }
        val declaration3: KoParentProvider =
            mockk {
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
        val parent1: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoParentProvider =
            mockk {
                every { parents() } returns listOf(parent1)
            }
        val declaration2: KoParentProvider =
            mockk {
                every { parents() } returns listOf(parent2)
            }
        val declaration3: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name1, name2) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name1, name2) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name1) } returns true
                every { hasParents(name2) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents(name1) } returns false
                every { hasParents(name2) } returns true
            }
        val declaration3: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents(name1) } returns true
                every { hasParents(name2) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents(name1) } returns false
                every { hasParents(name2) } returns true
            }
        val declaration3: KoParentProvider =
            mockk {
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
    fun `withParentOf(empty list) returns declaration with any parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentOf(empty set) returns declaration with any parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsOf(empty list) returns declaration with any parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsOf(empty set) returns declaration with any parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentOf(empty list) returns declaration without parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentOf(empty set) returns declaration without parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsOf(empty list) returns declaration without parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsOf(empty set) returns declaration without parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParents() } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParents() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentOf(KClass) returns declaration with any of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentOf(list of KClass) returns declaration with any of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentOf(set of KClass) returns declaration with any of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentOf(KClass) returns declaration without all of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentOf(list of KClass) returns declaration without all of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentOf(set of KClass) returns declaration without all of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasParentOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutParentOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParentsOf(KClass) returns declaration with all of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsOf(list of KClass) returns declaration with all of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParentsOf(set of KClass) returns declaration with all of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasAllParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParentsOf(KClass) returns declaration without any of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsOf(list of KClass) returns declaration without any of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasAllParentsOf(listOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParentsOf(set of KClass) returns declaration without any of given parents`() {
        // given
        val declaration1: KoParentProvider =
            mockk {
                every { hasAllParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns true
            }
        val declaration2: KoParentProvider =
            mockk {
                every { hasAllParentsOf(setOf(SampleClass::class, SampleInterface::class)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllParentsOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeParentsOf(KClass) returns declaration with given parent`() {
        // given
        val name1 = "SampleClass"
        val name2 = "SampleInterface"
        val parent1: KoParentDeclaration =
            mockk {
                every { name } returns name1
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { name } returns name2
            }

        val declaration1: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val parent1: KoParentDeclaration =
            mockk {
                every { name } returns name1
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { name } returns name2
            }
        val parent3: KoParentDeclaration =
            mockk {
                every { name } returns name3
            }

        val declaration1: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent3)
            }
        val declaration3: KoParentProvider =
            mockk {
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
        val parent1: KoParentDeclaration =
            mockk {
                every { name } returns name1
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { name } returns name2
            }

        val declaration1: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentProvider =
            mockk {
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
        val parent1: KoParentDeclaration =
            mockk {
                every { name } returns name1
            }
        val parent2: KoParentDeclaration =
            mockk {
                every { name } returns name2
            }
        val parent3: KoParentDeclaration =
            mockk {
                every { name } returns name3
            }

        val declaration1: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent2)
            }
        val declaration2: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent1, parent3)
            }
        val declaration3: KoParentProvider =
            mockk {
                every { parents } returns listOf(parent3)
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParentsOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
