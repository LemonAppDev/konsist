package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTypeProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeProviderListExtTest {
    @Test
    fun `withClassType() returns types that are classes`() {
        // given
        val classType: KoTypeProvider = mockk { every { isClass } returns true }
        val nonClassType: KoTypeProvider = mockk { every { isClass } returns false }
        val types = listOf(classType, nonClassType)

        // when
        val result = types.withClassType()

        // then
        result shouldBeEqualTo listOf(classType)
    }

    @Test
    fun `withoutClassType() returns types that are not classes`() {
        // given
        val classType: KoTypeProvider = mockk { every { isClass } returns true }
        val notClassType: KoTypeProvider = mockk { every { isClass } returns false }
        val types = listOf(classType, notClassType)

        // when
        val result = types.withoutClassType()

        // then
        result shouldBeEqualTo listOf(notClassType)
    }

    @Test
    fun `withObjectType() returns types that are objects`() {
        // given
        val objectType: KoTypeProvider = mockk { every { isObject } returns true }
        val nonObjectType: KoTypeProvider = mockk { every { isObject } returns false }
        val types = listOf(objectType, nonObjectType)

        // when
        val result = types.withObjectType()

        // then
        result shouldBeEqualTo listOf(objectType)
    }

    @Test
    fun `withoutObjectType() returns types that are not objects`() {
        // given
        val objectType: KoTypeProvider = mockk { every { isObject } returns true }
        val notObjectType: KoTypeProvider = mockk { every { isObject } returns false }
        val types = listOf(objectType, notObjectType)

        // when
        val result = types.withoutObjectType()

        // then
        result shouldBeEqualTo listOf(notObjectType)
    }

    @Test
    fun `withInterfaceType() returns types that are interfaces`() {
        // given
        val interfaceType: KoTypeProvider = mockk { every { isInterface } returns true }
        val nonInterfaceType: KoTypeProvider = mockk { every { isInterface } returns false }
        val types = listOf(interfaceType, nonInterfaceType)

        // when
        val result = types.withInterfaceType()

        // then
        result shouldBeEqualTo listOf(interfaceType)
    }

    @Test
    fun `withoutInterfaceType() returns types that are not interfaces`() {
        // given
        val interfaceType: KoTypeProvider = mockk { every { isInterface } returns true }
        val notInterfaceType: KoTypeProvider = mockk { every { isInterface } returns false }
        val types = listOf(interfaceType, notInterfaceType)

        // when
        val result = types.withoutInterfaceType()

        // then
        result shouldBeEqualTo listOf(notInterfaceType)
    }

    @Test
    fun `withTypeAlias() returns types that are type aliases`() {
        // given
        val typeAlias: KoTypeProvider = mockk { every { isTypeAlias } returns true }
        val nonTypeAlias: KoTypeProvider = mockk { every { isTypeAlias } returns false }
        val types = listOf(typeAlias, nonTypeAlias)

        // when
        val result = types.withTypeAlias()

        // then
        result shouldBeEqualTo listOf(typeAlias)
    }

    @Test
    fun `withoutTypeAlias() returns types that are not type aliases`() {
        // given
        val typeAlias: KoTypeProvider = mockk { every { isTypeAlias } returns true }
        val notTypeAlias: KoTypeProvider = mockk { every { isTypeAlias } returns false }
        val types = listOf(typeAlias, notTypeAlias)

        // when
        val result = types.withoutTypeAlias()

        // then
        result shouldBeEqualTo listOf(notTypeAlias)
    }

    @Test
    fun `withImportAlias() returns types that are import aliases`() {
        // given
        val importAlias: KoTypeProvider = mockk { every { isImportAlias } returns true }
        val nonImportAlias: KoTypeProvider = mockk { every { isImportAlias } returns false }
        val types = listOf(importAlias, nonImportAlias)

        // when
        val result = types.withImportAlias()

        // then
        result shouldBeEqualTo listOf(importAlias)
    }

    @Test
    fun `withoutImportAlias() returns types that are not import aliases`() {
        // given
        val importAlias: KoTypeProvider = mockk { every { isImportAlias } returns true }
        val notImportAlias: KoTypeProvider = mockk { every { isImportAlias } returns false }
        val types = listOf(importAlias, notImportAlias)

        // when
        val result = types.withoutImportAlias()

        // then
        result shouldBeEqualTo listOf(notImportAlias)
    }

    @Test
    fun `withoutKotlinType() returns type without Kotlin basic type`() {
        // given
        val type1: KoTypeProvider =
            mockk {
                every { isKotlinType } returns true
            }
        val type2: KoTypeProvider =
            mockk {
                every { isKotlinType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withKotlinType() returns type with Kotlin basic type`() {
        // given
        val type1: KoTypeProvider =
            mockk {
                every { isKotlinType } returns true
            }
        val type2: KoTypeProvider =
            mockk {
                every { isKotlinType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withKotlinBasicType() returns type with Kotlin basic type`() {
        // given
        val type1: KoTypeProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val type2: KoTypeProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinBasicType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinBasicType() returns type without Kotlin basic type`() {
        // given
        val type1: KoTypeProvider =
            mockk {
                every { isKotlinBasicType } returns true
            }
        val type2: KoTypeProvider =
            mockk {
                every { isKotlinBasicType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinBasicType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withKotlinCollectionType() returns type with Kotlin basic type`() {
        // given
        val type1: KoTypeProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val type2: KoTypeProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withKotlinCollectionType()

        // then
        sut shouldBeEqualTo listOf(type1)
    }

    @Test
    fun `withoutKotlinCollectionType() returns type without Kotlin basic type`() {
        // given
        val type1: KoTypeProvider =
            mockk {
                every { isKotlinCollectionType } returns true
            }
        val type2: KoTypeProvider =
            mockk {
                every { isKotlinCollectionType } returns false
            }
        val types = listOf(type1, type2)

        // when
        val sut = types.withoutKotlinCollectionType()

        // then
        sut shouldBeEqualTo listOf(type2)
    }

    @Test
    fun `withTypeParameter() returns types that are type parameters`() {
        // given
        val typeParameter: KoTypeProvider = mockk { every { isTypeParameter } returns true }
        val nonTypeParameter: KoTypeProvider = mockk { every { isTypeParameter } returns false }
        val types = listOf(typeParameter, nonTypeParameter)

        // when
        val result = types.withTypeParameter()

        // then
        result shouldBeEqualTo listOf(typeParameter)
    }

    @Test
    fun `withoutTypeParameter() returns types that are not type parameters`() {
        // given
        val typeParameter: KoTypeProvider = mockk { every { isTypeParameter } returns true }
        val notTypeParameter: KoTypeProvider = mockk { every { isTypeParameter } returns false }
        val types = listOf(typeParameter, notTypeParameter)

        // when
        val result = types.withoutTypeParameter()

        // then
        result shouldBeEqualTo listOf(notTypeParameter)
    }

    @Test
    fun `withExternalType() returns types that are external`() {
        // given
        val externalType: KoTypeProvider = mockk { every { isExternalType } returns true }
        val nonExternalType: KoTypeProvider = mockk { every { isExternalType } returns false }
        val types = listOf(externalType, nonExternalType)

        // when
        val result = types.withExternalType()

        // then
        result shouldBeEqualTo listOf(externalType)
    }

    @Test
    fun `withoutExternalType() returns types that are not external types`() {
        // given
        val externalType: KoTypeProvider = mockk { every { isExternalType } returns true }
        val notExternalType: KoTypeProvider = mockk { every { isExternalType } returns false }
        val types = listOf(externalType, notExternalType)

        // when
        val result = types.withoutExternalType()

        // then
        result shouldBeEqualTo listOf(notExternalType)
    }
}
