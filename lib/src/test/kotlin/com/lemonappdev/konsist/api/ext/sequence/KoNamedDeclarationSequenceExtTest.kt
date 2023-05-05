package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoNamedDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationSequenceExtTest {
    @Test
    fun `withName() returns namedDeclarations with one of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { name } returns name2
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { name } returns name3
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutName() returns namedDeclaration without any of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { name } returns name2
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { name } returns name3
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNamePrefix() returns namedDeclarations which names starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameWithPrefix(prefix1) } returns true
            every { hasNameWithPrefix(prefix2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameWithPrefix(prefix1) } returns true
            every { hasNameWithPrefix(prefix2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { hasNameWithPrefix(prefix1) } returns false
            every { hasNameWithPrefix(prefix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNamePrefix(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNamePrefix() returns namedDeclaration which name not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameWithPrefix(prefix1) } returns true
            every { hasNameWithPrefix(prefix2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameWithPrefix(prefix1) } returns true
            every { hasNameWithPrefix(prefix2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { hasNameWithPrefix(prefix1) } returns false
            every { hasNameWithPrefix(prefix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNamePrefix(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNameSuffix() returns namedDeclarations which names ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameWithSuffix(suffix1) } returns true
            every { hasNameWithSuffix(suffix2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameWithSuffix(suffix1) } returns true
            every { hasNameWithSuffix(suffix2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { hasNameWithSuffix(suffix1) } returns false
            every { hasNameWithSuffix(suffix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNameSuffix(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNameSuffix() returns namedDeclaration which name not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameWithSuffix(suffix1) } returns true
            every { hasNameWithSuffix(suffix2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameWithSuffix(suffix1) } returns true
            every { hasNameWithSuffix(suffix2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { hasNameWithSuffix(suffix1) } returns false
            every { hasNameWithSuffix(suffix2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNameSuffix(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }

    @Test
    fun `withNameContaining() returns namedDeclarations which names contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
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
    fun `withoutNameContaining() returns namedDeclaration which name not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
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
    fun `withNameMatching() returns namedDeclarations which names contains given one of texts`() {
        // given
        val text1 = Regex("[1-9]")
        val text2 = Regex("[a-z]")
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { hasNameMatching(text1) } returns false
            every { hasNameMatching(text2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withNameMatching(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1, namedDeclaration2)
    }

    @Test
    fun `withoutNameMatching() returns namedDeclaration which name not contains given texts`() {
        // given
        val text1 = Regex("[1-9]")
        val text2 = Regex("[a-z]")
        val namedDeclaration1: KoNamedDeclarationImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns true
        }
        val namedDeclaration2: KoNamedDeclarationImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns false
        }
        val namedDeclaration3: KoNamedDeclarationImpl = mockk {
            every { hasNameMatching(text1) } returns false
            every { hasNameMatching(text2) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2, namedDeclaration3)

        // when
        val sut = namedDeclarations.withoutNameMatching(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration3)
    }
}
