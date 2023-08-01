package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationProviderListExtTest {
    @Test
    fun `withAnnotations() returns declaration with any annotation`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations() } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotations()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotations() returns declaration without any annotation`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations() } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotations()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllAnnotations(String) returns declaration with all of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllAnnotations(annotation1, annotation2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllAnnotations(String) returns declaration without any of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllAnnotations(annotation1, annotation2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeAnnotations(String) returns declaration with given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeAnnotations(annotation)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeAnnotations(String) returns declarations with at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration3: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeAnnotations(annotation1, annotation2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeAnnotations(String) returns declaration without given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeAnnotations(annotation)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeAnnotations(String) returns declarations with at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration3: KoAnnotationProvider = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeAnnotations(annotation1, annotation2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAllAnnotationsOf(KClass) returns declaration with given annotation`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllAnnotationsOf(SampleAnnotation1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllAnnotationsOf(KClass) returns declaration with all of given annotations`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllAnnotationsOf(KClass) returns declaration without given annotation`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllAnnotationsOf(SampleAnnotation1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllAnnotationsOf(KClass) returns declaration without any of given annotations`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeAnnotationsOf(KClass) returns declarations with at least one of given annotations`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declaration3: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeAnnotationsOf(KClass) returns declarations without at least one of given annotations`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declaration3: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAnnotationOf(KClass) returns declaration with all of given annotations`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationOf<SampleAnnotation>()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationOf(KClass) returns declaration without any of given annotations`() {
        // given
        val declaration1: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation::class) } returns true
        }
        val declaration2: KoAnnotationProvider = mockk {
            every { hasAnnotationsOf(SampleAnnotation::class) } returns false
        }

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationOf<SampleAnnotation>()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
