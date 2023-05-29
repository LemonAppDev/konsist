package com.lemonappdev.konsist.api.ext.sequence.kotypealiasdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withSourceType
import com.lemonappdev.konsist.api.ext.sequence.withoutSourceType
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForTypeSequenceExtTest {
    @Test
    fun `withType(name) returns typeAliases which has one of given types`() {
        // given
        val type1 = "SampleType1"
        val type2 = "SampleType2"
        val type3 = "SampleType3"
        val typeAlias1: KoTypeAliasDeclarationImpl = mockk {
            every { type.sourceType } returns type1
        }
        val typeAlias2: KoTypeAliasDeclarationImpl = mockk {
            every { type.sourceType } returns type2
        }
        val typeAlias3: KoTypeAliasDeclarationImpl = mockk {
            every { type.sourceType } returns type3
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2, typeAlias3)

        // when
        val sut = typeAliases.withSourceType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias1, typeAlias2)
    }

    @Test
    fun `withoutType(name) returns typeAlias which has not any of given types`() {
        // given
        val type1 = "SampleType1"
        val type2 = "SampleType2"
        val type3 = "SampleType3"
        val typeAlias1: KoTypeAliasDeclarationImpl = mockk {
            every { type.sourceType } returns type1
        }
        val typeAlias2: KoTypeAliasDeclarationImpl = mockk {
            every { type.sourceType } returns type2
        }
        val typeAlias3: KoTypeAliasDeclarationImpl = mockk {
            every { type.sourceType } returns type3
        }
        val typeAliases = sequenceOf(typeAlias1, typeAlias2, typeAlias3)

        // when
        val sut = typeAliases.withoutSourceType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(typeAlias3)
    }
}
