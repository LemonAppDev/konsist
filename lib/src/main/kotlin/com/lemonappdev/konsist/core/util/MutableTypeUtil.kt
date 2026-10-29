package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider

internal object MutableTypeUtil {
    private const val MUTABLE_TYPE_PREFIX = "Mutable"

    /*
     * Checks if the source declaration is a mutable type.
     * Type aliases and import aliases are resolved to the declaration they point to, so their own names are ignored.
     */
    internal fun isMutableType(sourceDeclaration: KoDeclarationCastProvider?): Boolean = isMutableType(sourceDeclaration, mutableSetOf())

    private fun isMutableType(
        sourceDeclaration: KoDeclarationCastProvider?,
        visitedDeclarations: MutableSet<KoClassAndInterfaceAndObjectDeclaration>,
    ): Boolean =
        when (val declaration = sourceDeclaration.resolveImportAlias()) {
            is KoTypeAliasDeclaration -> isMutableType(declaration.type.sourceDeclaration, visitedDeclarations)
            is KoKotlinTypeDeclaration -> isKnownMutableTypeName(declaration.name)
            is KoExternalDeclaration -> isKnownMutableTypeName(declaration.name)
            is KoClassAndInterfaceAndObjectDeclaration -> isMutableProjectDeclaration(declaration, visitedDeclarations)
            else -> false
        }

    /*
     * Checks if the class, interface or object declared in the project is mutable:
     * its name starts with "Mutable", it declares a `var` property (including `var` constructor parameters),
     * it declares a `val` property whose type is mutable or has a mutable type argument (at any nesting level),
     * or any of its parents is mutable.
     * Properties of nested declarations (including companion objects) are not checked.
     * Types of properties with an inferred type are not checked.
     * Visited declarations are tracked to avoid infinite recursion for self-referencing types and when parents are
     * resolved ambiguously.
     */
    private fun isMutableProjectDeclaration(
        declaration: KoClassAndInterfaceAndObjectDeclaration,
        visitedDeclarations: MutableSet<KoClassAndInterfaceAndObjectDeclaration>,
    ): Boolean {
        if (!visitedDeclarations.add(declaration)) return false

        return declaration.name.startsWith(MUTABLE_TYPE_PREFIX) ||
            declaration.properties(includeNested = false).any { property ->
                property.isVar ||
                    property.type?.let {
                        isMutableType(it.sourceDeclaration, visitedDeclarations) ||
                            hasMutableTypeArgument(it.sourceDeclaration, it.typeArguments, visitedDeclarations)
                    } == true
            } ||
            declaration.parents().any { isMutableType(it.sourceDeclaration, visitedDeclarations) }
    }

    /*
     * Checks if any type argument (at any nesting level) is a mutable type.
     * For a type alias, type arguments of the aliased type are checked as well.
     */
    internal fun hasMutableTypeArgument(
        sourceDeclaration: KoDeclarationCastProvider?,
        typeArguments: List<KoTypeArgumentDeclaration>?,
    ): Boolean = hasMutableTypeArgument(sourceDeclaration, typeArguments, mutableSetOf())

    private fun hasMutableTypeArgument(
        sourceDeclaration: KoDeclarationCastProvider?,
        typeArguments: List<KoTypeArgumentDeclaration>?,
        visitedDeclarations: MutableSet<KoClassAndInterfaceAndObjectDeclaration>,
    ): Boolean =
        (sourceDeclaration.resolveImportAlias() as? KoTypeAliasDeclaration)?.type?.let {
            hasMutableTypeArgument(it.sourceDeclaration, it.typeArguments, visitedDeclarations)
        } == true ||
            typeArguments.orEmpty().any {
                isMutableType(it.sourceDeclaration, visitedDeclarations) ||
                    hasMutableTypeArgument(it.sourceDeclaration, it.typeArguments, visitedDeclarations)
            }

    /*
     * Checks if the source declaration is an immutable type: it resolves to a Kotlin type or final project declaration
     * that is not mutable, or to a known immutable external type.
     * Declarations whose mutability is unknown (type parameters, function types, external types not known to be
     * immutable, unresolved types, open Kotlin types such as `Any`, project interfaces and open, abstract or sealed
     * project classes) are not immutable.
     */
    internal fun isImmutableType(sourceDeclaration: KoDeclarationCastProvider?): Boolean =
        when (val declaration = sourceDeclaration.resolveImportAlias()) {
            is KoTypeAliasDeclaration -> isImmutableType(declaration.type.sourceDeclaration)
            is KoExternalDeclaration -> declaration.fullyQualifiedName?.let { isKnownImmutableTypeName(it) } == true
            is KoKotlinTypeDeclaration -> declaration.fullyQualifiedName !in openKotlinTypeNames && !isMutableType(declaration)
            is KoClassAndInterfaceAndObjectDeclaration -> !isOpenProjectDeclaration(declaration) && !isMutableType(declaration)
            else -> false
        }

    /*
     * Checks if the project declaration can be extended by a declaration that is mutable.
     */
    private fun isOpenProjectDeclaration(declaration: KoClassAndInterfaceAndObjectDeclaration): Boolean =
        declaration is KoInterfaceDeclaration ||
            (
                declaration is KoClassDeclaration &&
                    (declaration.hasOpenModifier || declaration.hasAbstractModifier || declaration.hasSealedModifier)
            )

