package com.lemonappdev.konsist.core.provider.koaliasprovider

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoAliasProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAliasProviderTest {
    @Test
    fun `import-without-import-alias`() {
        // given
        val sut = getSnippetFile("import-without-import-alias")
            .declarations()
            .filterIsInstance<KoAliasProvider>()
            .first()

        // then
        sut.alias shouldBeEqualTo null
    }

    @Test
    fun `import-with-import-alias`() {
        // given
        val sut = getSnippetFile("import-with-import-alias")
            .declarations()
            .filterIsInstance<KoAliasProvider>()

        // then
        assertSoftly(sut.toList()) {
            get(0).alias shouldBeEqualTo null
            get(1).alias shouldBeEqualTo "ImportAlias"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/provider/koaliasprovider/snippet/", fileName)
}
