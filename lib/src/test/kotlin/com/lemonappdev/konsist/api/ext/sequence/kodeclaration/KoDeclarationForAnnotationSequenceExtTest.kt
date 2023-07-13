package com.lemonappdev.konsist.api.ext.sequence.kodeclaration

import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withAllAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withSomeAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withSomeAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withoutAllAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withoutAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutSomeAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withoutSomeAnnotationsOf
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForAnnotationSequenceExtTest {
    @Test
    fun `withAnnotations() returns declaration with any annotation`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotations()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotations() returns declaration without any annotation`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotations()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllAnnotations(String) returns declaration with all of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllAnnotations(String) returns declaration without any of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeAnnotations(String) returns declaration with given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeAnnotations(String) returns declarations with at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeAnnotations(String) returns declaration without given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeAnnotations(String) returns declarations with at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAllAnnotationsOf(KClass) returns declaration with given annotation`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllAnnotationsOf(SampleAnnotation1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllAnnotationsOf(KClass) returns declaration with all of given annotations`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllAnnotationsOf(KClass) returns declaration without given annotation`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllAnnotationsOf(SampleAnnotation1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllAnnotationsOf(KClass) returns declaration without any of given annotations`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeAnnotationsOf(KClass) returns declarations with at least one of given annotations`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeAnnotationsOf(KClass) returns declarations without at least one of given annotations`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAnnotationOf(KClass) returns declaration with all of given annotations`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val declaration1: KoDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationOf(KClass) returns declaration without any of given annotations`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val declaration1: KoDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
