package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyProviderExtTest {
    private interface SampleTestPropertyDeclaration :
        KoPropertyProvider,
        KoKDocProvider

    @Test
    fun `hasValidKDocPropertyTags() returns false when declaration not implement KoKDocProvider`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val property1: KoPropertyDeclaration = mockk {
            every { name } returns name1
        }
        val property2: KoPropertyDeclaration = mockk {
            every { name } returns name2
        }
        val declaration: KoPropertyProvider = mockk {
            every { properties() } returns listOf(property1, property2)
        }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocPropertyTags() returns true when declaration has no properties`() {
        // given
        val declaration: SampleTestPropertyDeclaration = mockk {
            every { properties() } returns emptyList()
        }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocPropertyTags() returns true when declaration has valid property kdoc`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val property1: KoPropertyDeclaration = mockk {
            every { name } returns name1
        }
        val property2: KoPropertyDeclaration = mockk {
            every { name } returns name2
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { propertyTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestPropertyDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { properties() } returns listOf(property1, property2)
        }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo true
    }

    @Test
    fun `hasValidKDocPropertyTags() returns false when declaration properties have other names than property tags`() {
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
        val property1: KoPropertyDeclaration = mockk {
            every { name } returns incorrectName
        }
        val property2: KoPropertyDeclaration = mockk {
            every { name } returns name2
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { propertyTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestPropertyDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { properties() } returns listOf(property1, property2)
        }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocPropertyTags() returns false when declaration has fewer properties than property tags`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val tag1: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name1
        }
        val tag2: KoValuedKDocTagDeclaration = mockk {
            every { value } returns name2
        }
        val property1: KoPropertyDeclaration = mockk {
            every { name } returns name1
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { propertyTags } returns listOf(tag1, tag2)
        }
        val declaration: SampleTestPropertyDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { properties() } returns listOf(property1)
        }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo false
    }

    @Test
    fun `hasValidKDocPropertyTags() returns false when declaration has more properties than property tags`() {
        // given
        val property: KoPropertyDeclaration = mockk {
            every { name } returns "name1"
        }
        val kDocDeclaration: KoKDocDeclaration = mockk {
            every { propertyTags } returns emptyList()
        }
        val declaration: SampleTestPropertyDeclaration = mockk {
            every { kDoc } returns kDocDeclaration
            every { properties() } returns listOf(property)
        }

        // when
        val sut = declaration.hasValidKDocPropertyTags()

        // then
        sut shouldBeEqualTo false
    }
}
