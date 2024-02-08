package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoExternalTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.model.getParentClass
import com.lemonappdev.konsist.core.model.getParentInterface
import com.lemonappdev.konsist.core.model.getParentObject
import org.jetbrains.kotlin.psi.KtTypeReference
import kotlin.reflect.KClass

object TypeUtil {
    internal fun getType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoTypeDeclaration? {
        val type = if (isExtension && types.size > 1) {
            // We choose last because when we have extension the first one is receiver and the second one is (return) type.
            types.last()
        } else if (!isExtension) {
            types.firstOrNull()
        } else {
            null
        }

        val typeText = type?.text

        val fqn =
            containingFile
                .imports
                .firstOrNull { import ->
                    import.name.substringAfterLast(".") == typeText || import.alias == typeText
                }
                ?.name
                ?: (containingFile.packagee?.fullyQualifiedName + "." + typeText)

        return when {
            typeText != null && isKotlinBasicType(typeText) -> KoKotlinTypeDeclarationCore.getInstance(
                type,
                containingFile
            )

            typeText != null && isKotlinCollectionTypes(typeText) -> KoKotlinTypeDeclarationCore.getInstance(
                type,
                containingFile
            )

            typeText != null -> {
                getParentClass(typeText, fqn, containingFile)
                    ?: getParentInterface(typeText, fqn, containingFile
                    ) ?: getParentObject(typeText, fqn, containingFile)
                    ?: KoExternalTypeDeclarationCore.getInstance(typeText, type)
            }

            else -> null
        }
    }

    internal fun hasTypeOf(type: KoKotlinTypeDeclaration?, kClass: KClass<*>): Boolean =
        if (type?.isKotlinType == true) {
            kClass.simpleName == type.name
        } else {
            kClass.qualifiedName == type?.fullyQualifiedName
        }

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun getReceiverType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
    ): KoTypeDeclaration? {
        val type = if (isExtension) {
            types.first()
        } else {
            null
        }

        return type?.let { KoKotlinTypeDeclarationCore.getInstance(type, parentDeclaration) }
    }

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun hasReceiverType(receiverType: KoTypeDeclaration?, name: String?): Boolean = when (name) {
        null -> receiverType != null
        else -> receiverType?.name == name
    }

    private fun isKotlinBasicType(name: String): Boolean = kotlinBasicTypes.any { it == name }

    private fun isKotlinCollectionTypes(name: String): Boolean = kotlinCollectionTypes.any { name.startsWith(it) }

    // Basic types in Kotlin are described here: https://kotlinlang.org/docs/basic-types.html
    private val kotlinBasicTypes: Set<String>
        get() = setOf(
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
        )

    // Collections in Kotlin are described here: https://kotlinlang.org/docs/collections-overview.html#collection
    // and here https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections
    private val kotlinCollectionTypes: Set<String>
        get() = setOf(
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
