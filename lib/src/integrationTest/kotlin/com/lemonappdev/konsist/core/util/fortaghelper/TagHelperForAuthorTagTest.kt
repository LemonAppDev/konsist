package com.lemonappdev.konsist.core.util.fortaghelper

import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagHelperForAuthorTagTest {
    @Test
    fun `hasValidAuthorTag returns true when kDoc has author tag`() {
        // given
        val authorTag: KoKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.authorTags } returns listOf(authorTag)

        val sut = TagHelper

        // then
        sut.hasValidAuthorTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidAuthorTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagHelper

        // then
        sut.hasValidAuthorTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidAuthorTag returns true when verifyAuthorTag is false`() {
        // given
        val authorTag: KoKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.authorTags } returns listOf(authorTag)
        val sut = TagHelper

        // then
        sut.hasValidAuthorTag(false, kDoc) shouldBeEqualTo true
    }
}
