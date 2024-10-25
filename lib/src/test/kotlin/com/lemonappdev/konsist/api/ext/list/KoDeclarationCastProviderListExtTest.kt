package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoDeclarationCastProviderListExtTest {
    @Test
    fun `classDeclarations returns classes from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoClassDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration1
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration2
                every { hasClassDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `classDeclarations returns classes from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoClassDeclaration) -> Boolean = { it.hasNameContaining("SomeClass") }
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoClassDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration1
                every { hasClassDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration2
                every { hasClassDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `objectDeclarations returns objects from all declarations`() {
        // given
        val sourceDeclaration1: KoObjectDeclaration = mockk()
        val sourceDeclaration2: KoObjectDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration1
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration2
                every { hasObjectDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.objectDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `objectDeclarations returns objects from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoObjectDeclaration) -> Boolean = { it.hasNameContaining("SomeObject") }
        val sourceDeclaration1: KoObjectDeclaration = mockk()
        val sourceDeclaration2: KoObjectDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration1
                every { hasObjectDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration2
                every { hasObjectDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.objectDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `interfaceDeclarations returns interfaces from all declarations`() {
        // given
        val sourceDeclaration1: KoInterfaceDeclaration = mockk()
        val sourceDeclaration2: KoInterfaceDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration1
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration2
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfaceDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `interfaceDeclarations returns interfaces from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoInterfaceDeclaration) -> Boolean = { it.hasNameContaining("SomeInterface") }
        val sourceDeclaration1: KoInterfaceDeclaration = mockk()
        val sourceDeclaration2: KoInterfaceDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration1
                every { hasInterfaceDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration2
                every { hasInterfaceDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfaceDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `typeAliasDeclarations returns type aliases from all declarations`() {
        // given
        val sourceDeclaration1: KoTypeAliasDeclaration = mockk()
        val sourceDeclaration2: KoTypeAliasDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration1
                every { hasTypeAliasDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration2
                every { hasTypeAliasDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasTypeAliasDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeAliasDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `typeAliasDeclarations returns type aliases from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoTypeAliasDeclaration) -> Boolean = { it.hasNameContaining("SomeTypeAlias") }
        val sourceDeclaration1: KoTypeAliasDeclaration = mockk()
        val sourceDeclaration2: KoTypeAliasDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration1
                every { hasTypeAliasDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration2
                every { hasTypeAliasDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasTypeAliasDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeAliasDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `importAliasDeclarations returns import aliases from all declarations`() {
        // given
        val sourceDeclaration1: KoImportAliasDeclaration = mockk()
        val sourceDeclaration2: KoImportAliasDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration1
                every { hasImportAliasDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration2
                every { hasImportAliasDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasImportAliasDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.importAliasDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `importAliasDeclarations returns import aliases from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoImportAliasDeclaration) -> Boolean = { it.hasNameContaining("SomeImportAlias") }
        val sourceDeclaration1: KoImportAliasDeclaration = mockk()
        val sourceDeclaration2: KoImportAliasDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration1
                every { hasImportAliasDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration2
                every { hasImportAliasDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasImportAliasDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.importAliasDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `kotlinTypeDeclarations returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinTypeDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `kotlinTypeDeclarations returns kotlin types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoKotlinTypeDeclaration) -> Boolean = { it.hasNameContaining("SomeKotlinType") }
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinTypeDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `kotlinBasicTypeDeclarations returns kotlin basic types from all declarations`() {
        // given
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinBasicTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinBasicTypeDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinBasicTypeDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `kotlinBasicTypeDeclarations returns kotlin basic types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoKotlinTypeDeclaration) -> Boolean = { it.hasNameContaining("SomeKotlinType") }
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinBasicTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinBasicTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinBasicTypeDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `kotlinCollectionTypeDeclarations returns kotlin collection types from all declarations`() {
        // given
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinCollectionTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinCollectionTypeDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinCollectionTypeDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `kotlinCollectionTypeDeclarations returns kotlin collection types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoKotlinTypeDeclaration) -> Boolean = { it.hasNameContaining("SomeKotlinCollectionType") }
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinCollectionTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinCollectionTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinCollectionTypeDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `typeParameterDeclarations returns type parameters from all declarations`() {
        // given
        val sourceDeclaration1: KoTypeParameterDeclaration = mockk()
        val sourceDeclaration2: KoTypeParameterDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration1
                every { hasTypeParameterDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration2
                every { hasTypeParameterDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasTypeParameterDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeParameterDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `typeParameterDeclarations returns type parameters from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoTypeParameterDeclaration) -> Boolean = { it.hasNameContaining("SomeTypeParameter") }
        val sourceDeclaration1: KoTypeParameterDeclaration = mockk()
        val sourceDeclaration2: KoTypeParameterDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration1
                every { hasTypeParameterDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration2
                every { hasTypeParameterDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasTypeParameterDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeParameterDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `externalDeclarations returns external types from all declarations`() {
        // given
        val sourceDeclaration1: KoExternalDeclaration = mockk()
        val sourceDeclaration2: KoExternalDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceDeclaration1
                every { hasExternalDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceDeclaration2
                every { hasExternalDeclaration() } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.externalDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `externalDeclarations returns external types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoExternalDeclaration) -> Boolean = { it.hasNameContaining("SomeExternalType") }
        val sourceDeclaration1: KoExternalDeclaration = mockk()
        val sourceDeclaration2: KoExternalDeclaration = mockk()
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceDeclaration1
                every { hasExternalDeclaration(predicate) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceDeclaration2
                every { hasExternalDeclaration(predicate) } returns false
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.externalDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `withClassDeclaration() returns declaration with class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isClass } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isClass } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassDeclarationOf(empty list) returns declaration with class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassDeclarationOf(empty set) returns declaration with class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceClass1: KoClassDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceClass2: KoClassDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withClassDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutClassDeclaration() returns declaration without class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isClass } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isClass } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassDeclarationOf(empty list) returns declaration without class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassDeclarationOf(empty set) returns declaration without class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceClass1: KoClassDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceClass2: KoClassDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asClassDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutClassDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withClassDeclarationOf(KClass) returns declaration with given class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withClassDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withClassDeclarationOf(KClass) returns declarations with one of given classes`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withClassDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withClassDeclarationOf(list of KClass) returns declarations with one of given classes`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withClassDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withClassDeclarationOf(set of KClass) returns declarations with one of given classes`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withClassDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutClassDeclarationOf(KClass) returns declaration without given class`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutClassDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutClassDeclarationOf(KClass) returns declaration without any of given classes`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutClassDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutClassDeclarationOf(list of KClass) returns declaration without any of given classes`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutClassDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutClassDeclarationOf(set of KClass) returns declaration without any of given classes`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutClassDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withObjectDeclaration() returns declaration with object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isObject } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isObject } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectDeclarationOf(empty list) returns declaration with object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectDeclarationOf(empty set) returns declaration with object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceObject1: KoObjectDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceObject2: KoObjectDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withObjectDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutObjectDeclaration() returns declaration without object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isObject } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isObject } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectDeclarationOf(empty list) returns declaration without object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectDeclarationOf(empty set) returns declaration without object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceObject1: KoObjectDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceObject2: KoObjectDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asObjectDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutObjectDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withObjectDeclarationOf(KClass) returns declaration with given object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withObjectDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withObjectDeclarationOf(KClass) returns declarations with one of given objects`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withObjectDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withObjectDeclarationOf(list of KClass) returns declarations with one of given objects`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withObjectDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withObjectDeclarationOf(set of KClass) returns declarations with one of given objects`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withObjectDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutObjectDeclarationOf(KClass) returns declaration without given object`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutObjectDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutObjectDeclarationOf(KClass) returns declaration without any of given objects`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutObjectDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutObjectDeclarationOf(list of KClass) returns declaration without any of given objects`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutObjectDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutObjectDeclarationOf(set of KClass) returns declaration without any of given objects`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutObjectDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withInterfaceDeclaration() returns declaration with interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isInterface } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isInterface } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceDeclarationOf(empty list) returns declaration with interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceDeclarationOf(empty set) returns declaration with interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceInterface1: KoInterfaceDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceInterface2: KoInterfaceDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withInterfaceDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInterfaceDeclaration() returns declaration without interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isInterface } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isInterface } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceDeclarationOf(empty list) returns declaration without interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceDeclarationOf(empty set) returns declaration without interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceInterface1: KoInterfaceDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceInterface2: KoInterfaceDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asInterfaceDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutInterfaceDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withInterfaceDeclarationOf(KClass) returns declaration with given interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInterfaceDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withInterfaceDeclarationOf(KClass) returns declarations with one of given interfaces`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withInterfaceDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withInterfaceDeclarationOf(list of KClass) returns declarations with one of given interfaces`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withInterfaceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withInterfaceDeclarationOf(set of KClass) returns declarations with one of given interfaces`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withInterfaceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutInterfaceDeclarationOf(KClass) returns declaration without given interface`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInterfaceDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutInterfaceDeclarationOf(KClass) returns declaration without any of given interfaces`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutInterfaceDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutInterfaceDeclarationOf(list of KClass) returns declaration without any of given interfaces`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutInterfaceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutInterfaceDeclarationOf(set of KClass) returns declaration without any of given interfaces`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutInterfaceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTypeAliasDeclaration() returns declaration with type alias`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isTypeAlias } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isTypeAlias } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeAliasDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeAliasDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceTypeAlias1: KoTypeAliasDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceTypeAlias2: KoTypeAliasDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeAliasDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeAliasDeclaration() returns declaration without type alias`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isTypeAlias } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isTypeAlias } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeAliasDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeAliasDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceTypeAlias1: KoTypeAliasDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceTypeAlias2: KoTypeAliasDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeAliasDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withImportAliasDeclaration() returns declaration with import alias`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isImportAlias } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isImportAlias } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImportAliasDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withImportAliasDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceImportAlias1: KoImportAliasDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceImportAlias2: KoImportAliasDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withImportAliasDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImportAliasDeclaration() returns declaration without import alias`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isImportAlias } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isImportAlias } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImportAliasDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutImportAliasDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceImportAlias1: KoImportAliasDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceImportAlias2: KoImportAliasDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asImportAliasDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutImportAliasDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withKotlinTypeDeclaration() returns declaration with kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinTypeDeclarationOf(empty list) returns declaration with kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinTypeDeclarationOf(empty set) returns declaration with kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinTypeDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinType1: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceKotlinType2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKotlinTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKotlinTypeDeclaration() returns declaration without kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinTypeDeclarationOf(empty list) returns declaration without kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinTypeDeclarationOf(empty set) returns declaration without kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinTypeDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinType1: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceKotlinType2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKotlinTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withKotlinTypeDeclarationOf(KClass) returns declaration with given kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinTypeDeclarationOf(String::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinTypeDeclarationOf(KClass) returns declarations with one of given kotlin types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKotlinTypeDeclarationOf(String::class, Int::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withKotlinTypeDeclarationOf(list of KClass) returns declarations with one of given kotlin types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(String::class, Int::class)

        // when
        val sut = declarations.withKotlinTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withKotlinTypeDeclarationOf(set of KClass) returns declarations with one of given kotlin types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(String::class, Int::class)

        // when
        val sut = declarations.withKotlinTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutKotlinTypeDeclarationOf(KClass) returns declaration without given kotlin type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinTypeDeclarationOf(String::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinTypeDeclarationOf(KClass) returns declaration without any of given kotlin types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKotlinTypeDeclarationOf(String::class, Int::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutKotlinTypeDeclarationOf(list of KClass) returns declaration without any of given kotlin types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(String::class, Int::class)

        // when
        val sut = declarations.withoutKotlinTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutKotlinTypeDeclarationOf(set of KClass) returns declaration without any of given kotlin types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(String::class, Int::class)

        // when
        val sut = declarations.withoutKotlinTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withKotlinBasicTypeDeclaration() returns declaration with kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinBasicTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinBasicTypeDeclarationOf(empty list) returns declaration with kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinBasicTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinBasicTypeDeclarationOf(empty set) returns declaration with kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinBasicTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinBasicTypeDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinBasicType1: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceKotlinBasicType2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceKotlinBasicType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceKotlinBasicType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKotlinBasicTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclaration() returns declaration without kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclarationOf(empty list) returns declaration without kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclarationOf(empty set) returns declaration without kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinBasicType1: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceKotlinBasicType2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceKotlinBasicType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns sourceKotlinBasicType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asKotlinBasicTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withKotlinBasicTypeDeclarationOf(KClass) returns declaration with given kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinBasicTypeDeclarationOf(String::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinBasicTypeDeclarationOf(KClass) returns declarations with one of given kotlin basic types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKotlinBasicTypeDeclarationOf(String::class, Int::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withKotlinBasicTypeDeclarationOf(list of KClass) returns declarations with one of given kotlin basic types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(String::class, Int::class)

        // when
        val sut = declarations.withKotlinBasicTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withKotlinBasicTypeDeclarationOf(set of KClass) returns declarations with one of given kotlin basic types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(String::class, Int::class)

        // when
        val sut = declarations.withKotlinBasicTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclarationOf(KClass) returns declaration without given kotlin basic type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclarationOf(String::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclarationOf(KClass) returns declaration without any of given kotlin basic types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclarationOf(String::class, Int::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclarationOf(list of KClass) returns declaration without any of given kotlin basic types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(String::class, Int::class)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutKotlinBasicTypeDeclarationOf(set of KClass) returns declaration without any of given kotlin basic types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns true
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinBasicTypeDeclarationOf(String::class) } returns false
                every { hasKotlinBasicTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(String::class, Int::class)

        // when
        val sut = declarations.withoutKotlinBasicTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withKotlinCollectionTypeDeclaration() returns declaration with kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinCollectionTypeDeclarationOf(empty list) returns declaration with kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinCollectionTypeDeclarationOf(empty set) returns declaration with kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinCollectionTypeDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinCollectionType1: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceKotlinCollectionType2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceKotlinCollectionType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceKotlinCollectionType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclaration() returns declaration without kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclarationOf(empty list) returns declaration without kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclarationOf(empty set) returns declaration without kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceKotlinCollectionType1: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceKotlinCollectionType2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceKotlinCollectionType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns sourceKotlinCollectionType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asKotlinCollectionTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withKotlinCollectionTypeDeclarationOf(KClass) returns declaration with given kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclarationOf(String::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withKotlinCollectionTypeDeclarationOf(KClass) returns declarations with one of given kotlin collection types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclarationOf(String::class, Int::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withKotlinCollectionTypeDeclarationOf(list of KClass) returns declarations with one of given kotlin collection types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(String::class, Int::class)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withKotlinCollectionTypeDeclarationOf(set of KClass) returns declarations with one of given kotlin collection types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(String::class, Int::class)

        // when
        val sut = declarations.withKotlinCollectionTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclarationOf(KClass) returns declaration without given kotlin collection type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclarationOf(String::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclarationOf(KClass) returns declaration without any of given kotlin collection types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclarationOf(String::class, Int::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclarationOf(list of KClass) returns declaration without any of given kotlin collection types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(String::class, Int::class)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutKotlinCollectionTypeDeclarationOf(set of KClass) returns declaration without any of given kotlin collection types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns true
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasKotlinCollectionTypeDeclarationOf(String::class) } returns false
                every { hasKotlinCollectionTypeDeclarationOf(Int::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(String::class, Int::class)

        // when
        val sut = declarations.withoutKotlinCollectionTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withTypeParameterDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceTypeParameter1: KoTypeParameterDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceTypeParameter2: KoTypeParameterDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeParameterDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeParameterDeclaration() returns declaration without type parameter`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isTypeParameter } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isTypeParameter } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameterDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeParameterDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceTypeParameter1: KoTypeParameterDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceTypeParameter2: KoTypeParameterDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeParameterDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withExternalDeclaration() returns declaration with external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isExternalDeclaration } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isExternalDeclaration } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalDeclarationOf(empty list) returns declaration with external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalDeclarationOf(empty set) returns declaration with external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceExternalType1: KoExternalDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceExternalType2: KoExternalDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceExternalType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceExternalType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExternalDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalDeclaration() returns declaration without external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { isExternalDeclaration } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { isExternalDeclaration } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalDeclarationOf(empty list) returns declaration without external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalDeclarationOf(empty set) returns declaration without external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceExternalType1: KoExternalDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceExternalType2: KoExternalDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceExternalType1
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns sourceExternalType2
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { asExternalDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExternalDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withExternalDeclarationOf(KClass) returns declaration with given external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalDeclarationOf(KClass) returns declarations with one of given external types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExternalDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withExternalDeclarationOf(list of KClass) returns declarations with one of given external types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withExternalDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withExternalDeclarationOf(set of KClass) returns declarations with one of given external types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withExternalDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExternalDeclarationOf(KClass) returns declaration without given external type`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalDeclarationOf(KClass) returns declaration without any of given external types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExternalDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutExternalDeclarationOf(list of KClass) returns declaration without any of given external types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutExternalDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutExternalDeclarationOf(set of KClass) returns declaration without any of given external types`() {
        // given
        val declaration1: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns true
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoDeclarationCastProvider =
            mockk {
                every { hasExternalDeclarationOf(SampleType1::class) } returns false
                every { hasExternalDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutExternalDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
