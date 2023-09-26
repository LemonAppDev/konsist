package temporary

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import io.kotest.core.spec.style.FreeSpec

class KoTestSnippets {
    class UseCaseTest : FreeSpec({
        "UseCase has test class" {
            Konsist
                .scopeFromProject()
                .classes()
                .withNameEndingWith("UseCase")
                .assertTrue { it.hasTestClass() }
        }
    })

    class UseCaseTests : FreeSpec({
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .forEach { useCase ->
                "${useCase.name} should have test" {
                    useCase.assertTrue { it.hasTestClass() }
                }
                "${useCase.name} should reside in ..domain..usecase.. package" {
                    useCase.assertTrue { it.resideInPackage("..domain..usecase..") }
                }
                "${useCase.name} should ..." {
                    // another Konsist assert
                }
            }
    })
}
