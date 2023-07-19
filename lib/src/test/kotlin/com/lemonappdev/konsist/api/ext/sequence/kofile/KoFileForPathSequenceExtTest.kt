package com.lemonappdev.konsist.api.ext.sequence.kofile

import com.lemonappdev.konsist.api.ext.sequence.withPath
import com.lemonappdev.konsist.api.ext.sequence.withProjectPath
import com.lemonappdev.konsist.api.ext.sequence.withoutPath
import com.lemonappdev.konsist.api.ext.sequence.withoutProjectPath
import com.lemonappdev.konsist.core.container.KoFileImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileForPathSequenceExtTest {
    @Test
    fun `withPath(String) returns file with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val file1: KoFileImpl = mockk {
            every { resideInPath(path, absolutePath = true) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(path, absolutePath = true) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withPath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withPath(String) returns files with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val file1: KoFileImpl = mockk {
            every { resideInPath(path1, absolutePath = true) } returns true
            every { resideInPath(path2, absolutePath = true) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(path1, absolutePath = true) } returns false
            every { resideInPath(path2, absolutePath = true) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { resideInPath(path1, absolutePath = true) } returns false
            every { resideInPath(path2, absolutePath = true) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withPath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutPath(String) returns file without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val file1: KoFileImpl = mockk {
            every { resideInPath(path, absolutePath = true) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(path, absolutePath = true) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutPath(path)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutPath(String) returns file without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val file1: KoFileImpl = mockk {
            every { resideInPath(path1, absolutePath = true) } returns true
            every { resideInPath(path2, absolutePath = true) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(path1, absolutePath = true) } returns false
            every { resideInPath(path2, absolutePath = true) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { resideInPath(path1, absolutePath = true) } returns false
            every { resideInPath(path2, absolutePath = true) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutPath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withProjectPath(String) returns file with given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val file1: KoFileImpl = mockk {
            every { resideInPath(projectPath, absolutePath = false) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(projectPath, absolutePath = false) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withProjectPath(projectPath)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withProjectPath(String) returns files with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val file1: KoFileImpl = mockk {
            every { resideInPath(projectPath1, absolutePath = false) } returns true
            every { resideInPath(projectPath2, absolutePath = false) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(projectPath1, absolutePath = false) } returns false
            every { resideInPath(projectPath2, absolutePath = false) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { resideInPath(projectPath1, absolutePath = false) } returns false
            every { resideInPath(projectPath2, absolutePath = false) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withProjectPath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutProjectPath(String) returns file without given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val file1: KoFileImpl = mockk {
            every { resideInPath(projectPath, absolutePath = false) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(projectPath, absolutePath = false) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutProjectPath(projectPath)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutProjectPath(String) returns file without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val file1: KoFileImpl = mockk {
            every { resideInPath(projectPath1, absolutePath = false) } returns true
            every { resideInPath(projectPath2, absolutePath = false) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(projectPath1, absolutePath = false) } returns false
            every { resideInPath(projectPath2, absolutePath = false) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { resideInPath(projectPath1, absolutePath = false) } returns false
            every { resideInPath(projectPath2, absolutePath = false) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutProjectPath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }
}
