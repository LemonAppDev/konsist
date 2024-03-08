package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNameProviderListExtTest {
    @Test
    fun `withName() returns declaration with any name`() {
        // given
        val name1 = "sampleName"
        val name2 = ""
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withName()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutName() returns declaration without name`() {
        // given
        val name1 = "sampleName"
        val name2 = ""
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutName()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withName() returns declaration with given name`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withName(name1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withName() returns declarations with one of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declaration3: KoNameProvider =
            mockk {
                every { name } returns name3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withName(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutName() returns declaration without given name`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutName(name1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutName() returns declaration without any of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declaration3: KoNameProvider =
            mockk {
                every { name } returns name3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutName(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withName{predicate} returns declaration with name matching to predicate`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val prefix = "sample"
        val suffix = "1"
        val predicate: (String) -> Boolean = { it.startsWith(prefix) && it.endsWith(suffix) }
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withName(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutName{predicate} returns declaration without name matching to predicate`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val prefix = "sample"
        val suffix = "1"
        val predicate: (String) -> Boolean = { it.startsWith(prefix) && it.endsWith(suffix) }
        val declaration1: KoNameProvider =
            mockk {
                every { name } returns name1
            }
        val declaration2: KoNameProvider =
            mockk {
                every { name } returns name2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutName(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withNameStartingWith() returns declaration which names starts with given prefix`() {
        // given
        val prefix = "prefix"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNameStartingWith(prefix)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withNameStartingWith() returns declarations which names starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix1) } returns true
                every { hasNameStartingWith(prefix2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix1) } returns true
                every { hasNameStartingWith(prefix2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix1) } returns false
                every { hasNameStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withNameStartingWith(prefix1, prefix2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutNameStartingWith() returns declaration which name not starts with given prefix`() {
        // given
        val prefix = "prefix"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNameStartingWith(prefix)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutNameStartingWith() returns declaration which name not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix1) } returns true
                every { hasNameStartingWith(prefix2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix1) } returns true
                every { hasNameStartingWith(prefix2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameStartingWith(prefix1) } returns false
                every { hasNameStartingWith(prefix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutNameStartingWith(prefix1, prefix2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withNameEndingWith() returns declaration which names ends with given suffix`() {
        // given
        val suffix = "suffix"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNameEndingWith(suffix)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withNameEndingWith() returns declarations which names ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix1) } returns true
                every { hasNameEndingWith(suffix2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix1) } returns true
                every { hasNameEndingWith(suffix2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix1) } returns false
                every { hasNameEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withNameEndingWith(suffix1, suffix2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutNameEndingWith() returns declaration which name not ends with given suffix`() {
        // given
        val suffix = "suffix"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNameEndingWith(suffix)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutNameEndingWith() returns declaration which name not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix1) } returns true
                every { hasNameEndingWith(suffix2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix1) } returns true
                every { hasNameEndingWith(suffix2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameEndingWith(suffix1) } returns false
                every { hasNameEndingWith(suffix2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutNameEndingWith(suffix1, suffix2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withNameContaining() returns declaration which names contains given text`() {
        // given
        val text = "text"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameContaining(text) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameContaining(text) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNameContaining(text)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withNameContaining() returns declarations which names contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameContaining(text1) } returns true
                every { hasNameContaining(text2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameContaining(text1) } returns true
                every { hasNameContaining(text2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameContaining(text1) } returns false
                every { hasNameContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withNameContaining(text1, text2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutNameContaining() returns declaration which name not contains given text`() {
        // given
        val text = "text"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameContaining(text) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameContaining(text) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNameContaining(text)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutNameContaining() returns declaration which name not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameContaining(text1) } returns true
                every { hasNameContaining(text2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameContaining(text1) } returns true
                every { hasNameContaining(text2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameContaining(text1) } returns false
                every { hasNameContaining(text2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutNameContaining(text1, text2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withNameMatching() returns declaration which name contains given regex`() {
        // given
        val regex = Regex("[1-9]")
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameMatching(regex) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameMatching(regex) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNameMatching(regex)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withNameMatching() returns declarations which names contains given one of regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameMatching(regex1) } returns true
                every { hasNameMatching(regex2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameMatching(regex1) } returns true
                every { hasNameMatching(regex2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameMatching(regex1) } returns false
                every { hasNameMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withNameMatching(regex1, regex2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutNameMatching() returns declaration which name not contains given regex`() {
        // given
        val regex = Regex("[1-9]")
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameMatching(regex) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameMatching(regex) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNameMatching(regex)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutNameMatching() returns declaration which name not contains given regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val declaration1: KoNameProvider =
            mockk {
                every { hasNameMatching(regex1) } returns true
                every { hasNameMatching(regex2) } returns true
            }
        val declaration2: KoNameProvider =
            mockk {
                every { hasNameMatching(regex1) } returns true
                every { hasNameMatching(regex2) } returns false
            }
        val declaration3: KoNameProvider =
            mockk {
                every { hasNameMatching(regex1) } returns false
                every { hasNameMatching(regex2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutNameMatching(regex1, regex2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
