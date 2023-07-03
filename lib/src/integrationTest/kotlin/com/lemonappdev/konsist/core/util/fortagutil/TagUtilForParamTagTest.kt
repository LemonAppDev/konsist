package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForParamTagTest {
    @Test
    fun `hasValidParamTag returns true when parameters parameters list is equal to param tags list`() {
        // given
        val paramTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
            every { value } returns "name"
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.paramTags } returns listOf(paramTag)
        val parameter: KoParameterDeclaration = mockk {
            every { name } returns "name"
        }
        val sut = TagUtil

        // then
        sut.hasValidParamTag(true, listOf(parameter), kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidParamTag returns false when parameters parameters list is not equal to param tags list`() {
        // given
        val paramTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
            every { value } returns "name1"
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.paramTags } returns listOf(paramTag)
        val parameter: KoParameterDeclaration = mockk {
            every { name } returns "name2"
        }
        val sut = TagUtil

        // then
        sut.hasValidParamTag(true, listOf(parameter), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidParamTag returns false when parameters is not empty list and kDoc doesn't have param tag`() {
        // given
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.paramTags } returns emptyList()
        val parameter: KoParameterDeclaration = mockk {
            every { name } returns "name"
        }
        val sut = TagUtil

        // then
        sut.hasValidParamTag(true, listOf(parameter), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidParamTag returns false when parameters is empty list and kDoc has param tag`() {
        // given
        val paramTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
            every { value } returns "value"
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.paramTags } returns listOf(paramTag)
        val sut = TagUtil

        // then
        sut.hasValidParamTag(true, emptyList(), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidParamTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val parameter: KoParameterDeclaration = mockk {
            every { name } returns "name"
        }
        val sut = TagUtil

        // then
        sut.hasValidParamTag(true, listOf(parameter), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidParamTag returns true when verifyParamTag is false`() {
        // given
        val paramTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.paramTags } returns listOf(paramTag)
        val parameter: KoParameterDeclaration = mockk()
        val sut = TagUtil

        // then
        sut.hasValidParamTag(false, listOf(parameter), kDoc) shouldBeEqualTo true
    }
}
