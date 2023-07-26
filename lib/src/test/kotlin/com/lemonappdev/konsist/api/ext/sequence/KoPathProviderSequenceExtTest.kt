package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPathProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPathProviderSequenceExtTest {
    @Test
    fun `withFilePath() returns declaration with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFilePath() returns declaration without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withFilePath() with absolute path 'true' returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withFilePath(path1, path2, absolutePath = true)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutFilePath() with absolute path 'true' returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutFilePath(path1, path2, absolutePath = true)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withFilePath(String) with absolute path 'false' returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withFilePath(projectPath1, projectPath2, absolutePath = false)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutFilePath(String) with absolute path 'false' returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutFilePath(projectPath1, projectPath2, absolutePath = false)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAbsoluteFilePath(String) returns declaration with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAbsoluteFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAbsoluteFilePath(String) returns declaration without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path, true) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAbsoluteFilePath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAbsoluteFilePath(String) returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAbsoluteFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutAbsoluteFilePath(String) returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns true
            every { resideInPath(path2, true) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(path1, true) } returns false
            every { resideInPath(path2, true) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAbsoluteFilePath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withProjectFilePath(String) returns declaration with given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withProjectFilePath(projectPath)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProjectFilePath(String) returns declaration without given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath, false) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProjectFilePath(projectPath)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProjectFilePath(String) returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutProjectFilePath(String) returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns true
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration2: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns true
        }
        val declaration3: KoPathProvider = mockk {
            every { resideInPath(projectPath1, false) } returns false
            every { resideInPath(projectPath2, false) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutProjectFilePath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
