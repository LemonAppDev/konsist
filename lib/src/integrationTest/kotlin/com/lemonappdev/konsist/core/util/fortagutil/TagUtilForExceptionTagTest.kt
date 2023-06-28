package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForExceptionTagTest {
    @Test
    fun `hasValidExceptionTag returns true when kDoc has exception tag`() {
        // given
        val exceptionTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.exceptionTags } returns listOf(exceptionTag)

        val sut = TagUtil

        // then
        sut.hasValidExceptionTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidExceptionTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidExceptionTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidExceptionTag returns true when verifyExceptionTag is false`() {
        // given
        val exceptionTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.exceptionTags } returns listOf(exceptionTag)
        val sut = TagUtil

        // then
        sut.hasValidExceptionTag(false, kDoc) shouldBeEqualTo true
    }
}
