// package com.lemonappdev.konsist.core.declaration.koparent
//
// import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
// import com.lemonappdev.konsist.api.ext.list.parents
// import org.amshove.kluent.assertSoftly
// import org.amshove.kluent.shouldBeEqualTo
// import org.junit.jupiter.api.Test
//
// class KoParentDeclarationForKoLocationProviderTest {
//    @Test
//    fun `parent-from-file-of-class-location`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-class-location")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:1:1"
//    }
//
//    @Test
//    fun `parent-from-file-of-class-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("parent-from-file-of-class-location-with-text")
//            .classes()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("parent-from-file-of-class-location-with-text")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        val declaration = "interface SampleSuperInterface"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-interface-location`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-interface-location")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:3:1"
//    }
//
//    @Test
//    fun `parent-from-file-of-interface-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("parent-from-file-of-interface-location-with-text")
//            .interfaces()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("parent-from-file-of-interface-location-with-text")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        val declaration = "interface SampleSuperInterface"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-object-location`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-object-location")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:1:1"
//    }
//
//    @Test
//    fun `parent-from-file-of-object-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("parent-from-file-of-object-location-with-text")
//            .objects()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("parent-from-file-of-object-location-with-text")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        val declaration = "interface SampleSuperInterface"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-class-location`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-class-location")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:55:1"
//    }
//
//    @Test
//    fun `parent-from-import-of-class-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("parent-from-import-of-class-location-with-text")
//            .classes()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("parent-from-import-of-class-location-with-text")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        val declaration = "interface SampleParentInterface"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-interface-location`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-interface-location")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:55:1"
//    }
//
//    @Test
//    fun `parent-from-import-of-interface-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("parent-from-import-of-interface-location-with-text")
//            .interfaces()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("parent-from-import-of-interface-location-with-text")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        val declaration = "interface SampleParentInterface"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-object-location`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-object-location")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:55:1"
//    }
//
//    @Test
//    fun `parent-from-import-of-object-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("parent-from-import-of-object-location-with-text")
//            .objects()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("parent-from-import-of-object-location-with-text")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        val declaration = "interface SampleParentInterface"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `external-parent-of-class-location`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-class-location")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:3:20"
//    }
//
//    @Test
//    fun `external-parent-of-class-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("external-parent-of-class-location-with-text")
//            .classes()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("external-parent-of-class-location-with-text")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        val declaration = "Declaration:\nExternalParent"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `external-parent-of-interface-location`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-interface-location")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:3:28"
//    }
//
//    @Test
//    fun `external-parent-of-interface-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("external-parent-of-interface-location-with-text")
//            .interfaces()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("external-parent-of-interface-location-with-text")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        val declaration = "Declaration:\nExternalParent"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `external-parent-of-object-location`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-object-location")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        sut.location shouldBeEqualTo "${sut.path}:3:22"
//    }
//
//    @Test
//    fun `external-parent-of-object-location-with-text`() {
//        // given
//        val projectPath = getSnippetFile("external-parent-of-object-location-with-text")
//            .objects()
//            .parents
//            .first()
//            .projectPath
//
//        val sut = getSnippetFile("external-parent-of-object-location-with-text")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        val declaration = "Declaration:\nExternalParent"
//        assertSoftly(sut.locationWithText) {
//            startsWith("Location: /") shouldBeEqualTo true
//            contains(projectPath) shouldBeEqualTo true
//            endsWith(declaration) shouldBeEqualTo true
//        }
//    }
//
//    private fun getSnippetFile(fileName: String) =
//        getSnippetKoScope("core/declaration/koparent/snippet/forkolocationprovider/", fileName)
// }
