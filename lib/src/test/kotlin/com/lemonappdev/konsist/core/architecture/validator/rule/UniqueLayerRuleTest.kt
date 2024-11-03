package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class UniqueLayerRuleTest {
    private val sut = UniqueLayerRule()

    @Test
    fun `should validate empty dependencies set`() {
        // given
        val dependencies = emptySet<LayerDependency>()

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate unique layers successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")
        val dependencies =
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate single layer with DEPEND_ON_NOTHING successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val dependencies =
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_NOTHING),
            )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should throw exception when layers have duplicate names`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer1", "package2..")
        val dependencies =
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            )

        // when
        val func = { sut.validate(dependencies) }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layer configurations:\n" +
            "Layer name must be unique. Duplicated name: 'layer1'."
    }

    @Test
    fun `should throw exception when layers have duplicate root packages`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package1..")
        val dependencies =
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            )

        // when
        val func = { sut.validate(dependencies) }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layer configurations:\n" +
            "Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'."
    }

    @Test
    fun `should throw exception when layers have both duplicate names and root packages`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer1", "package1..")
        val dependencies =
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            )

        // when
        val func = { sut.validate(dependencies) }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layer configurations:\n" +
            "Layer name must be unique. Duplicated name: 'layer1'.\n" +
            "Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'."
    }

    @Test
    fun `should throw exception when multiple layer validations fail with multiple duplicates`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer1", "package2..") // Duplicate name with layer1
        val layer3 = Layer("layer3", "package1..") // Duplicate package with layer1
        val layer4 = Layer("layer3", "package3..") // Duplicate name with layer3
        val layer5 = Layer("layer5", "package2..") // Duplicate package with layer2

        val dependencies =
            setOf(
                LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
                LayerDependency(layer3, LayerDependencyType.DEPEND_ON_LAYER, layer4),
                LayerDependency(layer5, LayerDependencyType.DEPEND_ON_NOTHING),
            )

        // when
        val func = { sut.validate(dependencies) }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layer configurations:\n" +
            "Layer name must be unique. Duplicated name: 'layer1'.\n" +
            "Layer name must be unique. Duplicated name: 'layer3'.\n" +
            "Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'.\n" +
            "Layer rootPackage must be unique. Duplicated rootPackage: 'package2..'."
    }

    @Test
    fun `should validate single layer with multiple dependency types successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            LayerDependency(layer1, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, layer1),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate multiple dependencies for same layer successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")
        val layer3 = Layer("layer3", "package3..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer3),
            LayerDependency(layer2, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, layer3),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate self-referential dependencies successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer1),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate complex dependency graph successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")
        val layer3 = Layer("layer3", "package3..")
        val layer4 = Layer("layer4", "package4..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            LayerDependency(layer2, LayerDependencyType.DEPEND_ON_LAYER, layer3),
            LayerDependency(layer3, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, layer4),
            LayerDependency(layer4, LayerDependencyType.DEPEND_ON_LAYER, layer1),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate mixed dependency types successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")
        val layer3 = Layer("layer3", "package3..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            LayerDependency(layer2, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, layer3),
            LayerDependency(layer3, LayerDependencyType.DEPEND_ON_NOTHING),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate cyclic dependencies successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            LayerDependency(layer2, LayerDependencyType.DEPEND_ON_LAYER, layer1),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate multiple layers with DEPEND_ON_NOTHING successfully`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")
        val layer3 = Layer("layer3", "package3..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_NOTHING),
            LayerDependency(layer2, LayerDependencyType.DEPEND_ON_NOTHING),
            LayerDependency(layer3, LayerDependencyType.DEPEND_ON_NOTHING),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }

    @Test
    fun `should validate same layer instance used multiple times`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")

        val dependencies = setOf(
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2),
            LayerDependency(layer2, LayerDependencyType.DEPEND_ON_LAYER, layer1),
            LayerDependency(layer1, LayerDependencyType.DEPEND_ON_NOTHING),
        )

        // when
        sut.validate(dependencies)

        // then
        // No exception thrown
    }
}
