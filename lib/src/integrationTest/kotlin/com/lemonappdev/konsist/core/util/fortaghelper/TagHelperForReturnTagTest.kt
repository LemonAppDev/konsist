package com.lemonappdev.konsist.core.util.fortaghelper

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagHelperForReturnTagTest {
    @Test
    fun `hasValidReturnTag returns true when kDoc has return tag`() {
        // given
        val returnTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.returnTag } returns returnTag

        val sut = TagHelper

        // then
        sut.hasValidReturnTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidReturnTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagHelper

        // then
        sut.hasValidReturnTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidReturnTag returns true when verifyReturnTag is false`() {
        // given
        val returnTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.returnTag } returns returnTag
        val sut = TagHelper

        // then
        sut.hasValidReturnTag(false, kDoc) shouldBeEqualTo true
    }
}
