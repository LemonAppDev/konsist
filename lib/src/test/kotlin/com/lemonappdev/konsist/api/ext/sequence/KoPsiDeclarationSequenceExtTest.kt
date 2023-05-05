package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoPsiDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationSequenceExtTest {
    @Test
    fun `withFilePath(String) returns psiDeclarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1) } returns true
            every { resideInFilePath(path2) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

    @Test
    fun `withoutFilePath(String) returns psiDeclaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1) } returns true
            every { resideInFilePath(path2) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1) } returns false
            every { resideInFilePath(path2) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }

    @Test
    fun `withProjectFilePath(String) returns psiDeclarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { resideInProjectFilePath(projectPath1) } returns true
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

    @Test
    fun `withoutProjectFilePath(String) returns psiDeclaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { resideInProjectFilePath(projectPath1) } returns true
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInProjectFilePath(projectPath1) } returns false
            every { resideInProjectFilePath(projectPath2) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }
}
