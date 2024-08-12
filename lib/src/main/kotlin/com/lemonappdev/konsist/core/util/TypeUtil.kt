package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoFunctionTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.model.getObject
import com.lemonappdev.konsist.core.model.getTypeAlias
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

object TypeUtil {
    internal fun getBasicType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoBaseTypeDeclaration? {
        val type =
            if (isExtension && types.size > 1) {
                // The last element is chosen because, in the case of an extension, the first element is the receiver
                // and the second element is the return type.
                types.last()
            } else {
                if (!isExtension) {
                    types.firstOrNull()
                } else {
                    null
                }
                    ?.children
                    // The last item is chosen because when a type is preceded by an annotation or modifier,
                    // the type being searched for is the last item in the list.
                    ?.lastOrNull()
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
    ): Boolean = kClass.qualifiedName == (type?.declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName

    @Suppress("detekt.CyclomaticComplexMethod")
    private fun transformPsiElementToKoTypeDeclaration(
        type: PsiElement?,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoBaseTypeDeclaration? {
        val nestedType =
            if (type is KtNullableType) {
                type
                    .children
                    .firstOrNull()
            } else {
                type
            }

        val typeText = nestedType?.text

        var fqn = containingFile
            .imports
            .firstOrNull { import -> import.name.substringAfterLast(".") == typeText }
            ?.name

        val declarations = containingFile
            .declarations()
            .filterIsInstance<KoFullyQualifiedNameProvider>()
            .filter { it.fullyQualifiedName?.endsWith(typeText ?: "") == true }

        val parentDeclFqn = if (parentDeclaration is KoFullyQualifiedNameProvider && parentDeclaration.fullyQualifiedName != null) {
            parentDeclaration.fullyQualifiedName!!.substringBeforeLast(".")
        } else {
            (parentDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName ?: ""
        }

        val decl = declarations.singleOrNull() ?: declarations.firstOrNull {
            it.fullyQualifiedName?.contains(parentDeclFqn) == true
        }

        fqn = fqn ?: decl?.fullyQualifiedName

        return when {
            nestedType is KtFunctionType -> KoFunctionTypeDeclarationCore.getInstance(nestedType, containingFile)
            nestedType is KtUserType && typeText != null -> {
                if (isKotlinBasicType(typeText) || isKotlinCollectionTypes(typeText)) {
                    KoKotlinTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
                } else {
                    getClass(typeText, fqn, false, containingFile)
                        ?: getInterface(typeText, fqn, false, containingFile)
                        ?: getObject(typeText, fqn, false, containingFile)
                        ?: getTypeAlias(typeText, fqn, containingFile)
                        ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
                }
            }

            nestedType is KtTypeReference && typeText != null -> {
                getClass(typeText, fqn, false, containingFile)
                    ?: getInterface(typeText, fqn, false, containingFile)
                    ?: getObject(typeText, fqn, false, containingFile)
                    ?: getTypeAlias(typeText, fqn, containingFile)
                    ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
            }

            else -> null
        }
    }

    internal fun isKotlinBasicType(name: String): Boolean = kotlinBasicTypes.any { it == name }

    internal fun isKotlinCollectionTypes(name: String): Boolean = kotlinCollectionTypes.any { name.startsWith("$it<") }

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
