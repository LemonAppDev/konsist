package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForSeeTagTest {
    @Test
    fun `hasValidSeeTag returns true when kDoc has see tag`() {
        // given
        val seeTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.seeTags } returns listOf(seeTag)

        val sut = TagUtil

        // then
        sut.hasValidSeeTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidSeeTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidSeeTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidSeeTag returns true when verifySeeTag is false`() {
        // given
        val seeTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.seeTags } returns listOf(seeTag)
        val sut = TagUtil

        // then
        sut.hasValidSeeTag(false, kDoc) shouldBeEqualTo true
    }
}
