package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForPropertySetterTagTest {
    @Test
    fun `hasValidPropertySetterTag returns true when kDoc has propertySetter tag`() {
        // given
        val propertySetterTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertySetterTag } returns propertySetterTag

        val sut = TagUtil

        // then
        sut.hasValidPropertySetterTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidPropertySetterTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidPropertySetterTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidPropertySetterTag returns true when verifyPropertySetterTag is false`() {
        // given
        val propertySetterTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertySetterTag } returns propertySetterTag
        val sut = TagUtil

        // then
        sut.hasValidPropertySetterTag(false, kDoc) shouldBeEqualTo true
    }
}
