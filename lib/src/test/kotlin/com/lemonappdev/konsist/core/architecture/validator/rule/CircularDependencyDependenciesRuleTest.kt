package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependencyDependenciesRuleTest {
    private val sut = CircularDependencyDependenciesRule()

    @Test
    fun `should validate dependencies without cycles successfully`() {
        // given
        val layer1 = Layer("layer name 1", "package1..")
        val layer2 = Layer("layer name 2", "package2..")
        val layer3 = Layer("layer name 3", "package3..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    layer2 = layer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = layer3,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should throw exception when circular dependency detected`() {
        // given
        val layer1 = Layer("layer name 1", "package1..")
        val layer2 = Layer("layer name 2", "package2..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    layer2 = layer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        val func = { sut.validate(dependencies) }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Circular dependency detected: 'layer name 1' <-> 'layer name 2'."
    }

    @Test
    fun `should ignore non-layer dependencies`() {
        // given
        val layer1 = Layer("layer name 1", "package1..")
        val layer2 = Layer("layer name 2", "package2..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    layer2 = layer2,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
            )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should detect circular dependency with four layers`() {
        // given
        val layer1 = Layer("layer name 1", "package1..")
        val layer2 = Layer("layer name 2", "package2..")
        val layer3 = Layer("layer name 3", "package3..")
        val layer4 = Layer("layer name 4", "package4..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    layer2 = layer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = layer3,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer3,
                    layer2 = layer4,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer4,
                    layer2 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        val func = { sut.validate(dependencies) }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Circular dependency detected: 'layer name 1' -> 'layer name 2' -> 'layer name 3' -> 'layer name 4' -> 'layer name 1'."
    }
}
