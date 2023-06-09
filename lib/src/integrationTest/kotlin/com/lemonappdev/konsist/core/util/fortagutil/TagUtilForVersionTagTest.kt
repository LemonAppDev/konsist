package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForVersionTagTest {
    @Test
    fun `hasValidVersionTag returns true when kDoc has version tag`() {
        // given
        val versionTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.versionTag } returns versionTag

        val sut = TagUtil

        // then
        sut.hasValidVersionTag(true, kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidVersionTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val sut = TagUtil

        // then
        sut.hasValidVersionTag(true, kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidVersionTag returns true when verifyVersionTag is false`() {
        // given
        val versionTag: KoKDocTagDeclaration = mockk {
            every { name } returns RETURN
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.versionTag } returns versionTag
        val sut = TagUtil

        // then
        sut.hasValidVersionTag(false, kDoc) shouldBeEqualTo true
    }
}
