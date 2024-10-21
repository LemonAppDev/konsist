package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoTypeDeclarationProviderTest {
    @Test
    fun `star-projection-type-declaration`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-declaration")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut) {
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.hasExternalTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
            it?.hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkotypedeclarationprovider/",
            fileName,
        )
}
