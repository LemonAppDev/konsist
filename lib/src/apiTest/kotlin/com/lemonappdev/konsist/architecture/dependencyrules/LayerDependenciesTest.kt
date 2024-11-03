package com.lemonappdev.konsist.architecture.dependencyrules

import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoInvalidAssertArchitectureConfigurationException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerDependenciesTest {
    @Test
    fun `throws an exception when a layer with the same name already exists`() {
        // given
        val layer1 = Layer("name", "package1..")
        val layer2 = Layer("name", "package2..")

        val func = {
            architecture {
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layers configuration:\n" +
                "└── Layer name must be unique. Duplicated name: 'name'."
    }

    @Test
    fun `throws an exception when a layer with the same rootPackage already exists`() {
        // given
        val layer1 = Layer("name1", "package..")
        val layer2 = Layer("name2", "package..")

        val func = {
            architecture {
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layers configuration:\n" +
                "└── Layer rootPackage must be unique. Duplicated rootPackage: 'package..'."
    }

    @Test
    fun `throws an exception when self dependency is set`() {
        // given
        val layer = Layer("name", "package..")

        val func = {
            architecture { layer.dependsOn(layer) }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "Layer 'name' cannot be dependent on itself."
    }

    @Test
    fun `throws an exception when layer is set as independent and then set as depend on other layer`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        val func = {
            architecture {
                layer1.dependsOnNothing()
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured with no dependencies. It cannot subsequently depend on layer 'name2'."
    }

    @Test
    fun `throws an exception when layer is set as independent and then set as depend on other layer after other dependencies are set`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        val func = {
            architecture {
                layer1.dependsOnNothing()
                layer3.dependsOn(layer1)
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured with no dependencies. It cannot subsequently depend on layer 'name2'."
    }

    @Test
    fun `throws an exception when layer is set as independent twice`() {
        // given
        val layer = Layer("name", "package..")

        val func = {
            architecture {
                layer.dependsOnNothing()
                layer.dependsOnNothing()
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Duplicate layer dependency configuration: Layer 'name' is already configured to depend on 'null'."
    }

    @Test
    fun `throws an exception when layer is set as dependent on other layer and then as independent`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 =
            Layer("name2", "package2..")

        val func = {
            architecture {
                layer1.dependsOn(layer2)
                layer1.dependsOnNothing()
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured to depend on layer 'name2'. It cannot subsequently have no dependencies."
    }

    @Test
    fun `throws an exception when layer is set as dependent on the same layer twice`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 =
            Layer("name2", "package2..")

        val func = {
            architecture {
                layer1.dependsOn(layer2)
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
                "Duplicate layer dependency configuration: Layer 'name1' is already configured to depend on 'name2'."
    }
}
