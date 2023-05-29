package com.lemonappdev.konsist.api.ext.sequence.kodeclaration

import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withSomeAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withSomeAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationsOf
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
    fun `withAnnotations() returns declaration without any annotation`() {
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
    fun `withAnnotations(String) returns declaration with all of given annotations`() {
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
        val sut = declarations.withAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotations(String) returns declaration without any of given annotations`() {
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
        val sut = declarations.withoutAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
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
    fun `withAnnotationsOf(KClass) returns declaration with all of given annotations`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationsOf(KClass) returns declaration without any of given annotations`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

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
