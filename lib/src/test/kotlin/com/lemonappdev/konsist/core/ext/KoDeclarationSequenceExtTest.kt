package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.const.KoModifier.OPEN
import com.lemonappdev.konsist.core.const.KoModifier.PROTECTED
import com.lemonappdev.konsist.core.declaration.KoAnnotation
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationSequenceExtTest {
    @Test
    fun `withPublicModifier() returns declaration1 with public modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicModifier() returns declaration2 without public modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPublicOrDefaultModifier() returns declaration1 with public or default modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicOrDefaultModifier() returns declaration2 without public or default modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPrivateModifier() returns declaration1 with private modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrivateModifier() returns declaration2 without private modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProtectedModifier() returns declaration1 with protected modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProtectedModifier() returns declaration2 without protected modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInternalModifier() returns declaration1 with internal modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInternalModifier() returns declaration2 without internal modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTopLevel() returns declaration1 which is top level declaration`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTopLevel() returns declaration2 which is not top level declaration`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclaration = mockk {
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
        val annotation: KoAnnotation = mockk()
        val declaration1: KoDeclaration = mockk {
            every { annotations } returns listOf(annotation)
        }
        val declaration2: KoDeclaration = mockk {
            every { annotations } returns emptyList()
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotation()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAnnotations() returns declaration without any annotation`() {
        // given
        val annotation: KoAnnotation = mockk()
        val declaration1: KoDeclaration = mockk {
            every { annotations } returns listOf(annotation)
        }
        val declaration2: KoDeclaration = mockk {
            every { annotations } returns emptyList()
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotation()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAnnotations(String) returns declaration1 with given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclaration = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotations(String) returns declaration2 without given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclaration = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeAnnotations(String) returns declarations which have at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val declaration1: KoDeclaration = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val declaration3: KoDeclaration = mockk {
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
    fun `withAnnotationsOf(KClass) returns declaration1 with given annotations`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationsOf(KClass) returns declaration3 without given annotations`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeAnnotationsOf(KClass) returns declaration1 and declaration2 which have at least one of given annotations`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class) } returns true
            every { hasAnnotations(SampleAnnotation2::class) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class) } returns true
            every { hasAnnotations(SampleAnnotation2::class) } returns false
        }
        val declaration3: KoDeclaration = mockk {
            every { hasAnnotations(SampleAnnotation1::class) } returns false
            every { hasAnnotations(SampleAnnotation2::class) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAnnotationOf(KClass) returns declaration with given annotation`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val declaration1: KoDeclaration = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val declaration2: KoDeclaration = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationOf(KClass) returns declaration without given annotation`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val declaration1: KoDeclaration = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val declaration2: KoDeclaration = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withModifiers(String) returns declaration1 with all given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclaration = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifiers(String) returns declaration3 without given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclaration = mockk {
            every { hasModifiers(modifier1) } returns true
            every { hasModifiers(modifier2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns true
        }
        val declaration3: KoDeclaration = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSomeModifiers(String) returns declarations which have at least one of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclaration = mockk {
            every { hasModifiers(modifier1) } returns true
            every { hasModifiers(modifier2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns true
        }
        val declaration3: KoDeclaration = mockk {
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
    fun `withPackage(String) returns declarations which have at least one of given package`() {
        // given
        val package1 = "com.sample.samplepackage1"
        val package2 = "com.sample.samplepackage2"
        val declaration1: KoDeclaration = mockk {
            every { resideInPackage(package1) } returns true
            every { resideInPackage(package2) } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { resideInPackage(package1) } returns false
            every { resideInPackage(package2) } returns true
        }
        val declaration3: KoDeclaration = mockk {
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
    fun `withoutPackage(String) returns declaration3 without given packages`() {
        // given
        val package1 = "com.sample.samplepackage1"
        val package2 = "com.sample.samplepackage2"
        val declaration1: KoDeclaration = mockk {
            every { resideOutsidePackage(package1) } returns false
            every { resideOutsidePackage(package2) } returns false
        }
        val declaration2: KoDeclaration = mockk {
            every { resideOutsidePackage(package1) } returns true
            every { resideOutsidePackage(package2) } returns false
        }
        val declaration3: KoDeclaration = mockk {
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
