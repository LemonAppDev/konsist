package com.lemonappdev.konsist.core.util.fortaghelper

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagHelperForDescriptionTest {
    @Test
    fun `hasValidDescription returns true when kDoc has valid description`() {
        // given
        val kDoc: KoKDocDeclaration = mockk {
            every { description } returns "description"
        }
        val sut = TagHelper

        // then
        sut.hasValidDescription(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidDescription returns false when kDoc hasn't valid description`() {
        // given
        val kDoc: KoKDocDeclaration = mockk {
            every { description } returns ""
        }
        val sut = TagHelper

        // then
        sut.hasValidDescription(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidDescription returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagHelper

        // then
        sut.hasValidDescription(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidDescription returns true when verifyDescription is false`() {
        // given
        val kDoc: KoKDocDeclaration = mockk {
            every { description } returns "description"
        }
        val sut = TagHelper

        // then
        sut.hasValidDescription(false, kDoc) shouldBeEqualTo true
    }
}