    /*
     * Checks if any type argument (at any nesting level) is an immutable type.
     * For a type alias, type arguments of the aliased type are checked as well.
     */
    internal fun hasImmutableTypeArgument(
        sourceDeclaration: KoDeclarationCastProvider?,
        typeArguments: List<KoTypeArgumentDeclaration>?,
    ): Boolean =
        (sourceDeclaration.resolveImportAlias() as? KoTypeAliasDeclaration)?.type?.hasImmutableTypeArgument == true ||
            typeArguments.orEmpty().any {
                isImmutableType(it.sourceDeclaration) || hasImmutableTypeArgument(it.sourceDeclaration, it.typeArguments)
            }

    private fun KoDeclarationCastProvider?.resolveImportAlias(): KoDeclarationCastProvider? =
        if (this is KoImportAliasDeclaration) sourceDeclaration else this

    private fun isKnownMutableTypeName(name: String): Boolean {
        val bareName = TypeUtil.getBareType(name)

        return bareName.startsWith(MUTABLE_TYPE_PREFIX) || bareName in knownMutableTypeNames
    }

    private fun isKnownImmutableTypeName(fullyQualifiedName: String): Boolean =
        fullyQualifiedName in knownImmutableTypeNames ||
            fullyQualifiedName.substringBeforeLast(".") in knownImmutableTypePackages

    // Kotlin types that are not mutable themselves, but are commonly implemented by mutable types
    // (e.g. `StringBuilder` is a `CharSequence`, `AtomicInteger` is a `Number`)
    private val openKotlinTypeNames: Set<String> =
        setOf(
            "kotlin.Any",
            "kotlin.AutoCloseable",
            "kotlin.CharSequence",
            "kotlin.Comparable",
            "kotlin.Comparator",
            "kotlin.Function",
            "kotlin.Number",
            "kotlin.collections.AbstractCollection",
            "kotlin.collections.AbstractIterator",
            "kotlin.collections.AbstractList",
            "kotlin.collections.AbstractMap",
            "kotlin.collections.AbstractSet",
            "kotlin.text.Appendable",
        )

    // Packages whose types are all immutable
    private val knownImmutableTypePackages: Set<String> =
        setOf(
            "java.time",
            "kotlinx.datetime",
        )

    // Immutable external types (types that are not Kotlin types)
    private val knownImmutableTypeNames: Set<String> =
        setOf(
            // Java boxed primitives and strings
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Character",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            // Java numbers
            "java.math.BigDecimal",
            "java.math.BigInteger",
            // Java values
            "java.io.File",
            "java.net.URI",
            "java.net.URL",
            "java.nio.charset.Charset",
            "java.nio.file.Path",
            "java.time.format.DateTimeFormatter",
            "java.util.Currency",
            "java.util.Locale",
            "java.util.Optional",
            "java.util.OptionalDouble",
            "java.util.OptionalInt",
            "java.util.OptionalLong",
            "java.util.UUID",
            "java.util.regex.Pattern",
            // Kotlin values
            "kotlin.time.Duration",
            "kotlin.time.Instant",
            "kotlin.uuid.Uuid",
            // Kotlin immutable collections
            "kotlinx.collections.immutable.ImmutableCollection",
            "kotlinx.collections.immutable.ImmutableList",
            "kotlinx.collections.immutable.ImmutableMap",
            "kotlinx.collections.immutable.ImmutableSet",
            "kotlinx.collections.immutable.PersistentCollection",
            "kotlinx.collections.immutable.PersistentList",
            "kotlinx.collections.immutable.PersistentMap",
            "kotlinx.collections.immutable.PersistentSet",
        )

    // Mutable Kotlin and Java types whose names do not start with "Mutable"
    private val knownMutableTypeNames: Set<String> =
        setOf(
            // Kotlin collections
            "AbstractMutableCollection",
            "AbstractMutableList",
            "AbstractMutableMap",
            "AbstractMutableSet",
            "ArrayDeque",
            "ArrayList",
            "HashMap",
            "HashSet",
            "LinkedHashMap",
            "LinkedHashSet",
            // Kotlin arrays
            "Array",
            "BooleanArray",
            "ByteArray",
            "CharArray",
            "DoubleArray",
            "FloatArray",
            "IntArray",
            "LongArray",
            "ShortArray",
            "UByteArray",
            "UIntArray",
            "ULongArray",
            "UShortArray",
            // Java collections
            "ArrayBlockingQueue",
            "ConcurrentHashMap",
            "ConcurrentLinkedDeque",
            "ConcurrentLinkedQueue",
            "ConcurrentSkipListMap",
            "ConcurrentSkipListSet",
            "CopyOnWriteArrayList",
            "CopyOnWriteArraySet",
            "EnumMap",
            "Hashtable",
            "IdentityHashMap",
            "LinkedBlockingDeque",
            "LinkedBlockingQueue",
            "LinkedList",
            "PriorityBlockingQueue",
            "PriorityQueue",
            "Stack",
            "TreeMap",
            "TreeSet",
            "Vector",
            "WeakHashMap",
            // String builders
            "StringBuffer",
            "StringBuilder",
            // Atomics
            "AtomicArray",
            "AtomicBoolean",
            "AtomicInt",
            "AtomicIntArray",
            "AtomicInteger",
            "AtomicIntegerArray",
            "AtomicLong",
            "AtomicLongArray",
            "AtomicReference",
            "AtomicReferenceArray",
        )
}
