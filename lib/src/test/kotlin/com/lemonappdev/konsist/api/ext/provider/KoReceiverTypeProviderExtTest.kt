package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReceiverTypeProviderExtTest {
    private interface SampleTestReceiverTypeDeclaration :
        KoReceiverTypeProvider,
        KoKDocProvider

    @Test
    fun `hasValidKDocReceiverTag() returns false when declaration not implement KoKDocProvider`() {
        // given
        val declaration: KoReceiverTypeProvider = mockk {
            every { receiverType } returns mockk()
        }

        // when
        val sut = declaration.hasValidKDocReceiverTag()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocReceiverTag() returns true when declaration has no receiver`() {
        // given
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
            every { receiverType } returns null
        }

        // when
        val sut = declaration.hasValidKDocReceiverTag()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocReceiverTag() calls hasTags method`() {
        // given
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns true
        }

        // when
        declaration.hasValidKDocReceiverTag()

        // then
        verify { declaration.kDoc?.hasTags(KoKDocTag.RECEIVER) }
    }

    @Test
    fun `hasValidKDocReceiverTag() returns true when declaration has valid receiver type kdoc`() {
        // given
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns true
        }

        // when
        val sut = declaration.hasValidKDocReceiverTag()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocReceiverTag() returns false when declaration has no valid receiver type kdoc`() {
        // given
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns false
        }

        // when
        val sut = declaration.hasValidKDocReceiverTag()

        // then
        sut shouldBeEqualTo false
    }
}
