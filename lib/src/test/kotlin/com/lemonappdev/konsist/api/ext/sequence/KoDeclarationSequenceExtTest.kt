package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoKDocDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationSequenceExtTest {
    @Test
    fun `withPublicModifier() returns declaration with public modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicModifier() returns declaration without public modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPublicOrDefaultModifier() returns declaration with public or default modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicOrDefaultModifier() returns declaration without public or default modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPrivateModifier() returns declaration with private modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrivateModifier() returns declaration without private modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProtectedModifier() returns declaration with protected modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProtectedModifier() returns declaration without protected modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInternalModifier() returns declaration with internal modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInternalModifier() returns declaration without internal modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTopLevel() returns declaration which is top level declaration`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTopLevel() returns declaration which is not top level declaration`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

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

    @Test
    fun `withModifiers() returns declaration with modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifiers()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifiers() returns declaration without modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifiers()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withModifiers(String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifiers(String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeModifiers(String) returns declarations with at least one of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1) } returns true
            every { hasModifiers(modifier2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns true
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withPackage(String) returns declarations with at least one of given package`() {
        // given
        val package1 = "com.sample.samplepackage1"
        val package2 = "com.sample.samplepackage2"
        val declaration1: KoDeclarationImpl = mockk {
            every { resideInPackage(package1) } returns true
            every { resideInPackage(package2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { resideInPackage(package1) } returns false
            every { resideInPackage(package2) } returns true
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { resideInPackage(package1) } returns false
            every { resideInPackage(package2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutPackage(String) returns declaration without any of given packages`() {
        // given
        val package1 = "com.sample.samplepackage1"
        val package2 = "com.sample.samplepackage2"
        val declaration1: KoDeclarationImpl = mockk {
            every { resideOutsidePackage(package1) } returns false
            every { resideOutsidePackage(package2) } returns false
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { resideOutsidePackage(package1) } returns true
            every { resideOutsidePackage(package2) } returns false
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { resideOutsidePackage(package1) } returns true
            every { resideOutsidePackage(package2) } returns true
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
