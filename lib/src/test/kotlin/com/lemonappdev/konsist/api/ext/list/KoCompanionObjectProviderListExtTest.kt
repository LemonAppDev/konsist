package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoCompanionObjectProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoCompanionObjectProviderListExtTest {
    @Test
    fun `companionObjects returns companion objects from all declarations`() {
        // given
        val companionObject1: KoCompanionObjectDeclaration = mockk()
        val companionObject2: KoCompanionObjectDeclaration = mockk()
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { companionObject } returns companionObject1
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { companionObject } returns companionObject2
            }
        val declaration3: KoCompanionObjectProvider =
            mockk {
                every { companionObject } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.companionObjects

        // then
        sut shouldBeEqualTo listOf(companionObject1, companionObject2)
    }

    @Test
    fun `companionObjects() returns companion objects from all declarations`() {
        // given
        val companionObject1: KoCompanionObjectDeclaration = mockk()
        val companionObject2: KoCompanionObjectDeclaration = mockk()
        val companionObject3: KoCompanionObjectDeclaration = mockk()
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { companionObjects(includeNested = true) } returns listOf(companionObject1, companionObject2)
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { companionObjects(includeNested = true) } returns listOf(companionObject3)
            }
        val declaration3: KoCompanionObjectProvider =
            mockk {
                every { companionObjects(includeNested = true) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.companionObjects(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(companionObject1, companionObject2, companionObject3)
    }

    @Test
    fun `withCompanionObject() returns declaration with any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObject()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(empty list) returns declaration with any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(empty set) returns declaration with any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(empty list) returns declaration with any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(empty set) returns declaration with any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCompanionObject() returns declaration without any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObject()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(empty list) returns declaration without any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(empty set) returns declaration without any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(empty list) returns declaration without any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(empty set) returns declaration without any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withCompanionObjects() returns declaration with any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects(includeNested = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects(includeNested = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObjects(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCompanionObjects() returns declaration without any companion object`() {
        // given
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects(includeNested = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjects(includeNested = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObjects(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withCompanionObject{} returns declaration with companion object which satisfy predicate`() {
        // given
        val prefix = "sample"
        val predicate: (KoCompanionObjectDeclaration) -> Boolean = { it.hasNameStartingWith(prefix) }
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject(includeNested = true, predicate) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject(includeNested = true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObject(includeNested = true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCompanionObject{} returns declaration without companion object which satisfy predicate`() {
        // given
        val prefix = "sample"
        val predicate: (KoCompanionObjectDeclaration) -> Boolean = { it.hasNameStartingWith(prefix) }
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject(includeNested = true, predicate) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObject(includeNested = true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObject(includeNested = true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllCompanionObjects{} returns declaration with all companion objects satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoCompanionObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasAllCompanionObjects(predicate = predicate) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasAllCompanionObjects(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllCompanionObjects(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllCompanionObjects{} returns declaration with all companion objects which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoCompanionObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasAllCompanionObjects(predicate = predicate) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasAllCompanionObjects(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllCompanionObjects(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withCompanionObjects{} returns declaration with companion objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoCompanionObjectDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoCompanionObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoCompanionObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { companionObjects() } returns listOf(parent1)
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { companionObjects() } returns listOf(parent2)
            }
        val declaration3: KoCompanionObjectProvider =
            mockk {
                every { companionObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withCompanionObjects(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutCompanionObjects{} returns declaration without companion objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoCompanionObjectDeclaration>) -> Boolean =
            { it.all { parent -> parent.hasNameEndingWith(suffix) } }
        val parent1: KoCompanionObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parent2: KoCompanionObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { companionObjects() } returns listOf(parent1)
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { companionObjects() } returns listOf(parent2)
            }
        val declaration3: KoCompanionObjectProvider =
            mockk {
                every { companionObjects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutCompanionObjects(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withCompanionObjectNamed(name) returns declaration with given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(String) returns declaration with any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(list of String) returns declaration with any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withCompanionObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(set of String) returns declaration with any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withCompanionObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(name) with ignore case returns declaration with given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionObjectNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withCompanionObjectNamed(list of String) with ignore case returns declaration with any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withCompanionObjectNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCompanionObjectNamed(name) returns declaration without given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(String) returns declaration without any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(list of String) returns declaration without any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(set of String) returns declaration without any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(name) with ignore case returns declaration without given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutCompanionObjectNamed(list of String) with ignore case returns declaration without any of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectWithName(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutCompanionObjectNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllCompanionObjectsNamed(name) returns declaration with given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(String) returns declaration with all given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(list of String) returns declaration with all given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(set of String) returns declaration with all given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(name) with ignore case returns declaration with given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllCompanionObjectsNamed(list of String) with ignore case returns declaration with all given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllCompanionObjectsNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(name) returns declaration without given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(String) returns declaration without all of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(list of String) returns declaration without all of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(set of String) returns declaration without all of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(name) with ignore case returns declaration without given companion object`() {
        // given
        val name = "SampleName"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllCompanionObjectsNamed(list of String) with ignore case returns declaration without all of given companion objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoCompanionObjectProvider =
            mockk {
                every { hasCompanionObjectsWithAllNames(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllCompanionObjectsNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
