package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.validator.LayerValidatorManager
import com.lemonappdev.konsist.core.exception.KoInvalidAssertArchitectureConfigurationException
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerDependenciesCoreTest {
    private val layerValidatorManager: LayerValidatorManager = mockk()

    private val sut =
        LayerDependenciesCore(
            layerValidatorManager,
        )

    // region dependsOn
    @Test
    fun `dependsOn throws exception for empty set`() {
        // given
        val layer = Layer("name", "package..")

        // when
        val func = {
            with(sut) {
                layer.dependsOn(emptySet())
            }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "Layers set is empty."
    }

    @Test
    fun `dependsOn throws exception for duplicated layer dependency`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        // when
        val func = {
            with(sut) {
                layer1.dependsOn(layer2)
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Duplicate layer dependency configuration: Layer 'name1' is already configured to depend on 'name2'."
    }

    @Test
    fun `dependsOn throws exception when when layer is not dependant on itself`() {
        // given
        val layer = Layer("name", "package..")

        // when
        val func = {
            with(sut) {
                layer.dependsOn(layer)
            }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "Layer 'name' cannot be dependent on itself."
    }

    @Test
    fun `dependsOn with vararg arguments has all layers`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.dependsOn(layer2, layer3)
        }

        // then
        sut.layers shouldBeEqualTo setOf(layer1, layer2, layer3)
    }

    @Test
    fun `dependsOn with set argument has all layers`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.dependsOn(setOf(layer2, layer3))
        }

        // then
        sut.layers shouldBeEqualTo setOf(layer1, layer2, layer3)
    }

    @Test
    fun `dependsOn add layers to layerDependencies`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.dependsOn(setOf(layer2, layer3))
        }

        // then
        sut.layerDependencies shouldContainAll
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer3),
            )
    }

    @Test
    fun `dependsOn call validateLayerDependencies`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.dependsOn(setOf(layer2, layer3))
        }

        // then
        verify { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }
    }

    @Test
    fun `dependsOn with multiple layers passed throws exception when layer already depends on nothing`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        with(sut) {
            layer1.dependsOnNothing()
        }

        // when
        val func = {
            with(sut) {
                layer1.dependsOn(layer2, layer3)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured with no dependencies. It cannot subsequently depend on layers 'name2', 'name3'."
    }

    @Test
    fun `dependsOn with single layer passed throws exception when layer already depends on nothing and single layer is passed`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        with(sut) {
            layer1.dependsOnNothing()
        }

        // when
        val func = {
            with(sut) {
                layer1.dependsOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured with no dependencies. It cannot subsequently depend on layer 'name2'."
    }

    // endregion

    //region doesNotDependOn
    @Test
    fun `doesNotDependOn throws exception for empty set`() {
        // given
        val layer = Layer("name", "package..")

        // when
        val func = {
            with(sut) {
                layer.doesNotDependOn(emptySet())
            }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "Layers set is empty."
    }

    @Test
    fun `doesNotDependOn throws exception for duplicated layer dependency`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        // when
        val func = {
            with(sut) {
                layer1.doesNotDependOn(layer2)
                layer1.doesNotDependOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Duplicate layer dependency configuration: Layer 'name1' is already configured to depend on 'name2'."
    }

    @Test
    fun `doesNotDependOn add layers to layerDependencies`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.doesNotDependOn(setOf(layer2, layer3))
        }

        // then
        sut.layerDependencies shouldContainAll
            setOf(
                LayerDependency(layer1, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, layer2),
                LayerDependency(layer1, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, layer3),
            )
    }

    @Test
    fun `doesNotDependOn throws exception when layer attempts to not depend on itself`() {
        // given
        val layer = Layer("name", "package..")

        // when
        val func = {
            with(sut) {
                layer.doesNotDependOn(layer)
            }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "Layer 'name' cannot be dependent on itself."
    }

    @Test
    fun `doesNotDependOn with vararg arguments has all layers`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.doesNotDependOn(layer2, layer3)
        }

        // then
        sut.layers shouldBeEqualTo setOf(layer1, layer2, layer3)
    }

    @Test
    fun `doesNotDependOn with set argument has all layers`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.doesNotDependOn(setOf(layer2, layer3))
        }

        // then
        sut.layers shouldBeEqualTo setOf(layer1, layer2, layer3)
    }

    @Test
    fun `doesNotDependOn with multiple layers passed throws exception when layer already depends on nothing`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        with(sut) {
            layer1.dependsOnNothing()
        }

        // when
        val func = {
            with(sut) {
                layer1.doesNotDependOn(layer2, layer3)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured with no dependencies. It cannot subsequently depend on layers 'name2', 'name3'."
    }

    @Test
    fun `doesNotDependOn with single layer passed throws exception when layer already depends on nothing and single layer is passed`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        with(sut) {
            layer1.dependsOnNothing()
        }

        // when
        val func = {
            with(sut) {
                layer1.doesNotDependOn(layer2)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured with no dependencies. It cannot subsequently depend on layer 'name2'."
    }

    @Test
    fun `doesNotDependOn call validateLayerDependencies`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.doesNotDependOn(setOf(layer2, layer3))
        }

        // then
        verify { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }
    }

    //endregion

    //region dependsOnNothing

    @Test
    fun `dependsOnNothing add has layer`() {
        // given
        val layer = Layer("name", "package..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer.dependsOnNothing()
        }

        // then
        sut.layers shouldBeEqualTo setOf(layer)
    }

    @Test
    fun `dependsOnNothing throws exception for duplicated layer dependency`() {
        // given
        val layer = Layer("layer name", "package1..")

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        // when
        val func = {
            with(sut) {
                layer.dependsOnNothing()
                layer.dependsOnNothing()
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Duplicate layer dependency configuration: Layer 'layer name' is already configured to depend on nothing."
    }

    @Test
    fun `dependsOnNothing add layer to layerDependencies`() {
        // given
        val layer = Layer("name", "package..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer.dependsOnNothing()
        }

        // then
        sut.layerDependencies shouldContainAll
            setOf(
                LayerDependency(layer, LayerDependencyType.DEPEND_ON_NOTHING, null),
            )
    }

    @Test
    fun `dependsOnNothing with single layer throws exception when layer already depends on single layer`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        with(sut) {
            layer1.dependsOn(layer2)
        }

        // when
        val func = {
            with(sut) {
                layer1.dependsOnNothing()
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured to depend on layer 'name2'. It cannot subsequently have no dependencies."
    }

    @Test
    fun `dependsOnNothing throws exception when layer already depends on multiple layers`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")
        val layer3 = Layer("name3", "package3..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        with(sut) {
            layer1.dependsOn(layer2, layer3)
        }

        // when
        val func = {
            with(sut) {
                layer1.dependsOnNothing()
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer 'name1' is already configured to depend on layers 'name2', 'name3'. It cannot subsequently have no dependencies."
    }

    @Test
    fun `dependsOnNothing call validateLayerDependencies`() {
        // given
        val layer1 = Layer("name1", "package1..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        with(sut) {
            layer1.dependsOnNothing()
        }

        // then
        verify { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }
    }

    //endregion
}
