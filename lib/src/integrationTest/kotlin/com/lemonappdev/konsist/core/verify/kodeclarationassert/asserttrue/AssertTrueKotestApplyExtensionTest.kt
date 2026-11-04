package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.KoKotestExtension
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldStartWith

@ApplyExtension(KoKotestExtension::class)
class AssertTrueKotestApplyExtensionTest :
    FunSpec({
        test("kotest test with applied extension") {
            // given
            val sut =
                TestSnippetProvider
                    .getSnippetKoScope(
                        "core/verify/kodeclarationassert/asserttrue/snippet/",
                        "assert-test-name-derived-from-dynamic-test",
                    ).classes()
                    .first()

            // when
            val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

            // then
            result.message?.shouldStartWith("Assert 'kotest test with applied extension' was violated (1 time)")
        }
    })
