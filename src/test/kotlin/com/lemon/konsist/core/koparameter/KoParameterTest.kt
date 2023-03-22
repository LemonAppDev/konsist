package com.lemon.konsist.core.koparameter

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.assertion.check.check
import org.junit.jupiter.api.Test

class KoParameterTest {
    @Test
    fun `class-with-one-typed-parameter`() {
        // given
        val sut = getSut("class-with-one-typed-parameter")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check {
                it.parameters.first().type == "SampleParameter"
            }
    }

    @Test
    fun `class-with-one-name-parameter`() {
        // given
        val sut = getSut("class-with-one-name-parameter")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check {
                it.parameters.first().name == "sampleParameter"
            }
    }

    @Test
    fun `class-one-parameter-with-default-value`() {
        // given
        val sut = getSut("class-one-parameter-with-default-value")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check {
                it.parameters.first().hasDefaultValue
            }
    }

    @Test
    fun `class-without-parameters`() {
        // given
        val sut = getSut("class-without-parameters")

        // then
        sut.classes()
            .check {
                it.primaryConstructor == null
            }
    }

    @Test
    fun `class-with-empty-primary-constructor`() {
        // given
        val sut = getSut("class-with-empty-primary-constructor")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check {
                it.parameters.isEmpty()
            }
    }

    @Test
    fun `class-with-two-parameters`() {
        // given
        val sut = getSut("class-with-two-parameters")

        // then
        sut.classes()
            .mapNotNull { it.primaryConstructor }
            .check {
                it.parameters[0].name == "sampleParameter1" &&
                    it.parameters[1].name == "sampleParameter2" &&
                    it.parameters[0].type == "SampleParameter1" &&
                    it.parameters[1].type == "SampleParameter2" &&
                    !it.parameters[0].hasDefaultValue &&
                    !it.parameters[1].hasDefaultValue
            }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/koparameter/snippet/$fileName.kt.txt")
}
