package com.lemonappdev.konsist.core.util.fortaghelper

import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagHelperForThrowsTagTest {
    @Test
    fun `hasValidThrowsTag returns true when kDoc has throws tag`() {
        // given
        val throwsTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.throwsTags } returns listOf(throwsTag)

        val sut = TagHelper

        // then
        sut.hasValidThrowsTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidThrowsTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagHelper

        // then
        sut.hasValidThrowsTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidThrowsTag returns true when verifyThrowsTag is false`() {
        // given
        val throwsTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.throwsTags } returns listOf(throwsTag)
        val sut = TagHelper

        // then
        sut.hasValidThrowsTag(false, kDoc) shouldBeEqualTo true
    }
}
