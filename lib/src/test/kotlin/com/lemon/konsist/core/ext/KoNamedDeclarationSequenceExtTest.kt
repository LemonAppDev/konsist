package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoNamedDeclaration
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
    fun `withNameWithPrefix() returns namedDeclaration1 which name starts with given prefix`() {
        // given
        val name1 = "sampleName1"
        val name2 = "notSampleName2"
        val prefix = "sample"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameWithPrefix(prefix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withoutNameWithPrefix() returns namedDeclaration2 which name not starts with given prefix`() {
        // given
        val name1 = "sampleName1"
        val name2 = "notSampleName2"
        val prefix = "sample"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameWithPrefix(prefix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withNameWithSuffix() returns namedDeclaration1 which name ends with given suffix`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val suffix = "Name1"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withNameWithSuffix(suffix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration1)
    }

    @Test
    fun `withoutNameWithSuffix() returns namedDeclaration2 which name not ends with given suffix`() {
        // given
        val name1 = "sampleName1"
        val name2 = "notSampleName2"
        val suffix = "Name1"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameWithSuffix(suffix)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }

    @Test
    fun `withNameContaining() returns namedDeclaration1 which name containing given text`() {
        // given
        val name1 = "sampleFirstName"
        val name2 = "sampleSecondName"
        val text = "First"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
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
        val name1 = "sampleFirstName"
        val name2 = "sampleSecondName"
        val text = "First"
        val namedDeclaration1: KoNamedDeclaration = mockk {
            every { name } returns name1
        }
        val namedDeclaration2: KoNamedDeclaration = mockk {
            every { name } returns name2
        }
        val namedDeclarations = sequenceOf(namedDeclaration1, namedDeclaration2)

        // when
        val sut = namedDeclarations.withoutNameContaining(text)

        // then
        sut.toList() shouldBeEqualTo listOf(namedDeclaration2)
    }
}
