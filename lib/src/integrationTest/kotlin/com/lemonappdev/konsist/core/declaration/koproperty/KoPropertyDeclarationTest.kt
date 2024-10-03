package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.properties
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationTest {
    @Test
    fun `property-to-string`() {
        // given
        val sut =
            getSnippetFile("property-to-string")
                .properties()
                .first()

        // then
        sut.toString() shouldBeEqualTo "sampleProperty"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forgeneral/", fileName)
}
