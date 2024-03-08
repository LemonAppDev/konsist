package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoReturnProviderCore :
    KoReturnProvider,
    KoContainingDeclarationProviderCore,
    KoKDocProviderCore,
    KoBaseProviderCore {
    val ktFunction: KtFunction

    private fun getTypeReferences(): List<KtTypeReference> = ktFunction
        .children
        .filterIsInstance<KtTypeReference>()

    override val returnType: KoTypeDeclaration?
        get() {
            val references = getTypeReferences()

            val type = if (ktFunction.isExtensionDeclaration()) {
                when {
                    references.size > 1 -> references.lastOrNull()
                    else -> null
                }
            } else {
                references.firstOrNull()
            }

            return type?.let {
                KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
            }
        }

    override val hasReturnValue: Boolean
        get() = if (returnType != null) {
            returnType?.name != "Unit"
        } else if (ktFunction.hasBlockBody()) {
            // Every function with block body and without declared type returns Unit
            false
        } else {
            /*
            We have no way of distinguishing between:
            1) fun sampleFunction1() = println("some text") // returns Unit
            2) fun sampleFunction2() = SampleClass() // returns SampleClass,
            so we always return true.
             */
            true
        }

    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasReturnType()"))
    override val hasReturnType: Boolean
        get() = ktFunction.hasDeclaredReturnType()

    override fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> ktFunction.hasDeclaredReturnType()
            else -> returnType?.let { predicate(it) } ?: false
        }

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(returnType, kClass)
}
