package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForPropertyGetterTagTest {
    @Test
    fun `hasValidPropertyGetterTag returns true when kDoc has propertyGetter tag`() {
        // given
        val propertyGetterTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyGetterTag } returns propertyGetterTag

        val sut = TagUtil

        // then
        sut.hasValidPropertyGetterTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidPropertyGetterTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidPropertyGetterTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidPropertyGetterTag returns true when verifyPropertyGetterTag is false`() {
        // given
        val propertyGetterTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyGetterTag } returns propertyGetterTag
        val sut = TagUtil

        // then
        sut.hasValidPropertyGetterTag(false, kDoc) shouldBeEqualTo true
    }
}
