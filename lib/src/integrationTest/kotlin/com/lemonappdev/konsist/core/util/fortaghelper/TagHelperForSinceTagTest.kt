package com.lemonappdev.konsist.core.util.fortaghelper

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagHelperForSinceTagTest {
    @Test
    fun `hasValidSinceTag returns true when kDoc has since tag`() {
        // given
        val sinceTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.sinceTag } returns sinceTag

        val sut = TagHelper

        // then
        sut.hasValidSinceTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidSinceTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagHelper

        // then
        sut.hasValidSinceTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidSinceTag returns true when verifySinceTag is false`() {
        // given
        val sinceTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.sinceTag } returns sinceTag
        val sut = TagHelper

        // then
        sut.hasValidSinceTag(false, kDoc) shouldBeEqualTo true
    }
}
