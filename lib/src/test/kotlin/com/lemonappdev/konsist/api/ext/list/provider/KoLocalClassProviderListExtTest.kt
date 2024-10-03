package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLocalClassProviderListExtTest {
    @Test
    fun `localClasses returns local classes from all declarations`() {
        // given
        val localClass1: KoClassDeclaration = mockk()
        val localClass2: KoClassDeclaration = mockk()
        val localClass3: KoClassDeclaration = mockk()
        val declaration1: KoLocalClassProvider =
            mockk {
                every { localClasses } returns listOf(localClass1, localClass2)
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { localClasses } returns listOf(localClass3)
            }
        val declaration3: KoLocalClassProvider =
            mockk {
                every { localClasses } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.localClasses

        // then
        sut shouldBeEqualTo listOf(localClass1, localClass2, localClass3)
    }

    @Test
    fun `withLocalClasses() returns declaration with any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalClasses()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalClassNamed(empty list) returns declaration with any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalClassNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalClassNamed(empty set) returns declaration with any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalClassNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalClassesNamed(empty list) returns declaration with any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalClassesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalClassesNamed(empty set) returns declaration with any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalClassesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalClasses() returns declaration without any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalClasses()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalClassNamed(empty list) returns declaration without any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalClassNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalClassNamed(empty set) returns declaration without any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalClassNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalClassesNamed(empty list) returns declaration without any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalClassesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalClassesNamed(empty set) returns declaration without any class`() {
        // given
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalClassesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalClassNamed(name) returns declaration with given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalClassNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalClassNamed(String) returns declaration with any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalClassNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalClassNamed(list of String) returns declaration with any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withLocalClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalClassNamed(set of String) returns declaration with any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withLocalClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalClassNamed(name) returns declaration without given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalClassNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalClassNamed(String) returns declaration without any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalClassNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalClassNamed(list of String) returns declaration without any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutLocalClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalClassNamed(set of String) returns declaration without any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutLocalClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllLocalClassesNamed(name) returns declaration with given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalClassesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalClassesNamed(String) returns declaration with all given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalClassesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalClassesNamed(list of String) returns declaration with all given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllLocalClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalClassesNamed(set of String) returns declaration with all given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllLocalClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllLocalClassesNamed(name) returns declaration without given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalClassesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalClassesNamed(String) returns declaration without all of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalClassesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalClassesNamed(list of String) returns declaration without all of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllLocalClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalClassesNamed(set of String) returns declaration without all of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClassesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllLocalClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalClass{} returns declaration with local classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClass(predicate) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClass(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalClass(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalClass{} returns declaration without local classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasLocalClass(predicate) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasLocalClass(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalClass(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllLocalClasses{} returns declaration with all local classes satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasAllLocalClasses(predicate) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasAllLocalClasses(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalClasses(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllLocalClasses{} returns declaration with all local classes which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalClassProvider =
            mockk {
                every { hasAllLocalClasses(predicate) } returns true
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { hasAllLocalClasses(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalClasses(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalClass{} returns declaration with classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassDeclaration>) -> Boolean =
            { it.all { koClass -> koClass.hasNameEndingWith(suffix) } }
        val class1: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val class2: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoLocalClassProvider =
            mockk {
                every { localClasses } returns listOf(class1)
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { localClasses } returns listOf(class2)
            }
        val declaration3: KoLocalClassProvider =
            mockk {
                every { localClasses } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withLocalClasses(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutLocalClass{} returns declaration without classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoClassDeclaration>) -> Boolean =
            { it.all { koClass -> koClass.hasNameEndingWith(suffix) } }
        val class1: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val class2: KoClassDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoLocalClassProvider =
            mockk {
                every { localClasses } returns listOf(class1)
            }
        val declaration2: KoLocalClassProvider =
            mockk {
                every { localClasses } returns listOf(class2)
            }
        val declaration3: KoLocalClassProvider =
            mockk {
                every { localClasses } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutLocalClasses(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}