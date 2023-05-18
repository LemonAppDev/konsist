package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPsiDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationSequenceExtTest {
    @Test
    fun `withKDoc() returns psiDeclaration with any kDoc`() {
        // given
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { hasKDoc() } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { hasKDoc() } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDoc()

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutKDoc() returns psiDeclaration without any kDoc`() {
        // given
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { hasKDoc() } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { hasKDoc() } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDoc()

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withCompleteKDoc() returns psiDeclaration with complete kDoc`() {
        // given
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withCompleteKDoc(verifyDescription = true, verifyParamTag = true)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutCompleteKDoc() returns psiDeclaration without complete kDoc`() {
        // given
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns true
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { hasValidKDoc(verifyDescription = true, verifyParamTag = true) } returns false
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutCompleteKDoc(verifyDescription = true, verifyParamTag = true)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withKDocWithTags(String) returns psiDeclaration with all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns true
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns false
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withKDocWithTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1)
    }

    @Test
    fun `withoutKDocWithTags(String) returns psiDeclaration without any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns true
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1, tag2) } returns false
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2)

        // when
        val sut = psiDeclarations.withoutKDocWithTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration2)
    }

    @Test
    fun `withSomeKDocWithTags(String) returns psiDeclarations with at least one of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns true
            every { hasTags(tag2) } returns true
        }
        val psiDeclaration1: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc1
        }
        val kDoc2: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns true
            every { hasTags(tag2) } returns false
        }
        val psiDeclaration2: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc2
        }
        val kDoc3: KoKDocDeclarationImpl = mockk {
            every { hasTags(tag1) } returns false
            every { hasTags(tag2) } returns false
        }
        val psiDeclaration3: KoPsiDeclarationImpl = mockk {
            every { kDoc } returns kDoc3
        }
        val psiDeclarations = sequenceOf(psiDeclaration1, psiDeclaration2, psiDeclaration3)

        // when
        val sut = psiDeclarations.withSomeKDocWithTags(tag1, tag2)

        // then
        sut.toList() shouldBeEqualTo listOf(psiDeclaration1, psiDeclaration2)
    }

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
