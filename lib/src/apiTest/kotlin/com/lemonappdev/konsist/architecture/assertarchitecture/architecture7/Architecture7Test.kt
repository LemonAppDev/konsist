package com.lemonappdev.konsist.architecture.assertarchitecture.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture7Test {
    private val adapter =
        Layer("Adapter", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.adapter..")
    private val common =
        Layer("common", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.common..")
    private val domain = Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.domain..")
    private val port =
        Layer("Port", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.port..")
    private val scope = Konsist.scopeFromDirectory(
        "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture7/project",
    )

    @Test
    fun `passes when good dependency is set`() {
        // then
        scope
            .assertArchitecture {
                port.dependsOn(domain)
                adapter.dependsOn(port)
                adapter.dependsOn(common)
                domain.dependsOnNothing()
            }
    }
}
