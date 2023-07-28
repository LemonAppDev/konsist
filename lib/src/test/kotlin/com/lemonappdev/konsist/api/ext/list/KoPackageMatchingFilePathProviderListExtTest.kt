package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageMatchingFilePathProviderListExtTest {
    @Test
    fun `withMatchingFilePath() returns declaration which has matching file path`() {
        // given
        val declaration1: KoPackageMatchingFilePathProvider = mockk {
            every { hasMatchingFilePath } returns true
        }
        val declaration2: KoPackageMatchingFilePathProvider = mockk {
            every { hasMatchingFilePath } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withMatchingFilePath()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutMatchingFilePath() returns declaration which has not matching file path`() {
        // given
        val declaration1: KoPackageMatchingFilePathProvider = mockk {
            every { hasMatchingFilePath } returns true
        }
        val declaration2: KoPackageMatchingFilePathProvider = mockk {
            every { hasMatchingFilePath } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutMatchingFilePath()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
