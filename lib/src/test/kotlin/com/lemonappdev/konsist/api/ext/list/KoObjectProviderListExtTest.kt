package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectProviderListExtTest {
    @Test
    fun `objects() returns objects from all declarations`() {
        // given
        val object1: KoObjectDeclarationCore = mockk()
        val object2: KoObjectDeclarationCore = mockk()
        val object3: KoObjectDeclarationCore = mockk()
        val declaration1: KoObjectProvider =
            mockk {
                every { objects(includeNested = true) } returns listOf(object1, object2)
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { objects(includeNested = true) } returns listOf(object3)
            }
        val declaration3: KoObjectProvider =
            mockk {
                every { objects(includeNested = true) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.objects(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(object1, object2, object3)
    }

    @Test
    fun `withObjects() returns declaration with any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjects()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectNamed(empty list) returns declaration with any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectNamed(empty set) returns declaration with any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllObjectsNamed(empty list) returns declaration with any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllObjectsNamed(empty set) returns declaration with any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutObjects() returns declaration without any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjects()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectNamed(empty list) returns declaration without any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectNamed(empty set) returns declaration without any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllObjectsNamed(empty list) returns declaration without any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllObjectsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllObjectsNamed(empty set) returns declaration without any object`() {
        // given
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjects() } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjects() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllObjectsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withObjectNamed(name) returns declaration with given object`() {
        // given
        val name = "SampleName"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectNamed(String) returns declaration with any of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectNamed(list of String) returns declaration with any of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectNamed(set of String) returns declaration with any of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutObjectNamed(name) returns declaration without given object`() {
        // given
        val name = "SampleName"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectNamed(String) returns declaration without any of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectNamed(list of String) returns declaration without any of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectNamed(set of String) returns declaration without any of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutObjectNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllObjectsNamed(name) returns declaration with given object`() {
        // given
        val name = "SampleName"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllObjectsNamed(String) returns declaration with all given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllObjectsNamed(list of String) returns declaration with all given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllObjectsNamed(set of String) returns declaration with all given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllObjectsNamed(name) returns declaration without given object`() {
        // given
        val name = "SampleName"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllObjectsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllObjectsNamed(String) returns declaration without all of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllObjectsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllObjectsNamed(list of String) returns declaration without all of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllObjectsNamed(set of String) returns declaration without all of given objects`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObjectsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllObjectsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withObject{} returns declaration with objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObject(true, predicate) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObject(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObject(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutObject{} returns declaration without objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoObjectProvider =
            mockk {
                every { hasObject(true, predicate) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasObject(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObject(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllObjects{} returns declaration with all objects satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoObjectProvider =
            mockk {
                every { hasAllObjects(true, predicate) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasAllObjects(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllObjects{} returns declaration with all objects which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoObjectDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoObjectProvider =
            mockk {
                every { hasAllObjects(true, predicate) } returns true
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { hasAllObjects(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withObjects{} returns declaration with objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoObjectDeclaration>) -> Boolean =
            { it.all { koObject -> koObject.hasNameEndingWith(suffix) } }
        val object1: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val object2: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoObjectProvider =
            mockk {
                every { objects() } returns listOf(object1)
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { objects() } returns listOf(object2)
            }
        val declaration3: KoObjectProvider =
            mockk {
                every { objects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutObjects{} returns declaration without objects which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoObjectDeclaration>) -> Boolean =
            { it.all { koObject -> koObject.hasNameEndingWith(suffix) } }
        val object1: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val object2: KoObjectDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoObjectProvider =
            mockk {
                every { objects() } returns listOf(object1)
            }
        val declaration2: KoObjectProvider =
            mockk {
                every { objects() } returns listOf(object2)
            }
        val declaration3: KoObjectProvider =
            mockk {
                every { objects() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutObjects(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
