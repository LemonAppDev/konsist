package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

internal abstract class KoDeclarationImpl(
    override val ktTypeParameterListOwner: KtTypeParameterListOwner,
    val parentDeclaration: KoParentProvider?,
) :
    KoBaseDeclarationImpl(ktTypeParameterListOwner),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclaration {

    override val fullyQualifiedName: String by lazy {
        if (ktTypeParameterListOwner.fqName != null) {
            ktTypeParameterListOwner.fqName.toString()
        } else {
            ""
        }
    }

    override val modifiers: List<KoModifier> by lazy {
        ktTypeParameterListOwner
            .modifierList
            ?.text
            ?.split("\n")
            ?.map { it.substringBefore("//") }
            ?.flatMap { it.split(" ") }
            ?.takeLastWhile {
                // We filter this way because this list contains modifiers and annotations,
                // and we need to exclude all annotations especially with blank spaces
                // e.g. @SampleAnnotation(parameter = "sample parameter")
                // and with angle brackets
                // e.g. @SampleAnnotation<String, Int>
                !it.contains('<') &&
                    !it.contains('>') &&
                    !it.contains(')') &&
                    !it.contains('@') &&
                    it.isNotBlank()
            }
            ?.map {
                KoModifier
                    .values()
                    .firstOrNull { modifier -> modifier.type == it }
                    ?: throw KoInternalException("Modifier not found: $it")
            }
            ?.toList()
            ?: emptyList()
    }

    override fun hasPublicModifier(): Boolean = hasModifiers(KoModifier.PUBLIC)

    override fun isPublicOrDefault(): Boolean = ktTypeParameterListOwner.isPublic

    override fun hasPrivateModifier(): Boolean = hasModifiers(KoModifier.PRIVATE)

    override fun hasProtectedModifier(): Boolean = hasModifiers(KoModifier.PROTECTED)

    override fun hasInternalModifier(): Boolean = hasModifiers(KoModifier.INTERNAL)

    override fun isTopLevel(): Boolean = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }
}
