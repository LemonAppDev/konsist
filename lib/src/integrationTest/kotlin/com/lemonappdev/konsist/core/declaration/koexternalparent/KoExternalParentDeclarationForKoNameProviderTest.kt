package com.lemonappdev.konsist.core.declaration.koexternalparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.externalParents
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalParentDeclarationForKoNameProviderTest {
    @Test
    fun `class-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalClass"
            hasName("SampleExternalClass") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalclass", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalclass", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericClass<Int>"
            hasName("SampleExternalGenericClass<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalgenericclass<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericclass<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalClassWithParameter"
            hasName("SampleExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericClassWithParameter<Int>"
            hasName("SampleExternalGenericClassWithParameter<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalgenericclasswithparameter<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericclasswithparameter<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-interface")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalInterface"
            hasName("SampleExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-interface")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericInterface<Int>"
            hasName("SampleExternalGenericInterface<Int>") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalgenericinterface<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericinterface<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-external-parent-by-delegation`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-by-delegation")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalInterface"
            hasName("SampleExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-external-parent")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalClassWithParameter"
            hasName("SampleExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-external-parent-interface")
                .interfaces()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalInterface"
            hasName("SampleExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-external-parent-interface")
                .interfaces()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericInterface<Int>"
            hasName("SampleExternalGenericInterface<Int>") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalgenericinterface<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericinterface<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalClass"
            hasName("SampleExternalClass") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalclass", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalclass", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericClass<Int>"
            hasName("SampleExternalGenericClass<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalgenericclass<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericclass<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalClassWithParameter"
            hasName("SampleExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericClassWithParameter<Int>"
            hasName("SampleExternalGenericClassWithParameter<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalgenericclasswithparameter<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericclasswithparameter<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-interface")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalInterface"
            hasName("SampleExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-interface")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalGenericInterface<Int>"
            hasName("SampleExternalGenericInterface<Int>") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("sampleexternalgenericinterface<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalgenericinterface<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-external-parent")
                .objects()
                .first()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleExternalClassWithParameter"
            hasName("SampleExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koexternalparent/snippet/forkonameprovider/", fileName)
}
