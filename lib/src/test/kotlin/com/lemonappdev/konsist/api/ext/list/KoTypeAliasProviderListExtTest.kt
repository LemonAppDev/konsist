package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasProviderListExtTest {
    @Test
    fun `withTypeAlias() returns file with typealias`() {
        // given
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withTypeAliases()

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAlias() returns file without typealias`() {
        // given
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases()

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAllTypeAliases(String) returns files with all of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withAllTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAllTypeAliases(String) returns file without any of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1, typeAlias2) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutAllTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeTypeAliases(String) returns file with given typeAlias`() {
        // given
        val typeAlias = "SampleTypeAlias"
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withSomeTypeAliases(typeAlias)

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withSomeTypeAliases(String) returns files with at least one of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns true
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val file3: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withSomeTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutSomeTypeAliases(String) returns file without given typeAlias`() {
        // given
        val typeAlias = "SampleTypeAlias"
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutSomeTypeAliases(typeAlias)

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutSomeTypeAliases(String) returns files without at least one of given typeAliases`() {
        // given
        val typeAlias1 = "SampleTypeAlias1"
        val typeAlias2 = "SampleTypeAlias2"
        val file1: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns true
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val file2: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns true
        }
        val file3: KoTypeAliasProvider = mockk {
            every { hasTypeAliases(typeAlias1) } returns false
            every { hasTypeAliases(typeAlias2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withoutSomeTypeAliases(typeAlias1, typeAlias2)

        // then
        sut shouldBeEqualTo listOf(file3)
    }
}
