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
    private interface SampleTestDeclaration : KoReceiverTypeProvider, KoKDocProvider

    @Test
    fun `hasValidReceiverTypeKDoc() returns true when declaration has no receiver`() {
        // given
        val declaration: SampleTestDeclaration = mockk {
            every { receiverType } returns null
        }

        // when
        val sut = declaration.hasValidReceiverTypeKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidReceiverTypeKDoc() calls hasTags method`() {
        // given
        val declaration: SampleTestDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns true
        }

        // when
        declaration.hasValidReceiverTypeKDoc()

        // then
        verify { declaration.kDoc?.hasTags(KoKDocTag.RECEIVER) }
    }

    @Test
    fun `hasValidReceiverTypeKDoc() returns true when declaration has valid receiver type kdoc`() {
        // given
        val declaration: SampleTestDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns true
        }

        // when
        val sut = declaration.hasValidReceiverTypeKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidReceiverTypeKDoc() returns false when declaration has no valid receiver type kdoc`() {
        // given
        val declaration: SampleTestDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns false
        }

        // when
        val sut = declaration.hasValidReceiverTypeKDoc()

        // then
        sut shouldBeEqualTo false
    }
}
