package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorProviderExtTest {
    private interface SampleTestConstructorParametersDeclaration :
        KoConstructorProvider,
        KoPrimaryConstructorProvider,
        KoSecondaryConstructorsProvider,
        KoKDocProvider

    private interface SampleTestDeclarationWithoutKoKDocProvider :
        KoConstructorProvider,
        KoPrimaryConstructorProvider,
        KoSecondaryConstructorsProvider
    @Test
    fun `hasValidConstructorParameterKDoc() returns false when declaration not implement KoKDocProvider`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val tag: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns name1
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns name2
        }
        val kDocDecl: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag)
        }
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter2)
            every { kDoc } returns kDocDecl
        }
        val declaration: SampleTestDeclarationWithoutKoKDocProvider = mockk {
            every { constructors } returns listOf(primaryConstructorDeclaration, secondaryConstructorDeclaration)
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

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
        val kDoc1: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1)
        }
        val kDoc2: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag2)
        }
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter2)
            every { kDoc } returns kDoc2
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDoc1
            every { constructors } returns listOf(primaryConstructorDeclaration, secondaryConstructorDeclaration)
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
        val name1 = "name1"
        val name2 = "name2"
        val incorrectName = "incorrectName"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns incorrectName
        }
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns name1
        }
        val parameter2: KoParameterDeclaration = mockk {
            every { name } returns name2
        }
        val kDoc1: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1)
        }
        val kDoc2: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag2)
        }
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter2)
            every { kDoc } returns kDoc2
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDoc1
            every { constructors } returns listOf(primaryConstructorDeclaration, secondaryConstructorDeclaration)
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
        val parameter1: KoParameterDeclaration = mockk {
            every { name } returns "name1"
        }
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter1)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns true
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns listOf(tag1)
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors } returns listOf(primaryConstructorDeclaration, secondaryConstructorDeclaration)
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
            every { constructors } returns listOf(primaryConstructorDeclaration, secondaryConstructorDeclaration)
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
        val parameter: KoParameterDeclaration = mockk {
            every { name } returns "value1"
        }
        val primaryConstructorDeclaration: KoPrimaryConstructorDeclaration = mockk {
            every { parameters } returns listOf(parameter)
        }
        val secondaryConstructorDeclaration: KoSecondaryConstructorDeclaration = mockk {
            every { hasValidParameterKDoc() } returns true
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { paramTags } returns emptyList()
        }
        val declaration: SampleTestConstructorParametersDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { constructors } returns listOf(primaryConstructorDeclaration, secondaryConstructorDeclaration)
            every { primaryConstructor } returns primaryConstructorDeclaration
            every { secondaryConstructors } returns listOf(secondaryConstructorDeclaration)
        }

        // when
        val sut = declaration.hasValidConstructorParameterKDoc()

        // then
        sut shouldBeEqualTo false
    }
}
