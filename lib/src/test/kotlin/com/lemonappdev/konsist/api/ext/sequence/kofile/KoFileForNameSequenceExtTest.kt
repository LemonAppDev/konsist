package com.lemonappdev.konsist.api.ext.sequence.kofile

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
import com.lemonappdev.konsist.core.container.KoFileImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileForNameSequenceExtTest {
    @Test
    fun `withName() returns file with one of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val file1: KoFileImpl = mockk {
            every { name } returns name1
        }
        val file2: KoFileImpl = mockk {
            every { name } returns name2
        }
        val file3: KoFileImpl = mockk {
            every { name } returns name3
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutName() returns file without any of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val file1: KoFileImpl = mockk {
            every { name } returns name1
        }
        val file2: KoFileImpl = mockk {
            every { name } returns name2
        }
        val file3: KoFileImpl = mockk {
            every { name } returns name3
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameStartingWith() returns file which names starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val file1: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns false
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameStartingWith(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameStartingWith() returns file which name not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val file1: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns false
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameStartingWith(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameEndingWith() returns file which names ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val file1: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns false
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameEndingWith(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameEndingWith() returns file which name not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val file1: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns false
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameEndingWith(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameContaining() returns file which names contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val file1: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns false
            every { hasNameContaining(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameContaining(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameContaining() returns file which name not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val file1: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns false
            every { hasNameContaining(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameContaining(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameMatching() returns file which names contains given one of texts`() {
        // given
        val text1 = Regex("[1-9]")
        val text2 = Regex("[a-z]")
        val file1: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns false
            every { hasNameMatching(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameMatching(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameMatching() returns file which name not contains given texts`() {
        // given
        val text1 = Regex("[1-9]")
        val text2 = Regex("[a-z]")
        val file1: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns false
            every { hasNameMatching(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameMatching(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }
}
