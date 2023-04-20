package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoNamedDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationSequenceExtTest {
    @Test
    fun `withName() returns namedDeclaration1 with given name`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withName(name1)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withoutName() returns namedDeclaration2 without given name`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutName(name1)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withNamePrefix() returns namedDeclaration1 which name starts with given prefix`() {
        // given
        val prefix = "prefix"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { hasNameWithPrefix(prefix) } returns true
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { hasNameWithPrefix(prefix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNamePrefix(prefix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withoutNamePrefix() returns namedDeclaration2 which name not starts with given prefix`() {
        // given
        val prefix = "prefix"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { hasNameWithPrefix(prefix) } returns true
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { hasNameWithPrefix(prefix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNamePrefix(prefix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withNameSuffix() returns namedDeclaration1 which name ends with given suffix`() {
        // given

        val suffix = "suffix"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { hasNameWithSuffix(suffix) } returns true
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { hasNameWithSuffix(suffix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameSuffix(suffix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withoutNameSuffix() returns namedDeclaration2 which name not ends with given suffix`() {
        // given
        val suffix = "suffix"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { hasNameWithSuffix(suffix) } returns true
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { hasNameWithSuffix(suffix) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameSuffix(suffix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withNameContaining() returns namedDeclaration1 which name containing given text`() {
        // given
        val text = "text"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { hasNameContaining(text) } returns true
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { hasNameContaining(text) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameContaining(text)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withoutNameContaining() returns namedDeclaration2 which name not containing given text`() {
        // given
        val text = "text"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { hasNameContaining(text) } returns true
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { hasNameContaining(text) } returns false
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameContaining(text)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }
}
