package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocProviderListExtTest {
    @Test
    fun `kDocs returns kDocs from all declarations`() {
        // given
        val kDoc1: KoKDocDeclaration = mockk()
        val kDoc2: KoKDocDeclaration = mockk()
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declaration3: KoKDocProvider =
            mockk {
                every { kDoc } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kDocs

        // then
        sut shouldBeEqualTo listOf(kDoc1, kDoc2)
    }

    @Test
    fun `withKDoc() returns declaration with any kDoc`() {
        // given
        val declaration1: KoKDocProvider =
            mockk {
                every { hasKDoc } returns true
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { hasKDoc } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKDoc()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKDoc() returns declaration without any kDoc`() {
        // given
        val declaration1: KoKDocProvider =
            mockk {
                every { hasKDoc } returns true
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { hasKDoc } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKDoc()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withKDocWithTags() returns declaration with any tag`() {
        // given
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags() } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags() } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKDocWithTags()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKDocWithTags() returns declaration without any tag`() {
        // given
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags() } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags() } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKDocWithTags()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withKDocWithAllTags(String) returns declaration with given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKDocWithAllTags(tag)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKDocWithAllTags(String) returns declaration with all of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1, tag2) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1, tag2) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKDocWithAllTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKDocWithAllTags(String) returns declaration without given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKDocWithAllTags(tag)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKDocWithAllTags(String) returns declaration without any of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1, tag2) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1, tag2) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKDocWithAllTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withKDocWithSomeTags(String) returns declarations with given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKDocWithSomeTags(tag)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKDocWithSomeTags(String) returns declarations with at least one of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1) } returns true
                every { hasTags(tag2) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1) } returns true
                every { hasTags(tag2) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val kDoc3: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1) } returns false
                every { hasTags(tag2) } returns false
            }
        val declaration3: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKDocWithSomeTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutKDocWithSomeTags(String) returns declarations with given tag`() {
        // given
        val tag = KoKDocTag.SINCE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKDocWithSomeTags(tag)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKDocWithSomeTags(String) returns declarations with at least one of given tags`() {
        // given
        val tag1 = KoKDocTag.SINCE
        val tag2 = KoKDocTag.SEE
        val kDoc1: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1) } returns true
                every { hasTags(tag2) } returns true
            }
        val declaration1: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc1
            }
        val kDoc2: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1) } returns true
                every { hasTags(tag2) } returns false
            }
        val declaration2: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc2
            }
        val kDoc3: KoKDocDeclarationCore =
            mockk {
                every { hasTags(tag1) } returns false
                every { hasTags(tag2) } returns false
            }
        val declaration3: KoKDocProvider =
            mockk {
                every { kDoc } returns kDoc3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKDocWithSomeTags(tag1, tag2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
