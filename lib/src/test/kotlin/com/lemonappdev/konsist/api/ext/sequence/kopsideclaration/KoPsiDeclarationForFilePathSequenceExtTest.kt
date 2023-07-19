package com.lemonappdev.konsist.api.ext.sequence.kopsideclaration

import com.lemonappdev.konsist.api.ext.sequence.withAbsoluteFilePath
import com.lemonappdev.konsist.api.ext.sequence.withFilePath
import com.lemonappdev.konsist.api.ext.sequence.withProjectFilePath
import com.lemonappdev.konsist.api.ext.sequence.withoutAbsoluteFilePath
import com.lemonappdev.konsist.api.ext.sequence.withoutFilePath
import com.lemonappdev.konsist.api.ext.sequence.withoutProjectFilePath
import com.lemonappdev.konsist.api.provider.KoPathProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForFilePathSequenceExtTest {
    @Test
    fun `withFilePath() returns psiDeclaration with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutFilePath() returns psiDeclaration without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withFilePath() with absolute path 'true' returns psiDeclarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withFilePath(path1, path2, absolutePath = true)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

    @Test
    fun `withoutFilePath() with absolute path 'true' returns psiDeclaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutFilePath(path1, path2, absolutePath = true)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }

    @Test
    fun `withFilePath(String) with absolute path 'false' returns psiDeclarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withFilePath(projectPath1, projectPath2, absolutePath = false)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

    @Test
    fun `withoutFilePath(String) with absolute path 'false' returns psiDeclaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutFilePath(projectPath1, projectPath2, absolutePath = false)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }

    @Test
    fun `withAbsoluteFilePath(String) returns psiDeclaration with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withAbsoluteFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutAbsoluteFilePath(String) returns psiDeclaration without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutAbsoluteFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withAbsoluteFilePath(String) returns psiDeclarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withAbsoluteFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

    @Test
    fun `withoutAbsoluteFilePath(String) returns psiDeclaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutAbsoluteFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }

    @Test
    fun `withProjectFilePath(String) returns psiDeclaration with given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withProjectFilePath(projectPath)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutProjectFilePath(String) returns psiDeclaration without given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutProjectFilePath(projectPath)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withProjectFilePath(String) returns psiDeclarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
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
        val psiDeclaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val psiDeclaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withoutProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration3)
    }
}
