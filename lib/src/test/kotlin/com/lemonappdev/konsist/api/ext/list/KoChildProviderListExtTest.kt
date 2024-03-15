package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoChildProviderListExtTest {
    @Test
    fun `children() returns children from all declarations`() {
        // given
        val child1: KoChildDeclaration = mockk()
        val child2: KoChildDeclaration = mockk()
        val child3: KoChildDeclaration = mockk()
        val declaration1: KoChildProvider =
            mockk {
                every { children() } returns listOf(child1, child2)
            }
        val declaration2: KoChildProvider =
            mockk {
                every { children() } returns listOf(child3)
            }
        val declaration3: KoChildProvider =
            mockk {
                every { children() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.children()

        // then
        sut shouldBeEqualTo listOf(child1, child2, child3)
    }

    @Test
    fun `withChildren() returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildren()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildNamed(empty list) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildNamed(empty set) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenNamed(empty list) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenNamed(empty set) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutChildren() returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildren()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildNamed(empty list) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildNamed(empty set) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenNamed(empty list) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenNamed(empty set) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withChildNamed(name) returns declaration with given child`() {
        // given
        val name = "SampleName"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildNamed(String) returns declaration with any of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildNamed(list of String) returns declaration with any of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withChildNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildNamed(set of String) returns declaration with any of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withChildNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutChildNamed(name) returns declaration without given child`() {
        // given
        val name = "SampleName"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildNamed(String) returns declaration without any of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildNamed(list of String) returns declaration without any of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutChildNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildNamed(set of String) returns declaration without any of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutChildNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllChildrenNamed(name) returns declaration with given child`() {
        // given
        val name = "SampleName"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenNamed(String) returns declaration with all given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenNamed(list of String) returns declaration with all given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllChildrenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenNamed(set of String) returns declaration with all given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllChildrenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllChildrenNamed(name) returns declaration without given child`() {
        // given
        val name = "SampleName"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenNamed(String) returns declaration without all of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenNamed(list of String) returns declaration without all of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllChildrenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenNamed(set of String) returns declaration without all of given children`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildrenWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllChildrenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withChild{} returns declaration with child which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoChildDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoChildProvider =
            mockk {
                every { hasChild(predicate = predicate) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChild(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChild(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutChild{} returns declaration without child which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoChildDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoChildProvider =
            mockk {
                every { hasChild(predicate = predicate) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChild(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChild(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllChildren{} returns declaration with all children satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoChildDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildren(predicate = predicate) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildren(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildren(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllChildren{} returns declaration with all children which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoChildDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildren(predicate = predicate) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildren(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildren(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withChildren{} returns declaration with children which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoChildDeclaration>) -> Boolean =
            { it.all { child -> child.hasNameEndingWith(suffix) } }
        val child1: KoChildDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val child2: KoChildDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoChildProvider =
            mockk {
                every { children() } returns listOf(child1)
            }
        val declaration2: KoChildProvider =
            mockk {
                every { children() } returns listOf(child2)
            }
        val declaration3: KoChildProvider =
            mockk {
                every { children() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withChildren(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutChildren{} returns declaration without children which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoChildDeclaration>) -> Boolean =
            { it.all { child -> child.hasNameEndingWith(suffix) } }
        val child1: KoChildDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val child2: KoChildDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoChildProvider =
            mockk {
                every { children() } returns listOf(child1)
            }
        val declaration2: KoChildProvider =
            mockk {
                every { children() } returns listOf(child2)
            }
        val declaration3: KoChildProvider =
            mockk {
                every { children() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutChildren(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withChildOf(empty list) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildOf(empty set) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenOf(empty list) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenOf(empty set) returns declaration with any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutChildOf(empty list) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildOf(empty set) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenOf(empty list) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenOf(empty set) returns declaration without any child`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildren() } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildren() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withChildOf(KClass) returns declaration with any of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withChildOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildOf(list of KClass) returns declaration with any of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withChildOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withChildOf(set of KClass) returns declaration with any of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withChildOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutChildOf(KClass) returns declaration without all of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutChildOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildOf(list of KClass) returns declaration without all of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutChildOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutChildOf(set of KClass) returns declaration without all of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasChildOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutChildOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllChildrenOf(KClass) returns declaration with all of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllChildrenOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenOf(list of KClass) returns declaration with all of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllChildrenOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllChildrenOf(set of KClass) returns declaration with all of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withAllChildrenOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllChildrenOf(KClass) returns declaration without any of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllChildrenOf(SampleClass::class, SampleInterface::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenOf(list of KClass) returns declaration without any of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = listOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllChildrenOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllChildrenOf(set of KClass) returns declaration without any of given children`() {
        // given
        val declaration1: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns true
            }
        val declaration2: KoChildProvider =
            mockk {
                every { hasAllChildrenOf(SampleClass::class, SampleInterface::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val kClasses = setOf(SampleClass::class, SampleInterface::class)

        // when
        val sut = declarations.withoutAllChildrenOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
