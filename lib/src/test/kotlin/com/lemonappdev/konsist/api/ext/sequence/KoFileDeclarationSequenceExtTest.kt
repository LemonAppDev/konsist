package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationSequenceExtTest {
    @Test
    fun `withImport() returns file1 with given import`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports() } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImport() returns file2 without given import`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports() } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file1 with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file2 without given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file1 with all given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file2 without given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeImports(String) returns files which have at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val file3: KoFileDeclarationImpl = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withPackage(String) returns files with one of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFileDeclarationImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutPackage(String) returns file3 without given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFileDeclarationImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withAnnotation() returns file1 which has annotation`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations() } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotation() returns file2 which has not annotation`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations() } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(String) returns file1 with given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(String) returns file2 without given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(String) returns file1 with all given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(String) returns file2 without given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeAnnotations(String) returns file1 and file2 which have at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val file3: KoFileDeclarationImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withAnnotationOf(KClass) returns file1 with given annotation`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val file1: KoFileDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotationOf(KClass) returns file2 without given annotation`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val file1: KoFileDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotationsOf(KClass) returns file1 with all given annotations`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotationsOf(KClass) returns file2 without given annotations`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeAnnotationsOf(KClass) returns file1 and file2 which have at least one of given annotations`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val file3: KoFileDeclarationImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withTypeAlias() returns file1 which has typealias`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAlias() returns file2 which has not typealias`() {
        // given
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns file1 with given typealias`() {
        // given
        val typeAliasName = "SampleTypeAlias"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases(typeAliasName)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file2 without given typealias`() {
        // given
        val typeAliasName = "SampleTypeAlias"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases(typeAliasName)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns files with all given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file2 without given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeTypeAliases(String) returns files which have at least one of given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1) } returns true
            every { hasTypeAliases(typeAliasName2) } returns true
        }
        val file2: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1) } returns false
            every { hasTypeAliases(typeAliasName2) } returns true
        }
        val file3: KoFileDeclarationImpl = mockk {
            every { hasTypeAliases(typeAliasName1) } returns false
            every { hasTypeAliases(typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }
}
