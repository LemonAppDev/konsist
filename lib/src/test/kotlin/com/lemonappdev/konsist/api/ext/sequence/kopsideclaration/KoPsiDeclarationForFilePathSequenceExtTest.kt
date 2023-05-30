package com.lemonappdev.konsist.api.ext.sequence.kopsideclaration

import com.lemonappdev.konsist.api.ext.sequence.withFilePath
import com.lemonappdev.konsist.api.ext.sequence.withProjectFilePath
import com.lemonappdev.konsist.api.ext.sequence.withoutFilePath
import com.lemonappdev.konsist.api.ext.sequence.withoutProjectFilePath
import com.lemonappdev.konsist.core.declaration.KoPsiDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForFilePathSequenceExtTest {
    @Test
    fun `withFilePath(String) returns psiDeclarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1, true) } returns true
            every { resideInFilePath(path2, true) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1, true) } returns false
            every { resideInFilePath(path2, true) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1, true) } returns false
            every { resideInFilePath(path2, true) } returns false
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
            every { resideInFilePath(path1, true) } returns true
            every { resideInFilePath(path2, true) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1, true) } returns false
            every { resideInFilePath(path2, true) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(path1, true) } returns false
            every { resideInFilePath(path2, true) } returns false
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
            every { resideInFilePath(projectPath1, false) } returns true
            every { resideInFilePath(projectPath2, false) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(projectPath1, false) } returns false
            every { resideInFilePath(projectPath2, false) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(projectPath1, false) } returns false
            every { resideInFilePath(projectPath2, false) } returns false
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
            every { resideInFilePath(projectPath1, false) } returns true
            every { resideInFilePath(projectPath2, false) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(projectPath1, false) } returns false
            every { resideInFilePath(projectPath2, false) } returns true
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { resideInFilePath(projectPath1, false) } returns false
            every { resideInFilePath(projectPath2, false) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }
}
