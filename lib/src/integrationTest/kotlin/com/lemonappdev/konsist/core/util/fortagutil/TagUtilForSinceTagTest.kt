package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForSinceTagTest {
    @Test
    fun `hasValidSinceTag returns true when kDoc has since tag`() {
        // given
        val sinceTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.sinceTag } returns sinceTag

        val sut = TagUtil

        // then
        sut.hasValidSinceTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidSinceTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

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
        val sut = TagUtil

        // then
        sut.hasValidSinceTag(false, kDoc) shouldBeEqualTo true
    }
}
