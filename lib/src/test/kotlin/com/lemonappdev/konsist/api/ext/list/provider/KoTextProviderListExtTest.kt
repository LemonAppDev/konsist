package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoTextProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoTextProviderListExtTest {
    @Test
    fun `withText() returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withText()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withText(empty list) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withText(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withText(empty set) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withText(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutText() returns declaration without text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutText()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutText(empty list) returns declaration without text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutText(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutText(empty set) returns declaration without text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutText(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withText() returns declaration with given text`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withText(text1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withText() returns declarations with one of given texts`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val text3 = "sampleText3"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declaration3: KoTextProvider =
            mockk {
                every { text } returns text3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withText(text1, text2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withText(List) returns declarations with one of given texts`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val text3 = "sampleText3"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declaration3: KoTextProvider =
            mockk {
                every { text } returns text3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = listOf(text1, text2)

        // when
        val sut = declarations.withText(texts)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withText(Set) returns declarations with one of given texts`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val text3 = "sampleText3"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declaration3: KoTextProvider =
            mockk {
                every { text } returns text3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = setOf(text1, text2)

        // when
        val sut = declarations.withText(texts)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutText() returns declaration without given text`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutText(text1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutText() returns declaration without any of given texts`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val text3 = "sampleText3"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declaration3: KoTextProvider =
            mockk {
                every { text } returns text3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutText(text1, text2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutText(List) returns declarations with one of given texts`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val text3 = "sampleText3"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declaration3: KoTextProvider =
            mockk {
                every { text } returns text3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = listOf(text1, text2)

        // when
        val sut = declarations.withoutText(texts)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutText(Set) returns declarations with one of given texts`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val text3 = "sampleText3"
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declaration3: KoTextProvider =
            mockk {
                every { text } returns text3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = setOf(text1, text2)

        // when
        val sut = declarations.withoutText(texts)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withText{predicate} returns declaration with text matching to predicate`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val prefix = "sample"
        val suffix = "1"
        val predicate: (String) -> Boolean = { it.startsWith(prefix) && it.endsWith(suffix) }
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withText(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutText{predicate} returns declaration without text matching to predicate`() {
        // given
        val text1 = "sampleText1"
        val text2 = "sampleText2"
        val prefix = "sample"
        val suffix = "1"
        val predicate: (String) -> Boolean = { it.startsWith(prefix) && it.endsWith(suffix) }
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutText(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTextStartingWith(empty list) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextStartingWith(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextStartingWith(empty set) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextStartingWith(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTextStartingWith(empty list) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextStartingWith(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextStartingWith(empty set) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextStartingWith(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTextStartingWith() returns declaration which texts starts with given prefix`() {
        // given
        val prefix = "prefix"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextStartingWith(prefix)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextStartingWith() returns declarations which texts starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns false
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTextStartingWith(prefix1, prefix2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextStartingWith(List) returns declarations which texts starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns false
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val prefixes = listOf(prefix1, prefix2)

        // when
        val sut = declarations.withTextStartingWith(prefixes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextStartingWith(Set) returns declarations which texts starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns false
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val prefixes = setOf(prefix1, prefix2)

        // when
        val sut = declarations.withTextStartingWith(prefixes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTextStartingWith() returns declaration which text not starts with given prefix`() {
        // given
        val prefix = "prefix"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextStartingWith(prefix)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextStartingWith() returns declaration which text not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns false
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTextStartingWith(prefix1, prefix2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextStartingWith(List) returns declaration which text not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns false
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val prefixes = listOf(prefix1, prefix2)

        // when
        val sut = declarations.withoutTextStartingWith(prefixes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextStartingWith(Set) returns declaration which text not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns true
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextStartingWith(prefix1) } returns false
                every { hasTextStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val prefixes = setOf(prefix1, prefix2)

        // when
        val sut = declarations.withoutTextStartingWith(prefixes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTextEndingWith(empty list) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextEndingWith(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextEndingWith(empty set) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextEndingWith(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTextEndingWith(empty list) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextEndingWith(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextEndingWith(empty set) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextEndingWith(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTextEndingWith() returns declaration which texts ends with given suffix`() {
        // given
        val suffix = "suffix"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextEndingWith(suffix)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextEndingWith() returns declarations which texts ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns false
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTextEndingWith(suffix1, suffix2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextEndingWith(List) returns declarations which texts ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns false
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val suffixes = listOf(suffix1, suffix2)

        // when
        val sut = declarations.withTextEndingWith(suffixes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextEndingWith(Set) returns declarations which texts ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns false
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val suffixes = setOf(suffix1, suffix2)

        // when
        val sut = declarations.withTextEndingWith(suffixes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTextEndingWith() returns declaration which text not ends with given suffix`() {
        // given
        val suffix = "suffix"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextEndingWith(suffix)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextEndingWith() returns declaration which text not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns false
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTextEndingWith(suffix1, suffix2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextEndingWith(List) returns declaration which text not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns false
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val suffixes = listOf(suffix1, suffix2)

        // when
        val sut = declarations.withoutTextEndingWith(suffixes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextEndingWith(Set) returns declaration which text not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns true
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextEndingWith(suffix1) } returns false
                every { hasTextEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val suffixes = setOf(suffix1, suffix2)

        // when
        val sut = declarations.withoutTextEndingWith(suffixes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTextContaining(empty list) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextContaining(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextContaining(empty set) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextContaining(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTextContaining(empty list) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextContaining(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextContaining(empty set) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextContaining(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTextContaining() returns declaration which texts contains given text`() {
        // given
        val text = "text"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextContaining(text)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextContaining() returns declarations which texts contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns false
                every { hasTextContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTextContaining(text1, text2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextContaining(List) returns declarations which texts contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns false
                every { hasTextContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = listOf(text1, text2)

        // when
        val sut = declarations.withTextContaining(texts)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextContaining(Set) returns declarations which texts contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns false
                every { hasTextContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = setOf(text1, text2)

        // when
        val sut = declarations.withTextContaining(texts)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTextContaining() returns declaration which text not contains given text`() {
        // given
        val text = "text"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextContaining(text)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextContaining() returns declaration which text not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns false
                every { hasTextContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTextContaining(text1, text2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextContaining(List) returns declaration which text not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns false
                every { hasTextContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = listOf(text1, text2)

        // when
        val sut = declarations.withoutTextContaining(texts)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextContaining(Set) returns declaration which text not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns true
                every { hasTextContaining(text2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextContaining(text1) } returns false
                every { hasTextContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val texts = setOf(text1, text2)

        // when
        val sut = declarations.withoutTextContaining(texts)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTextMatching(empty list) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextMatching(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextMatching(empty set) returns declaration with any text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextMatching(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTextMatching(empty list) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextMatching(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextMatching(empty set) returns declaration with none text`() {
        // given
        val text1 = "sampleText"
        val text2 = ""
        val declaration1: KoTextProvider =
            mockk {
                every { text } returns text1
            }
        val declaration2: KoTextProvider =
            mockk {
                every { text } returns text2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextMatching(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTextMatching() returns declaration which text contains given regex`() {
        // given
        val regex = Regex("[1-9]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTextMatching(regex)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTextMatching() returns declarations which texts contains given one of regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns false
                every { hasTextMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTextMatching(regex1, regex2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextMatching(List) returns declarations which texts contains given one of regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns false
                every { hasTextMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val regexes = listOf(regex1, regex2)

        // when
        val sut = declarations.withTextMatching(regexes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTextMatching(Set) returns declarations which texts contains given one of regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns false
                every { hasTextMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val regexes = setOf(regex1, regex2)

        // when
        val sut = declarations.withTextMatching(regexes)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTextMatching() returns declaration which text not contains given regex`() {
        // given
        val regex = Regex("[1-9]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTextMatching(regex)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTextMatching() returns declaration which text not contains given regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns false
                every { hasTextMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTextMatching(regex1, regex2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextMatching(List) returns declaration which text not contains given regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns false
                every { hasTextMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val regexes = listOf(regex1, regex2)

        // when
        val sut = declarations.withoutTextMatching(regexes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTextMatching(Set) returns declaration which text not contains given regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns true
            }
        val declaration2: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns true
                every { hasTextMatching(regex2) } returns false
            }
        val declaration3: KoTextProvider =
            mockk {
                every { hasTextMatching(regex1) } returns false
                every { hasTextMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val regexes = setOf(regex1, regex2)

        // when
        val sut = declarations.withoutTextMatching(regexes)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
