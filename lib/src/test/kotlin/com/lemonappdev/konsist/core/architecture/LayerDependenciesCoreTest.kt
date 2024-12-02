package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.validator.LayerValidatorManager
import com.lemonappdev.konsist.core.exception.KoInvalidAssertArchitectureConfigurationException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
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

    // region general
    @Test
    fun `checkEmptyLayersDependencies throws exception for empty set`() {
        // when
        val func = {
            sut.checkEmptyLayersDependencies()
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `properties return correct results when mixed dependencies are defined`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val layer3: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.dependsOn(layer2)
            layer2.doesNotDependOn(layer3)
            layer3.dependsOnNothing()
        }

        // when
        val dependsOn = sut.dependsOnDependencies
        val doesNotDependOn = sut.doesNotDependsOnDependencies
        val dependsOnNothing = sut.dependsOnNothingDependencies

        // then
        dependsOn shouldBeEqualTo
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
            )

        doesNotDependOn shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
            )

        dependsOnNothing shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer3,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                    strict = false,
                ),
            )
    }
    // endregion

    // region dependsOnDependencies
    @Test
    fun `dependsOnDependencies is empty by default`() {
        // when
        val result = sut.dependsOnDependencies

        // then
        result.shouldBeEmpty()
    }

    @Test
    fun `dependsOnDependencies returns correct dependencies for single layer`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.dependsOn(layer2)
        }

        // when
        val result = sut.dependsOnDependencies

        // then
        result shouldBeEqualTo
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
            )
    }

    @Test
    fun `dependsOnDependencies returns correct dependencies for multiple layers`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val layer3: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.dependsOn(layer2)
            layer2.dependsOn(layer3)
        }

        // when
        val result = sut.dependsOnDependencies

        // then
        result shouldBeEqualTo
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
            )
    }

    @Test
    fun `dependsOnDependencies returns correct dependencies when layer depends on multiple layers`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val layer3: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.dependsOn(setOf(layer2, layer3))
        }

        // when
        val result = sut.dependsOnDependencies

        // then
        result shouldBeEqualTo
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
            )
    }
    // endregion

    // region doesNotDependsOnDependencies
    @Test
    fun `doesNotDependsOnDependencies is empty by default`() {
        // when
        val result = sut.doesNotDependsOnDependencies

        // then
        result.shouldBeEmpty()
    }

    @Test
    fun `doesNotDependsOnDependencies returns correct dependencies for single layer`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.doesNotDependOn(layer2)
        }

        // when
        val result = sut.doesNotDependsOnDependencies

        // then
        result shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
            )
    }

    @Test
    fun `doesNotDependsOnDependencies returns correct dependencies for multiple layers`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val layer3: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.doesNotDependOn(layer2)
            layer2.doesNotDependOn(layer3)
        }

        // when
        val result = sut.doesNotDependsOnDependencies

        // then
        result shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
            )
    }

    @Test
    fun `doesNotDependsOnDependencies returns correct dependencies when layer does not depend on multiple layers`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val layer3: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.doesNotDependOn(setOf(layer2, layer3))
        }

        // when
        val result = sut.doesNotDependsOnDependencies

        // then
        result shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
            )
    }
    // endregion

    // region dependsOnNothingDependencies
    @Test
    fun `dependsOnNothingDependencies is empty by default`() {
        // when
        val result = sut.dependsOnNothingDependencies

        // then
        result.shouldBeEmpty()
    }

    @Test
    fun `dependsOnNothingDependencies returns correct layers for single layer`() {
        // given
        val layer1: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.dependsOnNothing()
        }

        // when
        val result = sut.dependsOnNothingDependencies

        // then
        result shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                    strict = false,
                ),
            )
    }

    @Test
    fun `dependsOnNothingDependencies returns correct layers for multiple layers`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()

        justRun { layerValidatorManager.validateLayerDependencies(sut.layerDependencies) }

        with(sut) {
            layer1.dependsOnNothing()
            layer2.dependsOnNothing()
        }

        // when
        val result = sut.dependsOnNothingDependencies

        // then
        result shouldBeEqualTo
            listOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                    strict = false,
                ),
            )
    }
    // endregion

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
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
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

    @Test
    fun `dependsOn throws exception for forbidden strict override`() {
        // given
        val layer1 = Layer("name1", "package1..")
        val layer2 = Layer("name2", "package2..")

        justRun { layerValidatorManager.validateLayerDependencies(any()) }

        // when
        val func = {
            with(sut) {
                layer1.dependsOn(layer2, strict = false)
                layer1.dependsOn(layer2, strict = true)
            }
        }

        // then
        func shouldThrow KoInvalidAssertArchitectureConfigurationException::class withMessage
            "Layer dependency configuration for layer 'name1' is already defined with a strict=false value. " +
            "It cannot be overridden with strict=true."
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
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer2,
                    strict = false,
                ),
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer3,
                    strict = false,
                ),
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
                LayerDependency(
                    layer1 = layer,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                    layer2 = null,
                    strict = false,
                ),
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

    // region include
    @Test
    fun `include adds layer to layers collection`() {
        // given
        val sut = LayerDependenciesCore()
        val layer = Layer("Domain", "com.example.domain..")

        // when
        with(sut) {
            layer.include()
        }

        // then
        sut.layers shouldContain layer
    }

    @Test
    fun `include creates dependency with NONE type and null target`() {
        // given
        val sut = LayerDependenciesCore()
        val layer = Layer("Domain", "com.example.domain..")
        val expectedDependency = LayerDependency(layer, LayerDependencyType.NONE, null)

        // when
        with(sut) {
            layer.include()
        }

        // then
        sut.layerDependencies shouldContain expectedDependency
        sut.layerDependencies.first() shouldBeEqualTo expectedDependency
    }

    @Test
    fun `include can be called multiple times for different layers`() {
        // given
        val sut = LayerDependenciesCore()
        val layer1 = Layer("Domain", "com.example.domain..")
        val layer2 = Layer("Presentation", "com.example.presentation..")

        // when
        with(sut) {
            layer1.include()
            layer2.include()
        }

        // then
        sut.layers shouldContain layer1
        sut.layers shouldContain layer2
        sut.layerDependencies shouldContain LayerDependency(layer1, LayerDependencyType.NONE, null)
        sut.layerDependencies shouldContain LayerDependency(layer2, LayerDependencyType.NONE, null)
    }
    // endregion

    // region Collection<Layer> extension dependencies
    @Test
    fun `collection dependsOn single layer creates dependencies for all layers in collection`() {
        // given
        val sut = LayerDependenciesCore()
        val sourceLayer1 = Layer("Domain", "com.example.domain..")
        val sourceLayer2 = Layer("Data", "com.example.data..")
        val targetLayer = Layer("Presentation", "com.example.presentation..")
        val sourceLayers = setOf(sourceLayer1, sourceLayer2)

        // when
        with(sut) {
            sourceLayers.dependsOn(targetLayer)
        }

        // then
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                layer2 = targetLayer,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                layer2 = targetLayer,
                strict = false,
            )
    }

    @Test
    fun `collection dependsOn set of layers creates dependencies for all combinations`() {
        // given
        val sut = LayerDependenciesCore()
        val sourceLayer1 = Layer("Domain", "com.example.domain..")
        val sourceLayer2 = Layer("Data", "com.example.data..")
        val targetLayer1 = Layer("Presentation", "com.example.presentation..")
        val targetLayer2 = Layer("Infrastructure", "com.example.infrastructure..")
        val sourceLayers = setOf(sourceLayer1, sourceLayer2)
        val targetLayers = setOf(targetLayer1, targetLayer2)

        // when
        with(sut) {
            sourceLayers.dependsOn(targetLayers)
        }

        // then
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                layer2 = targetLayer1,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                layer2 = targetLayer2,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                layer2 = targetLayer1,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                layer2 = targetLayer2,
                strict = false,
            )
    }

    @Test
    fun `collection dependsOnNothing creates dependencies for all layers in collection`() {
        // given
        val sut = LayerDependenciesCore()
        val sourceLayer1 = Layer("Domain", "com.example.domain..")
        val sourceLayer2 = Layer("Data", "com.example.data..")
        val sourceLayers = setOf(sourceLayer1, sourceLayer2)

        // when
        with(sut) {
            sourceLayers.dependsOnNothing()
        }

        // then
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                layer2 = null,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                layer2 = null,
                strict = false,
            )
    }

    @Test
    fun `collection include adds all layers to layers collection`() {
        // given
        val sut = LayerDependenciesCore()
        val layer1 = Layer("Domain", "com.example.domain..")
        val layer2 = Layer("Data", "com.example.data..")
        val layers = setOf(layer1, layer2)

        // when
        with(sut) {
            layers.include()
        }

        // then
        sut.layers shouldContain layer1
        sut.layers shouldContain layer2
        sut.layerDependencies shouldContain LayerDependency(layer1, LayerDependencyType.NONE, null)
        sut.layerDependencies shouldContain LayerDependency(layer2, LayerDependencyType.NONE, null)
    }

    @Test
    fun `collection doesNotDependOn single layer creates dependencies for all layers in collection`() {
        // given
        val sut = LayerDependenciesCore()
        val sourceLayer1 = Layer("Domain", "com.example.domain..")
        val sourceLayer2 = Layer("Data", "com.example.data..")
        val targetLayer = Layer("Presentation", "com.example.presentation..")
        val sourceLayers = setOf(sourceLayer1, sourceLayer2)

        // when
        with(sut) {
            sourceLayers.doesNotDependOn(targetLayer)
        }

        // then
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                layer2 = targetLayer,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                layer2 = targetLayer,
                strict = false,
            )
    }

    @Test
    fun `collection doesNotDependOn set of layers creates dependencies for all combinations`() {
        // given
        val sut = LayerDependenciesCore()
        val sourceLayer1 = Layer("Domain", "com.example.domain..")
        val sourceLayer2 = Layer("Data", "com.example.data..")
        val targetLayer1 = Layer("Presentation", "com.example.presentation..")
        val targetLayer2 = Layer("Infrastructure", "com.example.infrastructure..")
        val sourceLayers = setOf(sourceLayer1, sourceLayer2)
        val targetLayers = setOf(targetLayer1, targetLayer2)

        // when
        with(sut) {
            sourceLayers.doesNotDependOn(targetLayers)
        }

        // then
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                layer2 = targetLayer1,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer1,
                dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                layer2 = targetLayer2,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                layer2 = targetLayer1,
                strict = false,
            )
        sut.layerDependencies shouldContain
            LayerDependency(
                layer1 = sourceLayer2,
                dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                layer2 = targetLayer2,
                strict = false,
            )
    }
    // endregion
}
