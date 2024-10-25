package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("detekt.LongMethod")
class KoParentDeclarationForKoArgumentProviderTest {
    @ParameterizedTest
    @MethodSource("provideClassesForNoArgument")
    fun `class-parent-has-no-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName(emptyList()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideClassesForArgument")
    fun `class-parent-has-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("param")
            numArguments shouldBeEqualTo 1
            countArguments { it.value == "param" } shouldBeEqualTo 1
            countArguments { it.value == "1" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo true
            hasArgumentWithName(emptyList()) shouldBeEqualTo true
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasArgumentWithName("param") shouldBeEqualTo false
            hasArgumentWithName("param", "otherParameter") shouldBeEqualTo false
            hasArgumentWithName(listOf("param")) shouldBeEqualTo false
            hasArgumentWithName(listOf("param", "otherParameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames("param") shouldBeEqualTo false
            hasArgumentsWithAllNames("param", "otherParameter") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("param")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("param", "otherParameter")) shouldBeEqualTo false
            hasArgument { it.value == "param" } shouldBeEqualTo true
            hasArgument { it.value == "1" } shouldBeEqualTo false
            hasAllArguments { it.value == "param" } shouldBeEqualTo true
            hasAllArguments { it.value == "1" } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForNoArgument")
    fun `interface-parent-has-no-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName(emptyList()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForNoArgument")
    fun `object-parent-has-no-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName(emptyList()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForArgument")
    fun `object-parent-has-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("param")
            numArguments shouldBeEqualTo 1
            countArguments { it.value == "param" } shouldBeEqualTo 1
            countArguments { it.value == "1" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo true
            hasArgumentWithName(emptyList()) shouldBeEqualTo true
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasArgumentWithName("param") shouldBeEqualTo false
            hasArgumentWithName("param", "otherParameter") shouldBeEqualTo false
            hasArgumentWithName(listOf("param")) shouldBeEqualTo false
            hasArgumentWithName(listOf("param", "otherParameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames("param") shouldBeEqualTo false
            hasArgumentsWithAllNames("param", "otherParameter") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("param")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("param", "otherParameter")) shouldBeEqualTo false
            hasArgument { it.value == "param" } shouldBeEqualTo true
            hasArgument { it.value == "1" } shouldBeEqualTo false
            hasAllArguments { it.value == "param" } shouldBeEqualTo true
            hasAllArguments { it.value == "1" } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkoargumentprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClassesForNoArgument() =
            listOf(
                arguments("class-with-parent-class-from-file"),
                arguments("class-with-parent-interface-from-file"),
                arguments("class-with-parent-by-delegation-from-file"),
                arguments("class-with-parent-class-from-import"),
                arguments("class-with-parent-interface-from-import"),
                arguments("class-with-parent-by-delegation-from-import"),
                arguments("class-with-external-parent-class"),
                arguments("class-with-external-parent-interface"),
                arguments("class-with-external-parent-by-delegation"),
                arguments("class-with-generic-parent-class-from-file"),
                arguments("class-with-generic-parent-interface-from-file"),
                arguments("class-with-generic-parent-class-from-import"),
                arguments("class-with-generic-parent-interface-from-import"),
                arguments("class-with-generic-external-parent-class"),
                arguments("class-with-generic-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideClassesForArgument() =
            listOf(
                arguments("class-with-parametrized-parent-class-from-file"),
                arguments("class-with-parametrized-parent-class-from-import"),
                arguments("class-with-parametrized-external-parent-class"),
                arguments("class-with-parametrized-and-generic-parent-class-from-file"),
                arguments("class-with-parametrized-and-generic-parent-class-from-import"),
                arguments("class-with-parametrized-and-generic-external-parent-class"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoArgument() =
            listOf(
                arguments("interface-with-parent-interface-from-file"),
                arguments("interface-with-parent-interface-from-import"),
                arguments("interface-with-external-parent-interface"),
                arguments("interface-with-generic-parent-interface-from-file"),
                arguments("interface-with-generic-parent-interface-from-import"),
                arguments("interface-with-generic-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForNoArgument() =
            listOf(
                arguments("object-with-parent-class-from-file"),
                arguments("object-with-parent-interface-from-file"),
                arguments("object-with-parent-class-from-import"),
                arguments("object-with-parent-interface-from-import"),
                arguments("object-with-external-parent-class"),
                arguments("object-with-external-parent-interface"),
                arguments("object-with-generic-parent-class-from-file"),
                arguments("object-with-generic-parent-interface-from-file"),
                arguments("object-with-generic-parent-class-from-import"),
                arguments("object-with-generic-parent-interface-from-import"),
                arguments("object-with-generic-external-parent-class"),
                arguments("object-with-generic-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForArgument() =
            listOf(
                arguments("object-with-parametrized-parent-class-from-file"),
                arguments("object-with-parametrized-parent-class-from-import"),
                arguments("object-with-parametrized-external-parent-class"),
                arguments("object-with-parametrized-and-generic-parent-class-from-file"),
                arguments("object-with-parametrized-and-generic-parent-class-from-import"),
                arguments("object-with-parametrized-and-generic-external-parent-class"),
            )
    }
}
