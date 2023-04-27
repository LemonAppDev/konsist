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

    @Test
    fun `withParentDeclaration() returns baseDeclaration with any parent declaration`() {
        // given
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasParentDeclaration() } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasParentDeclaration() } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2)

        // when
        val sut = baseDeclarations.withParentDeclaration()

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1)
    }

    @Test
    fun `withoutParentDeclaration() returns baseDeclaration without parent declaration`() {
        // given
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasParentDeclaration() } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasParentDeclaration() } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2)

        // when
        val sut = baseDeclarations.withoutParentDeclaration()

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration2)
    }

    @Test
    fun `withParentDeclaration() returns baseDeclarations with one of given names`() {
        // given
        val name1 = "parentName1"
        val name2 = "parentName2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasParentDeclaration(name1) } returns true
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withParentDeclaration(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration1, baseDeclaration2)
    }

    @Test
    fun `withoutParentDeclaration() returns baseDeclaration without any of given names`() {
        // given
        val name1 = "parentName1"
        val name2 = "parentName2"
        val baseDeclaration1: KoBaseDeclaration = mockk {
            every { hasParentDeclaration(name1) } returns true
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration2: KoBaseDeclaration = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns true
        }
        val baseDeclaration3: KoBaseDeclaration = mockk {
            every { hasParentDeclaration(name1) } returns false
            every { hasParentDeclaration(name2) } returns false
        }
        val baseDeclarations = sequenceOf(baseDeclaration1, baseDeclaration2, baseDeclaration3)

        // when
        val sut = baseDeclarations.withoutParentDeclaration(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(baseDeclaration3)
    }
}
