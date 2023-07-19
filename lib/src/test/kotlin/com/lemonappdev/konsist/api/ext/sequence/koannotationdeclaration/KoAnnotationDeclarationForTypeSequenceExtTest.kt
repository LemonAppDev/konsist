package com.lemonappdev.konsist.api.ext.sequence.koannotationdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withRepresentedType
import com.lemonappdev.konsist.api.ext.sequence.withRepresentedTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutRepresentedType
import com.lemonappdev.konsist.api.ext.sequence.withoutRepresentedTypeOf
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForTypeSequenceExtTest {
    @Test
    fun `withRepresentedType(String) returns annotation with given type`() {
        // given
        val type = "type"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type) } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withRepresentedType(type)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withRepresentedType(String) returns annotations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withRepresentedType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutRepresentedType(String) returns annotation without given type`() {
        // given
        val type = "type"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type) } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutRepresentedType(type)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutRepresentedType(String) returns annotation without any of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutRepresentedType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    @Test
    fun `withRepresentedTypeOf(KClass) returns annotations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleAnnotation1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleAnnotation2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withRepresentedTypeOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass) returns annotation without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleAnnotation1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleAnnotation2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutRepresentedTypeOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    //  'every { representsType<SampleAnnotation>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withRepresentedTypeOf() with KClass syntax returns SampleAnnotation type`() {
        // given
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { SampleAnnotation::class.simpleName?.let { representsType(it) } } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { SampleAnnotation::class.simpleName?.let { representsType(it) } } returns false
            every { SampleAnnotation::class.qualifiedName?.let { representsType(it) } } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withRepresentedTypeOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutRepresentedTypeOf() with KClass syntax returns annotation without SampleAnnotation type`() {
        // given
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { SampleAnnotation::class.simpleName?.let { representsType(it) } } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { SampleAnnotation::class.simpleName?.let { representsType(it) } } returns false
            every { SampleAnnotation::class.qualifiedName?.let { representsType(it) } } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutRepresentedTypeOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }
}
