package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoTypeDeclarationProviderListExtTest {
    @Test
    fun `deprecated - declarations returns declarations from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration3: KoExternalDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.declarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2, sourceDeclaration3)
    }

    @Test
    fun `deprecated - classDeclarations returns classes from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoClassDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.classDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - objectDeclarations returns objects from all declarations`() {
        // given
        val sourceDeclaration1: KoObjectDeclaration = mockk()
        val sourceDeclaration2: KoObjectDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.objectDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - interfaceDeclarations returns interfaces from all declarations`() {
        // given
        val sourceDeclaration1: KoInterfaceDeclaration = mockk()
        val sourceDeclaration2: KoInterfaceDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.interfaceDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - typeAliasDeclarations returns type aliases from all declarations`() {
        // given
        val sourceDeclaration1: KoTypeAliasDeclaration = mockk()
        val sourceDeclaration2: KoTypeAliasDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeAliasDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - importAliasDeclarations returns import aliases from all declarations`() {
        // given
        val sourceDeclaration1: KoImportAliasDeclaration = mockk()
        val sourceDeclaration2: KoImportAliasDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.importAliasDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - kotlinTypeDeclarations returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoKotlinTypeDeclaration = mockk()
        val sourceDeclaration2: KoKotlinTypeDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.kotlinTypeDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - functionTypeDeclarations returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoFunctionTypeDeclaration = mockk()
        val sourceDeclaration2: KoFunctionTypeDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.functionTypeDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `deprecated - externalTypeDeclarations returns kotlin types from all declarations`() {
        // given
        val sourceDeclaration1: KoExternalDeclaration = mockk()
        val sourceDeclaration2: KoExternalDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceDeclaration2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.externalTypeDeclarations

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `classDeclarations returns classes from all declarations`() {
        // given
        val sourceDeclaration1: KoClassDeclaration = mockk()
        val sourceDeclaration2: KoClassDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration1
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration2
                every { hasClassDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration1
                every { hasClassDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceDeclaration2
                every { hasClassDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration1
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration2
                every { hasObjectDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration1
                every { hasObjectDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceDeclaration2
                every { hasObjectDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration1
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration2
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration1
                every { hasInterfaceDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceDeclaration2
                every { hasInterfaceDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration1
                every { hasTypeAliasDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration2
                every { hasTypeAliasDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration1
                every { hasTypeAliasDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceDeclaration2
                every { hasTypeAliasDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration1
                every { hasImportAliasDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration2
                every { hasImportAliasDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration1
                every { hasImportAliasDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceDeclaration2
                every { hasImportAliasDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration1
                every { hasKotlinTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceDeclaration2
                every { hasKotlinTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
    fun `functionTypeDeclarations returns function types from all declarations`() {
        // given
        val sourceDeclaration1: KoFunctionTypeDeclaration = mockk()
        val sourceDeclaration2: KoFunctionTypeDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceDeclaration1
                every { hasFunctionTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceDeclaration2
                every { hasFunctionTypeDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasFunctionTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.functionTypeDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `functionTypeDeclarations returns function types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoFunctionTypeDeclaration) -> Boolean = { it.hasNameContaining("SomeFunctionType") }
        val sourceDeclaration1: KoFunctionTypeDeclaration = mockk()
        val sourceDeclaration2: KoFunctionTypeDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceDeclaration1
                every { hasFunctionTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceDeclaration2
                every { hasFunctionTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasFunctionTypeDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.functionTypeDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `genericTypeDeclarations returns generic types from all declarations`() {
        // given
        val sourceDeclaration1: KoGenericTypeDeclaration = mockk()
        val sourceDeclaration2: KoGenericTypeDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceDeclaration1
                every { hasGenericTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceDeclaration2
                every { hasGenericTypeDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.genericTypeDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `genericTypeDeclarations returns generic types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoGenericTypeDeclaration) -> Boolean = { it.hasNameContaining("SomeGenericType") }
        val sourceDeclaration1: KoGenericTypeDeclaration = mockk()
        val sourceDeclaration2: KoGenericTypeDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceDeclaration1
                every { hasGenericTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceDeclaration2
                every { hasGenericTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.genericTypeDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `typeParameterDeclarations returns type parameters from all declarations`() {
        // given
        val sourceDeclaration1: KoTypeParameterDeclaration = mockk()
        val sourceDeclaration2: KoTypeParameterDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration1
                every { hasTypeParameterDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration2
                every { hasTypeParameterDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration1
                every { hasTypeParameterDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceDeclaration2
                every { hasTypeParameterDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
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
    fun `starProjectionDeclarations returns star projections from all declarations`() {
        // given
        val sourceDeclaration1: KoStarProjectionDeclaration = mockk()
        val sourceDeclaration2: KoStarProjectionDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceDeclaration1
                every { hasStarProjectionDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceDeclaration2
                every { hasStarProjectionDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasStarProjectionDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.starProjectionDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `starProjectionDeclarations returns star projections from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoStarProjectionDeclaration) -> Boolean = { it.hasNameContaining("SomeStarProjection") }
        val sourceDeclaration1: KoStarProjectionDeclaration = mockk()
        val sourceDeclaration2: KoStarProjectionDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceDeclaration1
                every { hasStarProjectionDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceDeclaration2
                every { hasStarProjectionDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasStarProjectionDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.starProjectionDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `externalTypeDeclarations returns external types from all declarations`() {
        // given
        val sourceDeclaration1: KoExternalDeclaration = mockk()
        val sourceDeclaration2: KoExternalDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceDeclaration1
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceDeclaration2
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.externalTypeDeclarations()

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1, sourceDeclaration2)
    }

    @Test
    fun `externalTypeDeclarations returns external types from all declarations which satisfies predicate`() {
        // given
        val predicate: (KoExternalDeclaration) -> Boolean = { it.hasNameContaining("SomeExternalType") }
        val sourceDeclaration1: KoExternalDeclaration = mockk()
        val sourceDeclaration2: KoExternalDeclaration = mockk()
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceDeclaration1
                every { hasExternalTypeDeclaration(predicate) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceDeclaration2
                every { hasExternalTypeDeclaration(predicate) } returns false
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.externalTypeDeclarations(predicate)

        // then
        sut shouldBeEqualTo listOf(sourceDeclaration1)
    }

    @Test
    fun `withDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceDeclaration1: KoClassDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceDeclaration2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceDeclaration1: KoClassDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceDeclaration2: KoKotlinTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { declaration } returns sourceDeclaration2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withDeclarationOf(empty list) returns all declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider = mockk()
        val declaration2: KoTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withDeclarationOf(empty set) returns all declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider = mockk()
        val declaration2: KoTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withDeclarationOf(KClass) returns declaration with given declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withDeclarationOf(KClass) returns declarations with one of given declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withDeclarationOf(list of KClass) returns declarations with one of given declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withDeclarationOf(set of KClass) returns declarations with one of given declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutDeclarationOf(empty list) returns none declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutDeclarationOf(empty set) returns none declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutDeclarationOf(KClass) returns declaration without given declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutDeclarationOf(KClass) returns declaration without any of given declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutDeclarationOf(list of KClass) returns declaration without any of given declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutDeclarationOf(set of KClass) returns declaration without any of given declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns true
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasDeclarationOf(SampleType1::class) } returns false
                every { hasDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSourceDeclarationOf(empty list) returns all source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider = mockk()
        val declaration2: KoTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(empty set) returns all source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider = mockk()
        val declaration2: KoTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(KClass) returns declaration with given source declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceDeclarationOf(KClass) returns declarations with one of given source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(list of KClass) returns declarations with one of given source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceDeclarationOf(set of KClass) returns declarations with one of given source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceDeclarationOf(empty list) returns none source declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceDeclarationOf(empty set) returns none source declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceDeclarationOf(KClass) returns declaration without given source declaration`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceDeclarationOf(KClass) returns declaration without any of given source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceDeclarationOf(list of KClass) returns declaration without any of given source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceDeclarationOf(set of KClass) returns declaration without any of given source declarations`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns true
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasSourceDeclarationOf(SampleType1::class) } returns false
                every { hasSourceDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutSourceDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withClassDeclaration() returns declaration with class`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asClassDeclaration() } returns sourceClass2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns true
                every { hasClassDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasClassDeclarationOf(SampleType1::class) } returns false
                every { hasClassDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asObjectDeclaration() } returns sourceObject2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns true
                every { hasObjectDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasObjectDeclarationOf(SampleType1::class) } returns false
                every { hasObjectDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asInterfaceDeclaration() } returns sourceInterface2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns true
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasInterfaceDeclarationOf(SampleType1::class) } returns false
                every { hasInterfaceDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeAliasDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeAliasDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeAliasDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeAliasDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeAliasDeclaration() } returns sourceTypeAlias2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasImportAliasDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasImportAliasDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasImportAliasDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasImportAliasDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asImportAliasDeclaration() } returns sourceImportAlias2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asKotlinTypeDeclaration() } returns sourceKotlinType2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns true
                every { hasKotlinTypeDeclarationOf(Int::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasKotlinTypeDeclarationOf(String::class) } returns false
                every { hasKotlinTypeDeclarationOf(Int::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
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
    fun `withFunctionTypeDeclaration() returns declaration with function type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasFunctionTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasFunctionTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunctionTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withFunctionTypeDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceFunctionType1: KoFunctionTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceFunctionType2: KoFunctionTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceFunctionType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceFunctionType2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withFunctionTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFunctionTypeDeclaration() returns declaration without function type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasFunctionTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasFunctionTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunctionTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutFunctionTypeDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceFunctionType1: KoFunctionTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceFunctionType2: KoFunctionTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceFunctionType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns sourceFunctionType2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asFunctionTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutFunctionTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withGenericTypeDeclaration() returns declaration with generic type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withGenericTypeDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceGenericType1: KoGenericTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceGenericType2: KoGenericTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceGenericType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceGenericType2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withGenericTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutGenericTypeDeclaration() returns declaration without generic type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutGenericTypeDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceGenericType1: KoGenericTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceGenericType2: KoGenericTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceGenericType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns sourceGenericType2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asGenericTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutGenericTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withTypeParameterDeclaration() returns declaration with type parameter`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeParameterDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeParameterDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameterDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter2
            }
        val declaration3: KoTypeDeclarationProvider =
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeParameterDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasTypeParameterDeclaration() } returns false
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asTypeParameterDeclaration() } returns sourceTypeParameter2
            }
        val declaration3: KoTypeDeclarationProvider =
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
    fun `withStarProjectionDeclaration() returns declaration with star projection`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasStarProjectionDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasStarProjectionDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withStarProjectionDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withStarProjectionDeclaration{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceStarProjection1: KoStarProjectionDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceStarProjection2: KoStarProjectionDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceStarProjection1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceStarProjection2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withStarProjectionDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutStarProjectionDeclaration() returns declaration without star projection`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasStarProjectionDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasStarProjectionDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutStarProjectionDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutStarProjectionDeclaration{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val sourceStarProjection1: KoStarProjectionDeclaration =
            mockk {
                every { name } returns name1
            }
        val sourceStarProjection2: KoStarProjectionDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceStarProjection1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns sourceStarProjection2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asStarProjectionDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutStarProjectionDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withExternalTypeDeclaration() returns declaration with external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalTypeDeclarationOf(empty list) returns declaration with external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalTypeDeclarationOf(empty set) returns declaration with external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalTypeDeclaration{} returns declaration which satisfy predicate`() {
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceExternalType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceExternalType2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExternalTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalTypeDeclaration() returns declaration without external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalTypeDeclaration()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalTypeDeclarationOf(empty list) returns declaration without external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalTypeDeclarationOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalTypeDeclarationOf(empty set) returns declaration without external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclaration() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalTypeDeclarationOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalTypeDeclaration{} returns declarations which not satisfy predicate`() {
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
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceExternalType1
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns sourceExternalType2
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { asExternalTypeDeclaration() } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExternalTypeDeclaration { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withExternalTypeDeclarationOf(KClass) returns declaration with given external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalTypeDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExternalTypeDeclarationOf(KClass) returns declarations with one of given external types`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExternalTypeDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withExternalTypeDeclarationOf(list of KClass) returns declarations with one of given external types`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withExternalTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withExternalTypeDeclarationOf(set of KClass) returns declarations with one of given external types`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withExternalTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExternalTypeDeclarationOf(KClass) returns declaration without given external type`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalTypeDeclarationOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExternalTypeDeclarationOf(KClass) returns declaration without any of given external types`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExternalTypeDeclarationOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutExternalTypeDeclarationOf(list of KClass) returns declaration without any of given external types`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutExternalTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutExternalTypeDeclarationOf(set of KClass) returns declaration without any of given external types`() {
        // given
        val declaration1: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns true
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declaration2: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns true
            }
        val declaration3: KoTypeDeclarationProvider =
            mockk {
                every { hasExternalTypeDeclarationOf(SampleType1::class) } returns false
                every { hasExternalTypeDeclarationOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutExternalTypeDeclarationOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
