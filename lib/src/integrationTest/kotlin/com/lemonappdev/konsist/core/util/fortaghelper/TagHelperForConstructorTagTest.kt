package com.lemonappdev.konsist.core.util.fortaghelper

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagHelperForConstructorTagTest {
    @Test
    fun `hasValidConstructorTag returns true when kDoc has constructor tag`() {
        // given
        val constructorTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.constructorTag } returns constructorTag

        val sut = TagHelper

        // then
        sut.hasValidConstructorTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidConstructorTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagHelper

        // then
        sut.hasValidConstructorTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidConstructorTag returns true when verifyConstructorTag is false`() {
        // given
        val constructorTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.constructorTag } returns constructorTag
        val sut = TagHelper

        // then
        sut.hasValidConstructorTag(false, kDoc) shouldBeEqualTo true
    }
}
