package com.lemonappdev.konsist.api.ext.sequence.koannotationdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withType
import com.lemonappdev.konsist.api.ext.sequence.withTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutType
import com.lemonappdev.konsist.api.ext.sequence.withoutTypeOf
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
    fun `withType(String) returns annotations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutType(String) returns annotation without any of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    @Test
    fun `withTypeOf(KClass) returns annotations with one of given types`() {
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
        val sut = annotations.withTypeOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns annotation without any of given types`() {
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
        val sut = annotations.withoutTypeOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    //  'every { representsType<SampleAnnotation>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withTypeOf() with KClass syntax returns SampleAnnotation type`() {
        // given
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleAnnotation"
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withTypeOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutTypeOf() with KClass syntax returns annotation without SampleAnnotation type`() {
        // given
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleAnnotation"
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutTypeOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }
}
