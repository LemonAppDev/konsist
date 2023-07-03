package com.lemonappdev.konsist.core.util.fortagutil

import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagUtilForPropertyTagTest {
    @Test
    fun `hasValidPropertyTag returns true when properties list is equal to properties tags list`() {
        // given
        val propertyTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
            every { value } returns "name"
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyTags } returns listOf(propertyTag)
        val property: KoPropertyDeclaration = mockk {
            every { name } returns "name"
        }
        val sut = TagUtil

        // then
        sut.hasValidPropertyTag(true, listOf(property), kDoc) shouldBeEqualTo true
    }

    @Test
    fun `hasValidPropertyTag returns false when properties list is not equal to properties tags list`() {
        // given
        val propertyTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
            every { value } returns "name1"
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyTags } returns listOf(propertyTag)
        val property: KoPropertyDeclaration = mockk {
            every { name } returns "name2"
        }
        val sut = TagUtil

        // then
        sut.hasValidPropertyTag(true, listOf(property), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidPropertyTag returns false when properties is not empty list and kDoc doesn't have property tag`() {
        // given
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyTags } returns emptyList()
        val property: KoPropertyDeclaration = mockk {
            every { name } returns "name"
        }
        val sut = TagUtil

        // then
        sut.hasValidPropertyTag(true, listOf(property), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidPropertyTag returns false when properties is empty list and kDoc has property tag`() {
        // given
        val propertyTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
            every { value } returns "value"
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyTags } returns listOf(propertyTag)
        val sut = TagUtil

        // then
        sut.hasValidPropertyTag(true, emptyList(), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidPropertyTag returns false when kDoc is null`() {
        // given
        val kDoc = null
        val property: KoPropertyDeclaration = mockk {
            every { name } returns "name"
        }
        val sut = TagUtil

        // then
        sut.hasValidPropertyTag(true, listOf(property), kDoc) shouldBeEqualTo false
    }

    @Test
    fun `hasValidPropertyTag returns true when verifyPropertyTag is false`() {
        // given
        val propertyTag: KoValuedKDocTagDeclaration = mockk {
            every { name } returns PARAM
        }
        val kDoc: KoKDocDeclaration = mockk()
        every { kDoc.propertyTags } returns listOf(propertyTag)
        val property: KoPropertyDeclaration = mockk()
        val sut = TagUtil

        // then
        sut.hasValidPropertyTag(false, listOf(property), kDoc) shouldBeEqualTo true
    }
}
