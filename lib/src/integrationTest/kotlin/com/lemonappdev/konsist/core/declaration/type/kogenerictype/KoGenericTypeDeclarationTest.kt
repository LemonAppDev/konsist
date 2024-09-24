package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        sut.toString() shouldBeEqualTo "List<Set<String>>"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forgeneral/", fileName)
}
