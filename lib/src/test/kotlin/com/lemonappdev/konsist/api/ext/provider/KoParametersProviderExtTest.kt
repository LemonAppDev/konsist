package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametersProviderExtTest {
    private interface SampleTestDeclaration: KoParametersProvider, KoKDocProvider

    @Test
    fun `hasValidParameterKDoc() returns true when declaration has no parameters`() {
        // given
        val declaration: SampleTestDeclaration = mockk {
            every { parameters } returns emptyList()
        }

        // when
        val sut = declaration.hasValidParameterKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidParameterKDoc() returns true when declaration has valid param kdoc`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value1"
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value2"
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns "value1"
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns "value2"
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidParameterKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidParameterKDoc() returns false when declaration parameters have other names than param tags`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value1"
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value2"
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns "name1"
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns "value2"
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidParameterKDoc() returns false when declaration has fewer parameters than param tags`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value1"
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value2"
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns "name1"
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1)
        }

        // when
        val sut = declaration.hasValidParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidParameterKDoc() returns false when declaration has more parameters than param tags`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value1"
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns "name1"
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns "name2"
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1)
        }
        val declaration: SampleTestDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }
}
