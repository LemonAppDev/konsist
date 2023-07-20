package com.lemonappdev.konsist.api.ext.sequence.konameddeclaration

import com.lemonappdev.konsist.api.ext.sequence.withName
import com.lemonappdev.konsist.api.ext.sequence.withNameContaining
import com.lemonappdev.konsist.api.ext.sequence.withNameEndingWith
import com.lemonappdev.konsist.api.ext.sequence.withNameMatching
import com.lemonappdev.konsist.api.ext.sequence.withNameStartingWith
import com.lemonappdev.konsist.api.ext.sequence.withoutName
import com.lemonappdev.konsist.api.ext.sequence.withoutNameContaining
import com.lemonappdev.konsist.api.ext.sequence.withoutNameEndingWith
import com.lemonappdev.konsist.api.ext.sequence.withoutNameMatching
import com.lemonappdev.konsist.api.ext.sequence.withoutNameStartingWith
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationForNameSequenceExtTest {
    @Test
    fun `withName() returns namedDeclaration with given name`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withName(name1)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withName() returns namedDeclarations with one of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { name } returns name2
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { name } returns name3
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutName() returns namedDeclaration without given name`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutName(name1)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withoutName() returns namedDeclaration without any of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { name } returns name2
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { name } returns name3
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNameStartingWith() returns namedDeclaration which names starts with given prefix`() {
        // given
        val prefix = "prefix"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameStartingWith(prefix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withNameStartingWith() returns namedDeclarations which names starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix1) } returns false
            every { hasNameStartingWith(prefix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNameStartingWith(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNameStartingWith() returns namedDeclaration which name not starts with given prefix`() {
        // given
        val prefix = "prefix"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameStartingWith(prefix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withoutNameStartingWith() returns namedDeclaration which name not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameStartingWith(prefix1) } returns false
            every { hasNameStartingWith(prefix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNameStartingWith(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNameEndingWith() returns namedDeclaration which names ends with given suffix`() {
        // given
        val suffix = "suffix"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameEndingWith(suffix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withNameEndingWith() returns namedDeclarations which names ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix1) } returns false
            every { hasNameEndingWith(suffix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNameEndingWith(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNameEndingWith() returns namedDeclaration which name not ends with given suffix`() {
        // given
        val suffix = "suffix"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameEndingWith(suffix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withoutNameEndingWith() returns namedDeclaration which name not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameEndingWith(suffix1) } returns false
            every { hasNameEndingWith(suffix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNameEndingWith(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNameContaining() returns namedDeclaration which names contains given text`() {
        // given
        val text = "text"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameContaining(text) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameContaining(text) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameContaining(text)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withNameContaining() returns namedDeclarations which names contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameContaining(text1) } returns false
            every { hasNameContaining(text2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNameContaining(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNameContaining() returns namedDeclaration which name not contains given text`() {
        // given
        val text = "text"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameContaining(text) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameContaining(text) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameContaining(text)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withoutNameContaining() returns namedDeclaration which name not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameContaining(text1) } returns false
            every { hasNameContaining(text2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNameContaining(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNameMatching() returns namedDeclaration which name contains given regex`() {
        // given
        val regex = Regex("[1-9]")
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameMatching(regex) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameMatching(regex) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameMatching(regex)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withNameMatching() returns namedDeclarations which names contains given one of regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameMatching(regex1) } returns true
            every { hasNameMatching(regex2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameMatching(regex1) } returns true
            every { hasNameMatching(regex2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameMatching(regex1) } returns false
            every { hasNameMatching(regex2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNameMatching(regex1, regex2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNameMatching() returns namedDeclaration which name not contains given regex`() {
        // given
        val regex = Regex("[1-9]")
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameMatching(regex) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameMatching(regex) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameMatching(regex)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withoutNameMatching() returns namedDeclaration which name not contains given regexes`() {
        // given
        val regex1 = Regex("[1-9]")
        val regex2 = Regex("[a-z]")
        val namedDeclaration1: KoNameProviderCore = mockk {
            every { hasNameMatching(regex1) } returns true
            every { hasNameMatching(regex2) } returns true
        }
        val namedDeclaration2: KoNameProviderCore = mockk {
            every { hasNameMatching(regex1) } returns true
            every { hasNameMatching(regex2) } returns false
        }
        val namedDeclaration3: KoNameProviderCore = mockk {
            every { hasNameMatching(regex1) } returns false
            every { hasNameMatching(regex2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNameMatching(regex1, regex2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }
}
