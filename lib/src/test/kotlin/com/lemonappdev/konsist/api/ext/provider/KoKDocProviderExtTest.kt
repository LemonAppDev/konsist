package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocProviderExtTest {
    private interface SampleTestReturnTypeDeclaration :
        KoReturnTypeProvider,
        KoKDocProvider

    private interface SampleTestReceiverTypeDeclaration :
        KoReceiverTypeProvider,
        KoKDocProvider

    private interface SampleTestParametersDeclaration :
        KoParametersProvider,
        KoKDocProvider

    private interface SampleTestConstructorParametersDeclaration :
        KoConstructorProvider,
        KoPrimaryConstructorProvider,
        KoSecondaryConstructorsProvider,
        KoKDocProvider

    @Test
    fun `hasValidReturnTypeKDoc() returns true when declaration has no return type`() {
        // given
        val declaration: SampleTestReturnTypeDeclaration = mockk {
            every { returnType } returns null
        }

        // when
        val sut = declaration.hasValidReturnTypeKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidReturnTypeKDoc() returns true when declaration has Unit return type`() {
        // given
        val declaration: SampleTestReturnTypeDeclaration = mockk {
            every { returnType } returns mockk()
            every { returnType?.name } returns "Unit"
        }

        // when
        val sut = declaration.hasValidReturnTypeKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidReturnTypeKDoc() calls hasTags method`() {
        // given
        val declaration: SampleTestReturnTypeDeclaration = mockk {
            every { returnType } returns mockk()
            every { returnType?.name } returns "Boolean"
            every { kDoc?.hasTags(KoKDocTag.RETURN) } returns true
        }

        // when
        declaration.hasValidReturnTypeKDoc()

        // then
        verify { declaration.kDoc?.hasTags(KoKDocTag.RETURN) }
    }

    @Test
    fun `hasValidReturnTypeKDoc() returns true when declaration has valid return type kdoc`() {
        // given
        val declaration: SampleTestReturnTypeDeclaration = mockk {
            every { returnType } returns mockk()
            every { returnType?.name } returns "Boolean"
            every { kDoc?.hasTags(KoKDocTag.RETURN) } returns true
        }

        // when
        val sut = declaration.hasValidReturnTypeKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidReturnTypeKDoc() returns false when declaration has no valid return type kdoc`() {
        // given
        val declaration: SampleTestReturnTypeDeclaration = mockk {
            every { returnType } returns mockk()
            every { returnType?.name } returns "Boolean"
            every { kDoc?.hasTags(KoKDocTag.RETURN) } returns false
        }

        // when
        val sut = declaration.hasValidReturnTypeKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidReceiverTypeKDoc() returns true when declaration has no receiver`() {
        // given
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
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
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
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
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
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
        val declaration: SampleTestReceiverTypeDeclaration = mockk {
            every { receiverType } returns mockk()
            every { kDoc?.hasTags(KoKDocTag.RECEIVER) } returns false
        }

        // when
        val sut = declaration.hasValidReceiverTypeKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidParameterKDoc() returns true when declaration has no parameters`() {
        // given
        val declaration: SampleTestParametersDeclaration = mockk {
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
        val declaration: SampleTestParametersDeclaration = mockk {
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
        val declaration: SampleTestParametersDeclaration = mockk {
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
        val declaration: SampleTestParametersDeclaration = mockk {
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
        val declaration: SampleTestParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { parameters } returns listOf(parameter1, parameter2)
        }

        // when
        val sut = declaration.hasValidParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidConstructorParameterKDoc() returns true when declaration has no constructors`() {
        // given
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { constructors } returns emptyList()
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidConstructorParameterKDoc() returns true when declaration has valid param kdoc`() {
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
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1, parameter2)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns true
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors.isNotEmpty() } returns true
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidConstructorParameterKDoc() returns false when secondary constructor has no valid param kdoc`() {
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
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1, parameter2)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns false
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors.isNotEmpty() } returns true
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidConstructorParameterKDoc() returns false when declaration parameters have other names than param tags`() {
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
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1, parameter2)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns true
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors.isNotEmpty() } returns true
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidConstructorParameterKDoc() returns false when declaration has fewer parameters than param tags`() {
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
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns true
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors.isNotEmpty() } returns true
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidConstructorParameterKDoc() returns false when declaration has more parameters than param tags`() {
        // given
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns "value1"
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns "value1"
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns "value2"
        }
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1, parameter2)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns true
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1)
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors.isNotEmpty() } returns true
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }
}
