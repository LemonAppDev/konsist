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
            every { resideInFilePath(path1) } returns true
            every { resideInFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns false
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
            every { resideInFilePath(path1) } returns true
            every { resideInFilePath(path2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withoutFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration3)
    }

    @Test
    fun `withProjectFilePath(String) returns baseDeclarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { resideInProjectFilePath(projectPath1) } returns true
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1, baseDeclaration2)
    }

    @Test
    fun `withoutProjectFilePath(String) returns baseDeclaration3 without given project path`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { resideInProjectFilePath(projectPath1) } returns true
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withoutProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration3)
    }
}
