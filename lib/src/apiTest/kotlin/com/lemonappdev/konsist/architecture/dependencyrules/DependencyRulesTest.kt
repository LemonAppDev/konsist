package com.lemonappdev.konsist.architecture.dependencyrules

import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class DependencyRulesTest {
    @Test
    fun `throws an exception when a layer with the same name already exists`() {
        // given
        val layer1 = Layer("Name", "package1..")
        val layer2 = Layer("Name", "package2..")

        val sut = {
            architecture {
                layer1.dependsOn(layer2)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Layers have the same name name: Name.
            """.trimIndent()
    }

    @Test
    fun `throws an exception when a layer with the same definedBy already exists`() {
        // given
        val layer1 = Layer("Name1", "package..")
        val layer2 = Layer("Name2", "package..")

        val sut = {
            architecture {
                layer1.dependsOn(layer2)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Layers have the same name definedBy: package.. .
            """.trimIndent()
    }

    @Test
    fun `throws an exception when self dependency is set`() {
        // given
        val layer = Layer("Name", "package..")

        val sut = {
            architecture { layer.dependsOn(layer) }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage "Layer Name cannot be dependent on itself."
    }

    @Test
    fun `throws an exception when layer is set as independent and then set as depend on other layer`() {
        // given
        val layer1 = Layer("Name1", "package1..")
        val layer2 = Layer("Name2", "package2..")

        val sut = {
            architecture {
                layer1.dependsOnNothing()
                layer1.dependsOn(layer2)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Layer Name1 was previously set as depend on nothing, so it cannot depend on Name2 layer.
            """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as independent and then set as depend on other layer after other dependencies are set`() {
        // given
        val layer1 = Layer("Name1", "package1..")
        val layer2 = Layer("Name2", "package2..")
        val layer3 = Layer("Name3", "package3..")

        val sut = {
            architecture {
                layer1.dependsOnNothing()
                layer3.dependsOn(layer1)
                layer1.dependsOn(layer2)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Layer Name1 was previously set as depend on nothing, so it cannot depend on Name2 layer.
            """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as independent twice`() {
        // given
        val layer = Layer("Name", "package..")

        val sut = {
            architecture {
                layer.dependsOnNothing()
                layer.dependsOnNothing()
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Duplicated the dependency that Name layer should be depend on nothing.
            """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as dependent on other layer and then as independent`() {
        // given
        val layer1 = Layer("Name1", "package1..")
        val layer2 =
            Layer("Name2", "package2..")

        val sut = {
            architecture {
                layer1.dependsOn(layer2)
                layer1.dependsOnNothing()
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Layer Name1 had a dependency previously set with Name2 layer, so it cannot be depend on nothing.
            """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as dependent on the same layer twice`() {
        // given
        val layer1 = Layer("Name1", "package1..")
        val layer2 =
            Layer("Name2", "package2..")

        val sut = {
            architecture {
                layer1.dependsOn(layer2)
                layer1.dependsOn(layer2)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage "Duplicated the dependency between Name1 and Name2 layers."
    }
}
