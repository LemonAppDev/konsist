package com.lemonappdev.konsist.api.ext.sequence.kopackagedeclaration

import com.lemonappdev.konsist.api.ext.sequence.withMatchingFilePath
import com.lemonappdev.konsist.api.ext.sequence.withoutMatchingFilePath
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForMatchingFilePathExtTest {
    @Test
    fun `withMatchingFilePath() returns package which has matching file path`() {
        // given
        val package1: KoPackageDeclarationImpl = mockk {
            every { hasMatchingFilePath } returns true
        }
        val package2: KoPackageDeclarationImpl = mockk {
            every { hasMatchingFilePath } returns false
        }
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withMatchingFilePath()

        // then
        sut.toList() shouldBeEqualTo listOf(package1)
    }

    @Test
    fun `withoutMatchingFilePath() returns package which has not matching file path`() {
        // given
        val package1: KoPackageDeclarationImpl = mockk {
            every { hasMatchingFilePath } returns true
        }
        val package2: KoPackageDeclarationImpl = mockk {
            every { hasMatchingFilePath } returns false
        }
        val packages = sequenceOf(package1, package2)

        // when
        val sut = packages.withoutMatchingFilePath()

        // then
        sut.toList() shouldBeEqualTo listOf(package2)
    }
}
