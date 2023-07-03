package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForSuppressTagTest {
    @Test
    fun `hasValidSuppressTag returns true when kDoc has suppress tag`() {
        // given
        val suppressTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.suppressTag } returns suppressTag

        val sut = TagUtil

        // then
        sut.hasValidSuppressTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidSuppressTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidSuppressTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidSuppressTag returns true when verifySuppressTag is false`() {
        // given
        val suppressTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.suppressTag } returns suppressTag
        val sut = TagUtil

        // then
        sut.hasValidSuppressTag(false, kDoc) shouldBeEqualTo true
    }
}
