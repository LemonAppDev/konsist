package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoFunctionTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoGenericTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoStarProjectionDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeParameterDeclarationCore
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.model.getObject
import com.lemonappdev.konsist.core.model.getTypeAlias
import com.lemonappdev.konsist.core.provider.KoTypeParameterProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

object TypeUtil {
    internal fun getBasicType(
        types: List<KtElement?>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoSourceDeclaration? {
        val notNullTypes = types.filterNotNull()

        val type =
            if (notNullTypes.filterIsInstance<KtTypeReference>().isNotEmpty()) {
                if (isExtension && notNullTypes.size > 1) {
                    // The last element is chosen because, in the case of an extension, the first element is the receiver
                    // and the second element is the return type.
                    notNullTypes.last()
                } else {
                    if (!isExtension) {
                        notNullTypes.firstOrNull()
                    } else {
                        null
                    }?.children
                        // The last item is chosen because when a type is preceded by an annotation or modifier,
                        // the type being searched for is the last item in the list.
                        ?.lastOrNull()
                }
            } else if (notNullTypes.filterIsInstance<KtNameReferenceExpression>().isNotEmpty()) {
                notNullTypes.filterIsInstance<KtNameReferenceExpression>().firstOrNull()
            } else if (notNullTypes.filterIsInstance<KtTypeProjection>().isNotEmpty()) {
                val typeProjection =
                    notNullTypes
                        .filterIsInstance<KtTypeProjection>()
                        .firstOrNull()

                if (typeProjection?.projectionKind == KtProjectionKind.STAR) {
                    return KoStarProjectionDeclarationCore.getInstance(typeProjection, parentDeclaration)
                } else {
                    typeProjection
                        ?.children
                        // The last item is chosen because when a type is preceded by an type projection modifier (out or in),
                        // the type being searched for is the last item in the list.
                        ?.lastOrNull()
                        ?.children
                        ?.firstOrNull()
                }
            } else {
                null
            }

        val nestedType =
            if (type is KtNullableType) {
                type
                    .children
                    .firstOrNull()
            } else {
                type
            }

        val importDirective =
            containingFile
                .imports
                .firstOrNull { it.alias?.name == nestedType?.text }

        return if (importDirective != null) {
            importDirective.alias
        } else {
            transformPsiElementToKoTypeDeclaration(type, parentDeclaration, containingFile)
        }
    }

    internal fun hasTypeOf(
        type: KoTypeDeclaration?,
        kClass: KClass<*>,
    ): Boolean = kClass.qualifiedName == (type?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName

    internal fun hasTypeOf(
        type: KoSourceDeclaration?,
        kClass: KClass<*>,
    ): Boolean = kClass.qualifiedName == (type as? KoFullyQualifiedNameProvider)?.fullyQualifiedName

    @Suppress("detekt.CyclomaticComplexMethod", "detekt.LongMethod")
    private fun transformPsiElementToKoTypeDeclaration(
        type: PsiElement?,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoSourceDeclaration? {
        val nestedType =
            if (type is KtNullableType) {
                type
                    .children
                    .firstOrNull()
            } else {
                type
            }

        val typeText = nestedType?.text

        val fullyQualifiedName =
            containingFile
                .imports
                .firstOrNull { import ->
                    if (import.hasAlias()) {
                        import.alias?.name == typeText
                    } else {
                        import.name.substringAfterLast(".") == typeText
                    }
                }?.name
                ?: containingFile
                    .declarations()
                    .getDeclarationFullyQualifiedName(typeText, parentDeclaration)
                ?: containingFile
                    .packagee
                    ?.name
                    ?.let { packageName ->
                        Konsist
                            .scopeFromPackage(packageName)
                            .declarations()
                            .getDeclarationFullyQualifiedName(typeText, parentDeclaration)
                    }

        val hasTypeParameterWithTheSameName =
            (parentDeclaration as? KoTypeParameterProviderCore)
                ?.typeParameters
                ?.map { it.text.substringBefore(":") }
                ?.map { it.trim() }
                ?.any { it == typeText }

        return when {
            nestedType is KtTypeProjection -> KoStarProjectionDeclarationCore.getInstance(nestedType, parentDeclaration)
            nestedType is KtFunctionType -> KoFunctionTypeDeclarationCore.getInstance(nestedType, containingFile)
            nestedType is KtUserType && typeText != null -> {
                if (nestedType.children.filterIsInstance<KtTypeArgumentList>().isNotEmpty()) {
                    KoGenericTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
                } else if (isKotlinBasicType(typeText) || isKotlinCollectionTypes(typeText)) {
                    KoKotlinTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
                } else if (hasTypeParameterWithTheSameName == true) {
                    KoTypeParameterDeclarationCore.getInstance(nestedType, containingFile)
                } else {
                    getClass(typeText, fullyQualifiedName, false, containingFile)
                        ?: getInterface(typeText, fullyQualifiedName, false, containingFile)
                        ?: getObject(typeText, fullyQualifiedName, false, containingFile)
                        ?: getTypeAlias(typeText, fullyQualifiedName, containingFile)
                        ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
                }
            }

            nestedType is KtNameReferenceExpression && typeText != null -> {
                if (isKotlinBasicType(typeText) || isKotlinCollectionTypes(typeText)) {
                    KoKotlinTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
                } else {
                    getClass(typeText, fullyQualifiedName, false, containingFile)
                        ?: getInterface(typeText, fullyQualifiedName, false, containingFile)
                        ?: getObject(typeText, fullyQualifiedName, false, containingFile)
                        ?: getTypeAlias(typeText, fullyQualifiedName, containingFile)
                        ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
                }
            }

            nestedType is KtTypeReference && typeText != null -> {
                getClass(typeText, fullyQualifiedName, false, containingFile)
                    ?: getInterface(typeText, fullyQualifiedName, false, containingFile)
                    ?: getObject(typeText, fullyQualifiedName, false, containingFile)
                    ?: getTypeAlias(typeText, fullyQualifiedName, containingFile)
                    ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
            }

            else -> null
        }
    }

    private fun List<KoBaseDeclaration>.getDeclarationFullyQualifiedName(
        typeText: String?,
        parentDeclaration: KoBaseDeclaration,
    ): String? {
        val parentDeclarationFullyQualifiedName =
            (parentDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName.orEmpty()

        val declarations =
            filterIsInstance<KoFullyQualifiedNameProvider>()
                .filter { it.fullyQualifiedName?.endsWith(typeText ?: "") == true }

        val declaration =
            declarations.singleOrNull()
                ?: declarations.firstOrNull { declaration ->
                    declaration.fullyQualifiedName?.contains(parentDeclarationFullyQualifiedName) == true ||
                        (
                            (declaration as? KoContainingDeclarationProvider)
                                ?.containingDeclaration as? KoDeclarationProvider
                        )?.hasDeclaration { it == parentDeclaration } == true
                }

        return declaration?.fullyQualifiedName
    }

    internal fun isKotlinBasicType(name: String): Boolean = kotlinBasicTypes.any { it == name }

    internal fun isKotlinCollectionTypes(name: String): Boolean = kotlinCollectionTypes.any { it == name }

    // Basic types in Kotlin are described here: https://kotlinlang.org/docs/basic-types.html
    private val kotlinBasicTypes: Set<String>
        get() =
            setOf(
                "Byte",
                "Short",
                "Int",
                "Long",
                "Float",
                "Double",
                "UByte",
                "UShort",
                "UInt",
                "ULong",
                "UByteArray",
                "UShortArray",
                "UIntArray",
                "ULongArray",
                "Boolean",
                "Char",
                "String",
                "Unit",
                "Any",
                "Nothing",
            )

    // Collections in Kotlin are described here: https://kotlinlang.org/docs/collections-overview.html#collection
    // and here https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections
    private val kotlinCollectionTypes: Set<String>
        get() =
            setOf(
                "AbstractCollection",
                "AbstractIterator",
                "AbstractList",
                "AbstractMap",
                "AbstractMutableCollection",
                "AbstractMutableList",
                "AbstractMutableMap",
                "AbstractMutableSet",
                "AbstractSet",
                "ArrayDeque",
                "ArrayList",
                "Array",
                "Collection",
                "HashMap",
                "HashSet",
                "LinkedHashMap",
                "LinkedHashSet",
                "List",
                "Map",
                "MutableCollection",
                "MutableList",
                "MutableMap",
                "MutableSet",
                "Set",
            )
}
