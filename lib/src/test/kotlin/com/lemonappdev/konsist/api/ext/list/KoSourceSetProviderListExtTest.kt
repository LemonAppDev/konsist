package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceSetProviderListExtTest {
    @Test
    fun `withSourceSet(String) returns file with given source set`() {
        // given
        val sourceSetName = "sourceSetName"
        val file1: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName) } returns true
        }
        val file2: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withSourceSet(sourceSetName)

        // then
        sut shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withSourceSet(String) returns files with one of given source sets`() {
        // given
        val sourceSetName1 = "sourceSetName1"
        val sourceSetName2 = "sourceSetName2"
        val file1: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName1) } returns true
            every { resideInSourceSet(sourceSetName2) } returns false
        }
        val file2: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName1) } returns false
            every { resideInSourceSet(sourceSetName2) } returns true
        }
        val file3: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName1) } returns false
            every { resideInSourceSet(sourceSetName2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withSourceSet(sourceSetName1, sourceSetName2)

        // then
        sut shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutSourceSet(String) returns file without given source set`() {
        // given
        val sourceSetName = "sourceSetName"
        val file1: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName) } returns true
        }
        val file2: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName) } returns false
        }
        val files = listOf(file1, file2)

        // when
        val sut = files.withoutSourceSet(sourceSetName)

        // then
        sut shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutSourceSet(String) returns file without any of given source sets`() {
        // given
        val sourceSetName1 = "sourceSetName1"
        val sourceSetName2 = "sourceSetName2"
        val file1: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName1) } returns true
            every { resideInSourceSet(sourceSetName2) } returns false
        }
        val file2: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName1) } returns false
            every { resideInSourceSet(sourceSetName2) } returns true
        }
        val file3: KoSourceSetProvider = mockk {
            every { resideInSourceSet(sourceSetName1) } returns false
            every { resideInSourceSet(sourceSetName2) } returns false
        }
        val files = listOf(file1, file2, file3)

        // when
        val sut = files.withoutSourceSet(sourceSetName1, sourceSetName2)

        // then
        sut shouldBeEqualTo listOf(file3)
    }
}
