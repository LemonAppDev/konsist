package com.lemonappdev.konsist.api.ext.sequence.kofile

import com.lemonappdev.konsist.api.ext.sequence.withSomeTypeAliases
import com.lemonappdev.konsist.api.ext.sequence.withTypeAliases
import com.lemonappdev.konsist.api.ext.sequence.withoutTypeAliases
import com.lemonappdev.konsist.core.container.KoFileImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileForTypeAliasSequenceExtTest {
    @Test
    fun `withTypeAlias() returns file with typealias`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAlias() returns file without typealias`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns files with all of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases(typeAlias1, typeAlias2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file without any of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases(typeAlias1, typeAlias2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeTypeAliases(String) returns file with given typeAlias`() {
        // given
        val typeAlias = "SampleTypeAlias"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withSomeTypeAliases(typeAlias)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withSomeTypeAliases(String) returns files with at least one of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1) } returns true
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeTypeAliases(typeAlias1, typeAlias2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }
}
