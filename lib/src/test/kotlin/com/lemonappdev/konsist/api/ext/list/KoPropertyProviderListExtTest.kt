package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyProviderListExtTest {
    @Test
    fun `properties() returns properties from all declarations`() {
        // given
        val property1: KoPropertyDeclarationCore = mockk()
        val property2: KoPropertyDeclarationCore = mockk()
        val property3: KoPropertyDeclarationCore = mockk()
        val declaration1: KoPropertyProvider =
            mockk {
                every { properties(includeNested = true) } returns listOf(property1, property2)
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { properties(includeNested = true) } returns listOf(property3)
            }
        val declaration3: KoPropertyProvider =
            mockk {
                every { properties(includeNested = true) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.properties(includeNested = true)

        // then
        sut shouldBeEqualTo listOf(property1, property2, property3)
    }

    @Test
    fun `withProperties() returns declaration with any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withProperties()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPropertyNamed(empty list) returns declaration with any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPropertyNamed(empty set) returns declaration with any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllPropertiesNamed(empty list) returns declaration with any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllPropertiesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllPropertiesNamed(empty set) returns declaration with any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllPropertiesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProperties() returns declaration without any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProperties()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPropertyNamed(empty list) returns declaration without any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPropertyNamed(empty set) returns declaration without any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllPropertiesNamed(empty list) returns declaration without any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllPropertiesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllPropertiesNamed(empty set) returns declaration without any property`() {
        // given
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperties() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllPropertiesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPropertyNamed(name) returns declaration with given property`() {
        // given
        val name = "SampleName"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPropertyNamed(String) returns declaration with any of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPropertyNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPropertyNamed(list of String) returns declaration with any of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withPropertyNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withPropertyNamed(set of String) returns declaration with any of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withPropertyNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPropertyNamed(name) returns declaration without given property`() {
        // given
        val name = "SampleName"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPropertyNamed(String) returns declaration without any of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPropertyNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPropertyNamed(list of String) returns declaration without any of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutPropertyNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutPropertyNamed(set of String) returns declaration without any of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertyWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutPropertyNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllPropertiesNamed(name) returns declaration with given property`() {
        // given
        val name = "SampleName"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllPropertiesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllPropertiesNamed(String) returns declaration with all given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllPropertiesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllPropertiesNamed(list of String) returns declaration with all given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllPropertiesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllPropertiesNamed(set of String) returns declaration with all given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllPropertiesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllPropertiesNamed(name) returns declaration without given property`() {
        // given
        val name = "SampleName"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllPropertiesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllPropertiesNamed(String) returns declaration without all of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllPropertiesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllPropertiesNamed(list of String) returns declaration without all of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllPropertiesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllPropertiesNamed(set of String) returns declaration without all of given properties`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasPropertiesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllPropertiesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProperty{} returns declaration with properties which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoPropertyDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperty(true, predicate) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperty(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withProperty(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProperty{} returns declaration without properties which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoPropertyDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasProperty(true, predicate) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasProperty(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProperty(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllProperties{} returns declaration with all properties satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoPropertyDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasAllProperties(true, predicate) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasAllProperties(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllProperties(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllProperties{} returns declaration with all properties which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoPropertyDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoPropertyProvider =
            mockk {
                every { hasAllProperties(true, predicate) } returns true
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { hasAllProperties(true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllProperties(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProperties{} returns declaration with properties which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoPropertyDeclaration>) -> Boolean =
            { it.all { koProperty -> koProperty.hasNameEndingWith(suffix) } }
        val property1: KoPropertyDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val property2: KoPropertyDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoPropertyProvider =
            mockk {
                every { properties() } returns listOf(property1)
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { properties() } returns listOf(property2)
            }
        val declaration3: KoPropertyProvider =
            mockk {
                every { properties() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withProperties(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutProperties{} returns declaration without properties which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoPropertyDeclaration>) -> Boolean =
            { it.all { koProperty -> koProperty.hasNameEndingWith(suffix) } }
        val property1: KoPropertyDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val property2: KoPropertyDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoPropertyProvider =
            mockk {
                every { properties() } returns listOf(property1)
            }
        val declaration2: KoPropertyProvider =
            mockk {
                every { properties() } returns listOf(property2)
            }
        val declaration3: KoPropertyProvider =
            mockk {
                every { properties() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutProperties(true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
