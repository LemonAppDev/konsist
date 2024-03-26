package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassProviderListExtTest {
    @Test
    fun `classes() returns classes from all declarations`() {
        // given
        val class1: KoClassDeclarationCore = mockk()
        val class2: KoClassDeclarationCore = mockk()
        val class3: KoClassDeclarationCore = mockk()
        val declaration1: KoClassProvider =
            mockk {
                every { classes(includeNested = true, includeLocal = false) } returns listOf(class1, class2)
            }
        val declaration2: KoClassProvider =
            mockk {
                every { classes(includeNested = true, includeLocal = false) } returns listOf(class3)
            }
        val declaration3: KoClassProvider =
            mockk {
                every { classes(includeNested = true, includeLocal = false) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classes(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(class1, class2, class3)
    }

    @Test
    fun `withClasses() returns declaration with any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClasses()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassNamed(empty list) returns declaration with any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassNamed(empty set) returns declaration with any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesNamed(empty list) returns declaration with any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesNamed(empty set) returns declaration with any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClasses() returns declaration without any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClasses()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassNamed(empty list) returns declaration without any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassNamed(empty set) returns declaration without any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesNamed(empty list) returns declaration without any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesNamed(empty set) returns declaration without any class`() {
        // given
        val declaration1: KoClassProvider =
            mockk {
                every { hasClasses() } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClassNamed(name) returns declaration with given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassNamed(String) returns declaration with any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassNamed(list of String) returns declaration with any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassNamed(set of String) returns declaration with any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassNamed(name) returns declaration without given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassNamed(String) returns declaration without any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassNamed(list of String) returns declaration without any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassNamed(set of String) returns declaration without any of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutClassNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClassesNamed(name) returns declaration with given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesNamed(String) returns declaration with all given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClassesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesNamed(list of String) returns declaration with all given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllClassesNamed(set of String) returns declaration with all given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClassesNamed(name) returns declaration without given class`() {
        // given
        val name = "SampleName"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesNamed(String) returns declaration without all of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClassesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesNamed(list of String) returns declaration without all of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllClassesNamed(set of String) returns declaration without all of given classes`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClassesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllClassesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClass{} returns declaration with classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassProvider =
            mockk {
                every { hasClass(true, true, predicate) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClass(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClass(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClass{} returns declaration without classes which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassProvider =
            mockk {
                every { hasClass(true, true, predicate) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasClass(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClass(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllClasses{} returns declaration with all classes satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassProvider =
            mockk {
                every { hasAllClasses(true, true, predicate) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasAllClasses(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllClasses(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllClasses{} returns declaration with all classes which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoClassProvider =
            mockk {
                every { hasAllClasses(true, true, predicate) } returns true
            }
        val declaration2: KoClassProvider =
            mockk {
                every { hasAllClasses(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllClasses(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withClasses{} returns declaration with classes which satisfy predicate`() {
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
        val declaration1: KoClassProvider =
            mockk {
                every { classes() } returns listOf(class1)
            }
        val declaration2: KoClassProvider =
            mockk {
                every { classes() } returns listOf(class2)
            }
        val declaration3: KoClassProvider =
            mockk {
                every { classes() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withClasses(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutClasses{} returns declaration without classes which satisfy predicate`() {
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
        val declaration1: KoClassProvider =
            mockk {
                every { classes() } returns listOf(class1)
            }
        val declaration2: KoClassProvider =
            mockk {
                every { classes() } returns listOf(class2)
            }
        val declaration3: KoClassProvider =
            mockk {
                every { classes() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutClasses(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
