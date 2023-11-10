package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoKotlinTypeProviderTest {
    @Test
    fun `basic-types`() {
        // given
        val sut = getSnippetFile("basic-types")
            .properties()

        // then
        assertSoftly {
            sut.forEach { property ->
                property.type?.isKotlinBasicType shouldBeEqualTo true
            }
        }
    }

    @Test
    fun `non-basic-types`() {
        // given
        val sut = getSnippetFile("non-basic-types")
            .properties()

        // then
        assertSoftly {
            sut.forEach { property ->
                property.type?.isKotlinBasicType shouldBeEqualTo false
            }
        }
    }

    @Test
    fun `collection-types`() {
        // given
        val sut = getSnippetFile("collection-types")
            .properties()

        // then
        assertSoftly {
            sut.forEach { property ->
                println("${property.type} ${property.type?.isKotlinCollectionType}")
                property.type?.isKotlinCollectionType shouldBeEqualTo true
            }
        }
    }

    @Test
    fun `non-collection-types`() {
        // given
        val sut = getSnippetFile("non-collection-types")
            .properties()

        // then
        assertSoftly {
            sut.forEach { property ->
                property.type?.isKotlinCollectionType shouldBeEqualTo false
            }
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/forkokotlintypeprovider/", fileName)
}
