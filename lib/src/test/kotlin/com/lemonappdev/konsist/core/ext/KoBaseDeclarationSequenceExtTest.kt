package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationSequenceExtTest {
    @Test
    fun `withFilePath(String) returns baseDeclarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns true
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1, baseDeclaration2)
    }

    @Test
    fun `withoutFilePath(String) returns baseDeclaration3 without given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns true
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasFilePath(path1) } returns false
            every { hasFilePath(path2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withoutFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration3)
    }
}
