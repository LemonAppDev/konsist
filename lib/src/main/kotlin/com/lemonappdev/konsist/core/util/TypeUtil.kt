package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoTypeArgumentDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoTypeParameterDeclarationCore
import com.lemonappdev.konsist.core.declaration.private.KoFunctionTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.private.KoGenericTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoStarProjectionDeclarationCore
import com.lemonappdev.konsist.core.provider.KoTypeParameterProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance

object TypeUtil {
    private val availableTypes = ConcurrentHashMap<String, Boolean>()

    /*
     * Checks if the class is on the classpath. The class is loaded without initialization, so static initializers
     * of the analyzed code are not executed.
     */
    internal fun isTypeAvailable(fqn: String): Boolean =
        availableTypes.computeIfAbsent(fqn) {
            runCatching { Class.forName(fqn, false, TypeUtil::class.java.classLoader) }.isSuccess
        }

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
            } else if (notNullTypes.filterIsInstance<KtAnnotationEntry>().isNotEmpty()) {
                notNullTypes
                    .filterIsInstance<KtAnnotationEntry>()
                    .firstOrNull()
                    ?.children
                    ?.firstOrNull()
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
    ): Boolean = matchesKClass((type?.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName, kClass)

    internal fun hasTypeOf(
        type: KoSourceDeclaration?,
        kClass: KClass<*>,
    ): Boolean = matchesKClass((type as? KoFullyQualifiedNameProvider)?.fullyQualifiedName, kClass)

    internal fun hasTypeOf(
        type: KoTypeDeclaration?,
        kType: KType,
    ): Boolean {
        val kClass = kType.classifier as? KClass<*> ?: return false

        return hasTypeOf(type, kClass) &&
            (!kType.isMarkedNullable || type?.isNullable == true) &&
            matchesTypeArguments(type?.typeArguments.orEmpty(), kType.arguments)
    }

    /*
     * Checks if the type arguments match the given type projections, e.g. `List<Int>` and `typeOf<List<Int>>()`.
     * Star projection (`typeOf<List<*>>()`) matches any type argument.
     */
    private fun matchesTypeArguments(
        typeArguments: List<KoTypeArgumentDeclaration>,
        kTypeProjections: List<KTypeProjection>,
    ): Boolean =
        typeArguments.size == kTypeProjections.size &&
            typeArguments.zip(kTypeProjections).all { (typeArgument, kTypeProjection) ->
                matchesTypeArgument(typeArgument, kTypeProjection)
            }

    private fun matchesTypeArgument(
        typeArgument: KoTypeArgumentDeclaration,
        kTypeProjection: KTypeProjection,
    ): Boolean {
        val kType = kTypeProjection.type ?: return true
        val kClass = kType.classifier as? KClass<*> ?: return false
        val isNullable =
            (typeArgument as? KoTypeArgumentDeclarationCore)
                ?.ktTypeProjection
                ?.typeReference
                ?.typeElement is KtNullableType

        return !typeArgument.isStarProjection &&
            typeArgument.hasInModifier == (kTypeProjection.variance == KVariance.IN) &&
            typeArgument.hasOutModifier == (kTypeProjection.variance == KVariance.OUT) &&
            isNullable == kType.isMarkedNullable &&
            matchesKClass((typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName, kClass) &&
            matchesTypeArguments(typeArgument.typeArguments.orEmpty(), kType.arguments)
    }

    /*
     * Checks if the fully qualified name refers to the given class.
     * For `kotlin.RuntimeException` and `RuntimeException::class` value will be true,
     * although `RuntimeException::class.qualifiedName` is `java.lang.RuntimeException`.
     */
    internal fun matchesKClass(
        fullyQualifiedName: String?,
        kClass: KClass<*>,
    ): Boolean {
        val qualifiedName = fullyQualifiedName?.let { getKotlinTypeQualifiedName(it) }

        return kClass.qualifiedName == qualifiedName
    }

    internal fun getKotlinTypeQualifiedName(fullyQualifiedName: String): String =
        kotlinTypeQualifiedNames[fullyQualifiedName] ?: fullyQualifiedName

    /*
     * Returns the name of the Kotlin type alias of the Java type, e.g. `kotlin.RuntimeException`
     * for `java.lang.RuntimeException`.
     */
    internal fun getKotlinTypeAliasName(qualifiedName: String): String = kotlinTypeAliasNames[qualifiedName] ?: qualifiedName

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

        val typeParameter =
            (parentDeclaration as? KoTypeParameterProviderCore)
                ?.ktTypeParameterListOwner
                ?.typeParameters
                ?.firstOrNull { it.name == typeText }

        return when {
            typeParameter != null -> {
                KoTypeParameterDeclarationCore.getInstance(
                    typeParameter,
                    emptyList(),
                    containingFile,
                )
            }

            nestedType is KtTypeProjection -> {
                KoStarProjectionDeclarationCore
            }

            nestedType is KtFunctionType -> {
                KoFunctionTypeDeclarationCore.getInstance(nestedType, containingFile)
            }

            nestedType is KtUserType && nestedType.children.filterIsInstance<KtTypeArgumentList>().isNotEmpty() -> {
                KoGenericTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
            }

            nestedType is KtUserType ||
                nestedType is KtNameReferenceExpression ||
                nestedType is KtTypeReference ||
                nestedType is KtConstructorCalleeExpression -> {
                getTypeDeclaration(nestedType, getTypeName(nestedType) ?: nestedType.text, parentDeclaration, containingFile)
            }

            else -> {
                null
            }
        } as KoDeclarationCastProvider?
    }

    /*
     * Returns the declaration of the type with the given name: declaration from the file or project, Kotlin type
     * or external declaration.
     */
    private fun getTypeDeclaration(
        ktElement: KtElement,
        name: String,
        containingDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoBaseProvider {
        // Unresolved name is not searched in the project, because it would match a type from the default package
        val fullyQualifiedName = TypeResolver.resolveFullyQualifiedName(name, containingFile, containingDeclaration)

        return fullyQualifiedName?.let { TypeResolver.getTypeDeclaration(it, containingFile) }
            ?: if (fullyQualifiedName != null && isKotlinTypeFullyQualifiedName(fullyQualifiedName)) {
                KoKotlinTypeDeclarationCore.getInstance(ktElement, containingDeclaration)
            } else {
                null
            }
            ?: KoExternalDeclarationCore.getInstance(name, fullyQualifiedName ?: name, ktElement)
    }

    /*
     * Returns the name of the referenced type without type arguments.
     * For `RecyclerView.ViewHolder<T>` and constructor call `RecyclerView.ViewHolder<T>()` value will be "RecyclerView.ViewHolder"
     */
    internal fun getTypeName(element: PsiElement?): String? =
        when (element) {
            is KtUserType -> {
                element.getQualifiedName()
            }

            is KtNameReferenceExpression -> {
                (element.parent as? KtUserType)?.getQualifiedName()
                    ?: element.getCallQualifiedName()
                    ?: element.getReferencedName()
            }

            is KtTypeReference -> {
                getTypeName(element.typeElement?.let { if (it is KtNullableType) it.innerType else it })
            }

            is KtConstructorCalleeExpression -> {
                getTypeName(element.typeReference)
            }

            else -> {
                null
            }
        }

    private fun KtUserType.getQualifiedName(): String? =
        referencedName?.let { name ->
            qualifier
                ?.getQualifiedName()
                ?.let { "$it.$name" }
                ?: name
        }

    private fun KtNameReferenceExpression.getCallQualifiedName(): String? {
        val callExpression = parent as? KtCallExpression ?: return null
        val qualifiedExpression = callExpression.parent as? KtDotQualifiedExpression ?: return null

        return if (qualifiedExpression.selectorExpression == callExpression) {
            "${qualifiedExpression.receiverExpression.text}.${getReferencedName()}"
        } else {
            null
        }
    }

    internal fun isKotlinTypeFullyQualifiedName(fullyQualifiedName: String): Boolean = fullyQualifiedName in kotlinTypeNames

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
     * For `((Int) -> Unit)` value will be "(Int) -> Unit"
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

    /*
     * Kotlin types whose `KClass.qualifiedName` differs from the Kotlin name on the JVM:
     * - type aliases, e.g. `RuntimeException::class.qualifiedName` is `java.lang.RuntimeException`
     * - mutable collections, e.g. `MutableList::class.qualifiedName` is `kotlin.collections.List`
     */
    private val kotlinTypeQualifiedNames: Map<String, String> =
        mapOf(
            "kotlin.ArithmeticException" to ArithmeticException::class,
            "kotlin.ArrayIndexOutOfBoundsException" to ArrayIndexOutOfBoundsException::class,
            "kotlin.AssertionError" to AssertionError::class,
            "kotlin.AutoCloseable" to AutoCloseable::class,
            "kotlin.ClassCastException" to ClassCastException::class,
            "kotlin.Comparator" to Comparator::class,
            "kotlin.ConcurrentModificationException" to ConcurrentModificationException::class,
            "kotlin.Error" to Error::class,
            "kotlin.Exception" to Exception::class,
            "kotlin.IllegalArgumentException" to IllegalArgumentException::class,
            "kotlin.IllegalStateException" to IllegalStateException::class,
            "kotlin.IndexOutOfBoundsException" to IndexOutOfBoundsException::class,
            "kotlin.NoSuchElementException" to NoSuchElementException::class,
            "kotlin.NullPointerException" to NullPointerException::class,
            "kotlin.NumberFormatException" to NumberFormatException::class,
            "kotlin.OutOfMemoryError" to OutOfMemoryError::class,
            "kotlin.RuntimeException" to RuntimeException::class,
            "kotlin.UnsupportedOperationException" to UnsupportedOperationException::class,
            "kotlin.collections.ArrayList" to ArrayList::class,
            "kotlin.collections.HashMap" to HashMap::class,
            "kotlin.collections.HashSet" to HashSet::class,
            "kotlin.collections.LinkedHashMap" to LinkedHashMap::class,
            "kotlin.collections.LinkedHashSet" to LinkedHashSet::class,
            "kotlin.collections.MutableCollection" to MutableCollection::class,
            "kotlin.collections.MutableIterable" to MutableIterable::class,
            "kotlin.collections.MutableIterator" to MutableIterator::class,
            "kotlin.collections.MutableList" to MutableList::class,
            "kotlin.collections.MutableListIterator" to MutableListIterator::class,
            "kotlin.collections.MutableMap" to MutableMap::class,
            "kotlin.collections.MutableSet" to MutableSet::class,
            "kotlin.collections.RandomAccess" to RandomAccess::class,
            "kotlin.text.Appendable" to Appendable::class,
            "kotlin.text.CharacterCodingException" to CharacterCodingException::class,
            "kotlin.text.StringBuilder" to StringBuilder::class,
        ).mapValues { (_, kClass) -> kClass.qualifiedName.orEmpty() }

    // Mutable collections are skipped, because they have the same qualified name as read-only ones
    private val kotlinTypeAliasNames: Map<String, String> by lazy {
        kotlinTypeQualifiedNames
            .filterValues { !it.startsWith("kotlin.") }
            .entries
            .associate { (kotlinName, javaName) -> javaName to kotlinName }
    }

    /*
        List of Kotlin standard library class declarations
        (extracted from Kotlin stdlib version 2.2).

        https://kotlinlang.org/api/core/kotlin-stdlib/
     */
    private val kotlinTypeNames: Set<String> by lazy { kotlinTypes.toSet() }

    val kotlinTypes =
        listOf(
            "kotlin.collections.AbstractCollection",
            "kotlin.collections.AbstractIterator",
            "kotlin.collections.AbstractList",
            "kotlin.collections.AbstractMap",
            "kotlin.collections.AbstractMutableCollection",
            "kotlin.collections.AbstractMutableList",
            "kotlin.collections.AbstractMutableMap",
            "kotlin.collections.AbstractMutableSet",
            "kotlin.collections.AbstractSet",
            "kotlin.io.AccessDeniedException",
            "kotlin.Annotation",
            "kotlin.annotation.AnnotationRetention",
            "kotlin.annotation.AnnotationTarget",
            "kotlin.Any",
            "kotlin.text.Appendable",
            "kotlin.ArithmeticException",
            "kotlin.Array",
            "kotlin.collections.ArrayDeque",
            "kotlin.ArrayIndexOutOfBoundsException",
            "kotlin.collections.ArrayList",
            "kotlin.AssertionError",
            "kotlin.AutoCloseable",
            "kotlin.Boolean",
            "kotlin.BooleanArray",
            "kotlin.collections.BooleanIterator",
            "kotlin.BuilderInference",
            "kotlin.Byte",
            "kotlin.ByteArray",
            "kotlin.collections.ByteIterator",
            "kotlin.Char",
            "kotlin.text.CharacterCodingException",
            "kotlin.CharArray",
            "kotlin.text.CharCategory",
            "kotlin.text.CharDirectionality",
            "kotlin.collections.CharIterator",
            "kotlin.ranges.CharProgression",
            "kotlin.ranges.CharRange",
            "kotlin.CharSequence",
            "kotlin.text.Charsets",
            "kotlin.ClassCastException",
            "kotlin.ranges.ClosedFloatingPointRange",
            "kotlin.ranges.ClosedRange",
            "kotlin.collections.Collection",
            "kotlin.Comparable",
            "kotlin.Comparator",
            "kotlin.ConcurrentModificationException",
            "kotlin.ConsistentCopyVisibility",
            "kotlin.js.Console",
            "kotlin.ContextFunctionTypeParams",
            "kotlin.js.Date",
            "kotlin.DeepRecursiveFunction",
            "kotlin.DeepRecursiveScope",
            "kotlin.Deprecated",
            "kotlin.DeprecatedSinceKotlin",
            "kotlin.DeprecationLevel",
            "kotlin.Double",
            "kotlin.DoubleArray",
            "kotlin.collections.DoubleIterator",
            "kotlin.DslMarker",
            "kotlin.js.Dynamic",
            "kotlin.EagerInitialization",
            "kotlin.js.EagerInitialization",
            "kotlin.Enum",
            "kotlin.Error",
            "kotlin.Exception",
            "kotlin.ExperimentalContextParameters",
            "kotlin.js.ExperimentalJsCollectionsApi",
            "kotlin.js.ExperimentalJsExport",
            "kotlin.js.ExperimentalJsFileName",
            "kotlin.js.ExperimentalJsReflectionCreateInstance",
            "kotlin.js.ExperimentalJsStatic",
            "kotlin.ExperimentalMultiplatform",
            "kotlin.ExperimentalStdlibApi",
            "kotlin.ExperimentalSubclassOptIn",
            "kotlin.ExperimentalUnsignedTypes",
            "kotlin.js.ExperimentalWasmJsInterop",
            "kotlin.js.ExperimentalWasmJsInterop",
            "kotlin.ExposedCopyVisibility",
            "kotlin.ExtensionFunctionType",
            "kotlin.io.FileAlreadyExistsException",
            "kotlin.io.FileSystemException",
            "kotlin.io.FileTreeWalk",
            "kotlin.io.FileWalkDirection",
            "kotlin.Float",
            "kotlin.FloatArray",
            "kotlin.collections.FloatIterator",
            "kotlin.Function",
            "kotlin.collections.Grouping",
            "kotlin.collections.HashMap",
            "kotlin.collections.HashSet",
            "kotlin.text.HexFormat",
            "kotlin.IgnorableReturnValue",
            "kotlin.IllegalArgumentException",
            "kotlin.IllegalStateException",
            "kotlin.jvm.ImplicitlyActualizedByJvmDeclaration",
            "kotlin.collections.IndexedValue",
            "kotlin.IndexOutOfBoundsException",
            "kotlin.Int",
            "kotlin.IntArray",
            "kotlin.collections.IntIterator",
            "kotlin.ranges.IntProgression",
            "kotlin.ranges.IntRange",
            "kotlin.collections.Iterable",
            "kotlin.collections.Iterator",
            "kotlin.js.JsAny",
            "kotlin.js.JsAny",
            "kotlin.js.JsArray",
            "kotlin.js.JsArray",
            "kotlin.js.JsBigInt",
            "kotlin.js.JsBigInt",
            "kotlin.js.JsBoolean",
            "kotlin.js.JsBoolean",
            "kotlin.js.JsClass",
            "kotlin.js.JsException",
            "kotlin.js.JsException",
            "kotlin.js.JsExport",
            "kotlin.js.JsExternalArgument",
            "kotlin.js.JsExternalInheritorsOnly",
            "kotlin.js.JsFileName",
            "kotlin.JsFun",
            "kotlin.JsFun",
            "kotlin.js.JsModule",
            "kotlin.js.JsName",
            "kotlin.js.JsNonModule",
            "kotlin.js.JsNumber",
            "kotlin.js.JsNumber",
            "kotlin.js.Json",
            "kotlin.js.JSON",
            "kotlin.js.JsPromiseError",
            "kotlin.js.JsQualifier",
            "kotlin.js.JsReference",
            "kotlin.js.JsReference",
            "kotlin.js.JsStatic",
            "kotlin.js.JsString",
            "kotlin.js.JsString",
            "kotlin.jvm.JvmDefaultWithCompatibility",
            "kotlin.jvm.JvmDefaultWithoutCompatibility",
            "kotlin.jvm.JvmExposeBoxed",
            "kotlin.jvm.JvmField",
            "kotlin.jvm.JvmInline",
            "kotlin.jvm.JvmMultifileClass",
            "kotlin.jvm.JvmName",
            "kotlin.jvm.JvmOverloads",
            "kotlin.jvm.JvmRecord",
            "kotlin.jvm.JvmRepeatable",
            "kotlin.jvm.JvmSerializableLambda",
            "kotlin.jvm.JvmStatic",
            "kotlin.jvm.JvmSuppressWildcards",
            "kotlin.jvm.JvmSynthetic",
            "kotlin.jvm.JvmWildcard",
            "kotlin.KotlinVersion",
            "kotlin.Lazy",
            "kotlin.LazyThreadSafetyMode",
            "kotlin.collections.LinkedHashMap",
            "kotlin.collections.LinkedHashSet",
            "kotlin.collections.List",
            "kotlin.collections.ListIterator",
            "kotlin.Long",
            "kotlin.LongArray",
            "kotlin.collections.LongIterator",
            "kotlin.ranges.LongProgression",
            "kotlin.ranges.LongRange",
            "kotlin.collections.Map",
            "kotlin.text.MatchGroup",
            "kotlin.text.MatchGroupCollection",
            "kotlin.text.MatchNamedGroupCollection",
            "kotlin.text.MatchResult",
            "kotlin.Metadata",
            "kotlin.annotation.MustBeDocumented",
            "kotlin.MustUseReturnValue",
            "kotlin.collections.MutableCollection",
            "kotlin.collections.MutableIterable",
            "kotlin.collections.MutableIterator",
            "kotlin.collections.MutableList",
            "kotlin.collections.MutableListIterator",
            "kotlin.collections.MutableMap",
            "kotlin.collections.MutableSet",
            "kotlin.js.nativeGetter",
            "kotlin.js.nativeInvoke",
            "kotlin.js.nativeSetter",
            "kotlin.NoSuchElementException",
            "kotlin.io.NoSuchFileException",
            "kotlin.Nothing",
            "kotlin.NotImplementedError",
            "kotlin.NoWhenBranchMatchedException",
            "kotlin.NullPointerException",
            "kotlin.Number",
            "kotlin.NumberFormatException",
            "kotlin.io.OnErrorAction",
            "kotlin.ranges.OpenEndRange",
            "kotlin.OptIn",
            "kotlin.OptionalExpectation",
            "kotlin.OutOfMemoryError",
            "kotlin.OverloadResolutionByLambdaReturnType",
            "kotlin.Pair",
            "kotlin.ParameterName",
            "kotlin.js.Promise",
            "kotlin.PublishedApi",
            "kotlin.jvm.PurelyImplements",
            "kotlin.collections.RandomAccess",
            "kotlin.text.Regex",
            "kotlin.text.RegexOption",
            "kotlin.js.RegExp",
            "kotlin.js.RegExpMatch",
            "kotlin.annotation.Repeatable",
            "kotlin.ReplaceWith",
            "kotlin.RequiresOptIn",
            "kotlin.Result",
            "kotlin.annotation.Retention",
            "kotlin.RuntimeException",
            "kotlin.sequences.Sequence",
            "kotlin.sequences.SequenceScope",
            "kotlin.collections.Set",
            "kotlin.Short",
            "kotlin.ShortArray",
            "kotlin.collections.ShortIterator",
            "kotlin.SinceKotlin",
            "kotlin.jvm.Strictfp",
            "kotlin.String",
            "kotlin.text.StringBuilder",
            "kotlin.SubclassOptInRequired",
            "kotlin.Suppress",
            "kotlin.jvm.Synchronized",
            "kotlin.annotation.Target",
            "kotlin.Throwable",
            "kotlin.Throws",
            "kotlin.jvm.Throws",
            "kotlin.jvm.Transient",
            "kotlin.Triple",
            "kotlin.text.Typography",
            "kotlin.UByte",
            "kotlin.UByteArray",
            "kotlin.UInt",
            "kotlin.UIntArray",
            "kotlin.ranges.UIntProgression",
            "kotlin.ranges.UIntRange",
            "kotlin.ULong",
            "kotlin.ULongArray",
            "kotlin.ranges.ULongProgression",
            "kotlin.ranges.ULongRange",
            "kotlin.UninitializedPropertyAccessException",
            "kotlin.Unit",
            "kotlin.UnsafeVariance",
            "kotlin.UnsupportedOperationException",
            "kotlin.UShort",
            "kotlin.UShortArray",
            "kotlin.jvm.Volatile",
        )
}
