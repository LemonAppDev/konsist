package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForSampleTagTest {
    @Test
    fun `hasValidSampleTag returns true when kDoc has sample tag`() {
        // given
        val sampleTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.sampleTags } returns listOf(sampleTag)

        val sut = TagUtil

        // then
        sut.hasValidSampleTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidSampleTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidSampleTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidSampleTag returns true when verifySampleTag is false`() {
        // given
        val sampleTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns THROWS
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.sampleTags } returns listOf(sampleTag)
        val sut = TagUtil

        // then
        sut.hasValidSampleTag(false, kDoc) shouldBeEqualTo true
    }
}
