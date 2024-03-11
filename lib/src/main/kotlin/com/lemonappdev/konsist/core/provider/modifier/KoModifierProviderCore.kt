package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtModifierListOwner

internal interface KoModifierProviderCore : KoModifierProvider, KoBaseProviderCore {
    val ktModifierListOwner: KtModifierListOwner

    override val modifiers: List<KoModifier>
        get() =
            ktModifierListOwner
                .modifierList
                ?.text
                ?.removeMultilineComments()
                ?.split(EndOfLine.UNIX.value)
                ?.map { it.ignoreCommentsOnTheSameLineAsModifiers() }
                ?.filterNot { it.isBlank() }
                ?.filterNot { it.isCommentLine() }
                ?.filterNot { it.isKDocLine() }
                ?.splitTextToSeparateDeclarations()
                ?.takeOnlyModifiers()
                ?.map {
                    KoModifier
                        .entries
                        .firstOrNull { modifier -> modifier.type == it }
                        ?: throw KoInternalException("Modifier not found: $it")
                }
                .orEmpty()

    override val numModifiers: Int
        get() = modifiers.size

    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasModifier`, otherwise with `hasAllModifiers`.
            """,
    )
    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean =
        when {
            koModifiers.isEmpty() -> modifiers.isNotEmpty()
            else -> modifiers.containsAll(koModifiers.toList())
        }

    override fun hasModifiers(): Boolean = modifiers.isNotEmpty()

    override fun hasModifier(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean {
        val givenModifiers = modifiers.toList() + modifier

        return givenModifiers.any { this.modifiers.any { modifier -> modifier == it } }
    }

    override fun hasAllModifiers(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = this.modifiers.containsAll(modifiers.toList() + modifier)

    private fun String.isKDocLine(): Boolean {
        val trimmed = trim()
        return trimmed.startsWith("/*") || trimmed.startsWith("*/") || trimmed.startsWith("*")
    }

    private fun String.isCommentLine(): Boolean = trim().startsWith("//")

    // E.g. "private open" splits to listOf("private", "open")
    private fun List<String>.splitTextToSeparateDeclarations(): List<String> = flatMap { it.split(" ") }

    private fun List<String>.takeOnlyModifiers(): List<String> =
        takeLastWhile {
        /*
        We filter this way because this list contains modifiers and annotations,
        and we need to exclude all annotations especially with blank spaces
        e.g. @SampleAnnotation(parameter = "sample parameter")
        and with angle brackets
        e.g. @SampleAnnotation<String, Int>
         */
            !it.contains('<') &&
                !it.contains('>') &&
                !it.contains(')') &&
                !it.contains('@') &&
                it.isNotBlank()
        }

    private fun String.removeMultilineComments(): String = substringAfter("*/")

    /*
    This method avoid situations when comment is in the same line as modifiers:
    ```
    private open // sample comment
    class SampleClass
    ```
     */
    private fun String.ignoreCommentsOnTheSameLineAsModifiers(): String = substringBefore("//")
}
