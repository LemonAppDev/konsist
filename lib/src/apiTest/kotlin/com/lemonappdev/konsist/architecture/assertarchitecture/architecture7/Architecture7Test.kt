package com.lemonappdev.konsist.architecture.assertarchitecture.architecture7

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture7Test {
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture7/project",
        )

    @Test
    fun `passes when good dependency is set (scope)`() {
        // then
        scope
            .assertArchitecture {
                val adapter =
                    Layer("Adapter", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.adapter..")
                val common =
                    Layer("Common", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.common..")
                val domain = Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.domain..")
                val port =
                    Layer("Port", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.port..")

                domain.dependsOn(common)
                adapter.dependsOn(common)
                port.dependsOn(domain, common)
                adapter.dependsOn(port)
                common.dependsOnNothing()
            }
    }

    @Test
    fun `passes when good dependency is set (files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                val adapter =
                    Layer("Adapter", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.adapter..")
                val common =
                    Layer("Common", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.common..")
                val domain = Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.domain..")
                val port =
                    Layer("Port", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.port..")

                domain.dependsOn(common)
                adapter.dependsOn(common)
                port.dependsOn(domain, common)
                adapter.dependsOn(port)
                common.dependsOnNothing()
            }
    }
}
