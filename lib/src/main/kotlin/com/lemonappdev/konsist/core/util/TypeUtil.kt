package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoTypeParameterDeclarationCore
import com.lemonappdev.konsist.core.declaration.private.KoFunctionTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.private.KoGenericTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoStarProjectionDeclarationCore
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
    ): KoDeclarationCastProvider? {
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
                    return KoStarProjectionDeclarationCore as? KoDeclarationCastProvider
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

        return (
            if (importDirective != null) {
                importDirective.alias
            } else {
                transformPsiElementToKoTypeDeclaration(type, parentDeclaration, containingFile)
            }
        ) as KoDeclarationCastProvider?
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
    ): KoDeclarationCastProvider? {
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

        val typeParameter =
            (parentDeclaration as? KoTypeParameterProviderCore)
                ?.ktTypeParameterListOwner
                ?.typeParameters
                ?.firstOrNull { it.name == typeText }

        return when {
            typeParameter != null -> KoTypeParameterDeclarationCore.getInstance(typeParameter, emptyList(), containingFile)
            nestedType is KtTypeProjection -> KoStarProjectionDeclarationCore
            nestedType is KtFunctionType -> KoFunctionTypeDeclarationCore.getInstance(nestedType, containingFile)
            nestedType is KtUserType && typeText != null -> {
                if (nestedType.children.filterIsInstance<KtTypeArgumentList>().isNotEmpty()) {
                    KoGenericTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
                } else if (isKotlinBasicType(typeText) || isKotlinCollectionTypes(typeText)) {
                    KoKotlinTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
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
        } as? KoSourceDeclaration as KoDeclarationCastProvider?
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

    internal fun isKotlinType(typeName: String): Boolean = isKotlinBasicType(typeName) || isKotlinCollectionTypes(typeName)

    internal fun isKotlinBasicType(typeName: String): Boolean {
        val bareTypeName = getBareType(typeName)
        return kotlinBasicTypeNames.any { it == bareTypeName }
    }

    internal fun isKotlinCollectionTypes(typeName: String): Boolean {
        val bareTypeName = getBareType(typeName)
        return kotlinCollectionTypeNames.any { it == bareTypeName }
    }

    internal fun getBareType(name: String): String =
        name
            .removeGenericTypeArguments()
            .removeNullability()
            .removePackage()
            .removeBrackets()

    /*
     * Removes generic type arguments from the type.
     * For `MyClass<String>` value will be "MyClass"
     */
    private fun String.removeGenericTypeArguments(): String = substringBefore("<")

    /*
     * Removes nullability from the type.
     * For `MyClass?` value will be "MyClass"
     */
    private fun String.removeNullability(): String = replace("?", "")

    /*
     * Removes package from the type.
     * For `com.app.MyClass` value will be "MyClass"
     */
    private fun String.removePackage(): String = substringAfterLast(".")

    /*
     * Removes brackets from the type.
     * For `((Int) -> Unit)` value will be "(Int) -> Unit)"
     */
    private fun String.removeBrackets(): String =
        if (startsWith("(") and endsWith(")")) {
            removePrefix("(")
                .removeSuffix(")")
        } else {
            this
        }

    // Kotlin basic types: https://kotlinlang.org/docs/basic-types.html
    @OptIn(ExperimentalUnsignedTypes::class)
    private val kotlinBasicTypeNames: Set<String> =
        setOf(
            Any::class,
            Boolean::class,
            Byte::class,
            Char::class,
            Double::class,
            Float::class,
            Int::class,
            Long::class,
            Nothing::class,
            Number::class,
            Short::class,
            String::class,
            UByte::class,
            UByteArray::class,
            UInt::class,
            UIntArray::class,
            ULong::class,
            ULongArray::class,
            UShort::class,
            UShortArray::class,
            Unit::class,
        ).mapNotNull { it.simpleName }
            .toSet()

    // Kotlin collections types:
    // https://kotlinlang.org/docs/collections-overview.html#collection
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections
    private val kotlinCollectionTypeNames: Set<String> =
        setOf(
            AbstractCollection::class,
            AbstractIterator::class,
            AbstractList::class,
            AbstractMap::class,
            AbstractMutableCollection::class,
            AbstractMutableList::class,
            AbstractMutableMap::class,
            AbstractMutableSet::class,
            AbstractSet::class,
            ArrayDeque::class,
            ArrayList::class,
            Array::class,
            Collection::class,
            HashMap::class,
            HashSet::class,
            LinkedHashMap::class,
            LinkedHashSet::class,
            List::class,
            Map::class,
            MutableCollection::class,
            MutableList::class,
            MutableMap::class,
            MutableSet::class,
            Set::class,
        ).mapNotNull { it.simpleName }
            .toSet()
}
