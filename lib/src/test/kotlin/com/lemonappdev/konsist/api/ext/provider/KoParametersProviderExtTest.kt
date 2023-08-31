package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametersProviderExtTest {
    private interface SampleTestParametersDeclaration :
        KoParametersProvider,
        KoKDocProvider

    @Test
    fun `hasValidKDocParamTags() returns false when declaration is not KoPrimaryConstructorDeclaration and KoKDocProvider`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns name1
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns name2
        }
        val declaration: KoParametersProvider = mockk {
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocParamTags() returns true when declaration has no parameters`() {
        // given
        val declaration: SampleTestParametersDeclaration = mockk {
            every { parameters } returns emptyList()
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocParamTags() returns true when declaration is KoPrimaryConstructorDeclaration and has valid param kdoc`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns name1
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns name2
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val containingClass: KoClassDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
        }
        val declaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1, parameter2)
            every { containingDeclaration } returns containingClass
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocParamTags() returns true when declaration implements KoKDocProvider and has valid param kdoc`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns name1
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns name2
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocParamTags() returns false when declaration parameters have other names than param tags`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val incorrectName = "incorrectName"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns incorrectName
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns name2
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocParamTags() returns false when declaration has fewer parameters than param tags`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns name1
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1)
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocParamTags() returns false when declaration has more parameters than param tags`() {
        // given
        val parameter: KoParameterDeclaration = mockk {
            every { name } returns "name1"
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns emptyList()
        }
        val declaration: SampleTestParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter)
        }

        // when
        val sut = declaration.hasValidKDocParamTags()

        // then
        sut shouldBeEqualTo false
    }
}
