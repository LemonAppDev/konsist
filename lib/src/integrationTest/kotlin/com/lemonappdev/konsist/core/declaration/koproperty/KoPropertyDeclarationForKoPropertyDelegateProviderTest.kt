package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPropertyDeclarationForKoPropertyDelegateProviderTest {
    @Test
    fun `property-has-lazy-delegate`() {
        // given
        val sut =
            getSnippetFile("property-has-lazy-delegate")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo "lazy"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("lazy") shouldBeEqualTo true
            hasDelegate("Delegates.observable()") shouldBeEqualTo false
            hasLazyDelegate() shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForBuiltInDelegates")
    fun `property-has-built-in-delegate`(
        fileName: String,
        builtInDelegate: String?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties()
                .first { it.name == "fixtureProperty" }

        // then
        assertSoftly(sut) {
            hasDelegate() shouldBeEqualTo true
            hasLazyDelegate() shouldBeEqualTo (builtInDelegate == "lazy")
            hasObservableDelegate shouldBeEqualTo (builtInDelegate == "observable")
            hasVetoableDelegate shouldBeEqualTo (builtInDelegate == "vetoable")
            hasNotNullDelegate shouldBeEqualTo (builtInDelegate == "notNull")
            hasPropertyReferenceDelegate shouldBeEqualTo (builtInDelegate == "propertyReference")
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForLazyDelegateThreadSafetyMode")
    fun `property-has-lazy-delegate-with-thread-safety-mode`(
        fileName: String,
        mode: LazyThreadSafetyMode?,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties()
                .first { it.name == "fixtureProperty" }

        // then
        assertSoftly(sut) {
            hasLazyDelegate() shouldBeEqualTo true
            LazyThreadSafetyMode.entries.forEach { entry ->
                hasLazyDelegate(entry) shouldBeEqualTo (entry == mode)
            }
        }
    }

    @Test
    fun `property-has-no-delegate`() {
        // given
        val sut =
            getSnippetFile("property-has-no-delegate")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
            hasLazyDelegate() shouldBeEqualTo false
            hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) shouldBeEqualTo false
            hasObservableDelegate shouldBeEqualTo false
            hasVetoableDelegate shouldBeEqualTo false
            hasNotNullDelegate shouldBeEqualTo false
            hasPropertyReferenceDelegate shouldBeEqualTo false
        }
    }

    @Test
    fun `property-defined-in-constructor-has-no-delegate`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-delegate")
                .classes()
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
            hasLazyDelegate() shouldBeEqualTo false
            hasLazyDelegate(LazyThreadSafetyMode.SYNCHRONIZED) shouldBeEqualTo false
            hasObservableDelegate shouldBeEqualTo false
            hasVetoableDelegate shouldBeEqualTo false
            hasNotNullDelegate shouldBeEqualTo false
            hasPropertyReferenceDelegate shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkopropertydelegateprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForBuiltInDelegates() =
            listOf(
                arguments("property-has-lazy-delegate", "lazy"),
                arguments("property-has-lazy-delegate-with-none-mode", "lazy"),
                arguments("property-has-lazy-delegate-with-type-argument", "lazy"),
                arguments("property-has-observable-delegate", "observable"),
                arguments("property-has-fully-qualified-observable-delegate", "observable"),
                arguments("property-has-vetoable-delegate", "vetoable"),
                arguments("property-has-not-null-delegate", "notNull"),
                arguments("property-has-not-null-delegate-with-type-argument", "notNull"),
                arguments("property-has-property-reference-delegate", "propertyReference"),
                arguments("property-has-property-reference-delegate-with-receiver", "propertyReference"),
                arguments("property-has-custom-delegate", null),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForLazyDelegateThreadSafetyMode() =
            listOf(
                arguments("property-has-lazy-delegate", LazyThreadSafetyMode.SYNCHRONIZED),
                arguments("property-has-lazy-delegate-with-type-argument", LazyThreadSafetyMode.SYNCHRONIZED),
                arguments("property-has-lazy-delegate-with-synchronized-mode", LazyThreadSafetyMode.SYNCHRONIZED),
                arguments("property-has-lazy-delegate-with-publication-mode", LazyThreadSafetyMode.PUBLICATION),
                arguments("property-has-lazy-delegate-with-none-mode", LazyThreadSafetyMode.NONE),
                arguments("property-has-lazy-delegate-with-fully-qualified-none-mode", LazyThreadSafetyMode.NONE),
                arguments("property-has-lazy-delegate-with-imported-none-mode", LazyThreadSafetyMode.NONE),
                arguments("property-has-lazy-delegate-with-named-none-mode", LazyThreadSafetyMode.NONE),
                arguments("property-has-lazy-delegate-with-none-mode-and-initializer-in-parentheses", LazyThreadSafetyMode.NONE),
                arguments("property-has-lazy-delegate-with-lock", null),
                arguments("property-has-lazy-delegate-with-mode-variable", null),
            )
    }
}
